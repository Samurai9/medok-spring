package ru.kpfu.itis.nasibullin.medokspring.services.user;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.nasibullin.medokspring.dto.user.UserEditDto;
import ru.kpfu.itis.nasibullin.medokspring.entities.Basket;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;
import ru.kpfu.itis.nasibullin.medokspring.entities.UserRole;
import ru.kpfu.itis.nasibullin.medokspring.repositories.basket.BasketRepository;
import ru.kpfu.itis.nasibullin.medokspring.repositories.user.UserRepository;
import ru.kpfu.itis.nasibullin.medokspring.utils.AuthUtils;
import ru.kpfu.itis.nasibullin.medokspring.utils.UserUtils;

@Service
public class UserServiceImpl implements UserService {

    private static final String CLIENT_ID = "8e74b415da2d42222120";
    private static final String CLIENT_SECRET = "46d76431c5bfc2fcfe2398bb063c5811ef4e8eb4";

    private static final String GET_ACCESS_TOKEN_HREF = "https://github.com/login/oauth/access_token";

    private static final String GET_INFO_FROM_TOKEN_HREF = "https://api.github.com/applications/" + CLIENT_ID + "/token";
    private static final String GET_EMAIL_URL = "https://api.github.com/user/emails";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s);
    }

    @Override
    public User save(User user) {
        if (user.getUserId() == 0) {
            UserUtils.setProfilePicture(user);
            user.setDiscount(50);
        }
        if (user.getBasket() == null) {
            Basket basket = new Basket();
            basket.setUser(user);
            user.setBasket(basketRepository.save(basket));
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User editProfile(User user, UserEditDto dto) {
        switch (dto.getAction()) {
            case CHANGE_INF:
                user.setName(dto.getName());
                user.setEmail(dto.getEmail());
                this.save(user);
                break;
            case CHANGE_PASSWORD:
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
                this.save(user);
                break;
            case DELETE_ACC:
                if (dto.getAgreement() != null) {
                    this.delete(user);
                    return null;
                }
        }
        return userRepository.save(user);
    }

    @Override
    public User authWithGithub(String code) {

        String accessToken = getAccessToken(code);
        String email = getEmail(accessToken);

        User user;
        if ((user = userRepository.findByEmail(email)) == null) {
            String login = getLogin(accessToken);

            user = new User();
            user.setEmail(email);
            user.setName(login);
            user.setPassword(passwordEncoder.encode(email + login));
            user.setRole(UserRole.USER);

            this.save(user);
        }

        AuthUtils.authenticate(user);
        return user;
    }

    private String getEmail(String code) {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(GET_EMAIL_URL);
        get.setHeader("Accept-Language", "en-us");
        get.setHeader("Accept", "application/json");
        get.setHeader("Authorization", "token " + code);
        get.setHeader("Accept-Encoding", "UTF-8");

        try (InputStream inputStream = client.execute(get).getEntity().getContent()) {
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            JsonArray array = new Gson().fromJson(reader, JsonArray.class);
            return array.get(0).getAsJsonObject().get("email").getAsString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getAccessToken(String code) {
        try {
            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(GET_ACCESS_TOKEN_HREF);

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("client_id", CLIENT_ID));
            params.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
            params.add(new BasicNameValuePair("code", code));

            post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
            post.addHeader("Accept", "application/json");
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String accessToken = null;
            if (entity != null) {
                try (InputStream is = entity.getContent()) {
                    accessToken = new Gson().fromJson(new InputStreamReader(is), JsonObject.class).get("access_token").getAsString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return accessToken;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getLogin(String accessToken) {
        HttpPost post = new HttpPost(GET_ACCESS_TOKEN_HREF);
        HttpClient client = HttpClients.createDefault();
        post = new HttpPost(GET_INFO_FROM_TOKEN_HREF);
        StringEntity stringEntity = null;

        try {
            stringEntity = new StringEntity("{\"access_token\":\"" + accessToken + "\"}");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        post.setEntity(stringEntity);
        post.addHeader("Accept", "application/vnd.github.v3+json");
        post.addHeader("User-Agent", "App");
        post.addHeader("Authorization", " Basic " + Base64.getEncoder()
          .encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes()));

        try (InputStream inputStream = client.execute(post).getEntity().getContent()) {
            JsonObject object = new Gson().fromJson(new InputStreamReader(inputStream), JsonObject.class);
            return object.getAsJsonObject("user").get("login").getAsString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
