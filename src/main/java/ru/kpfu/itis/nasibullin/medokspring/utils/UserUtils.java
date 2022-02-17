package ru.kpfu.itis.nasibullin.medokspring.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import ru.kpfu.itis.nasibullin.medokspring.entities.Product;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;

public class UserUtils {
    private static final String CATAAS = "https://cataas.com/cat/cute";
    private static final String PATH = "src/main/resources/static/img/ProfileImages/";
    private static final String TARGET_PATH = "target/classes/static/img/ProfileImages/";
    private static final String EXTENSION = ".jpg";
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private static Logger logger = LoggerFactory.getLogger(UserUtils.class);

    public static void setProfilePicture(User user) {
        URI cataasUrl = URI.create(CATAAS);
        try (InputStream inputStream = cataasUrl.toURL().openStream()) {

            String filePath = PATH + user.getName() + System.currentTimeMillis() + EXTENSION;
            File file = new File(Files.createFile(Paths.get(filePath)).toString());
            copyInputStreamToFile(inputStream, file);

            String targetPath = TARGET_PATH + file.getName();
            Files.copy(Paths.get(file.getPath()), Paths.get(targetPath));
            user.setProfilePicturePath(filePath.split("static")[1]);
        } catch (IOException e) {
            logger.error(e.getMessage());
            user.setProfilePicturePath("/img/ProfileImages/default-profile-picture.png");
        }
    }

    private static void copyInputStreamToFile(InputStream inputStream, File file)
      throws IOException {

        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        }
    }

    public static double getPrice(User user) {
        if (user.getBasket().getProducts() == null) {
            return 0;
        }
        double res = 0;
        for (Product product: user.getBasket().getProducts()) {
            res += product.getPrice();
        }
        res -= user.getDiscount()*user.getBasket().getProducts().size();
        return res;
    }
}
