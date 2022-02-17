package ru.kpfu.itis.nasibullin.medokspring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.nasibullin.medokspring.dto.user.UserRegisterDto;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;
import ru.kpfu.itis.nasibullin.medokspring.services.user.UserService;
import ru.kpfu.itis.nasibullin.medokspring.utils.AuthUtils;

@Controller
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String CLIENT_ID = "8e74b415da2d42222120";
    private static final String CLIENT_SECRET = "46d76431c5bfc2fcfe2398bb063c5811ef4e8eb4";

    private static final String AUTHORISE_HREF = "https://github.com/login/oauth/authorize?client_id=" + CLIENT_ID
      + "&redirect_uri=http://localhost:8080/authGitHub&scope=user";

    private static final String GET_ACCESS_TOKEN_HREF = "https://github.com/login/oauth/access_token";
    private static final String GET_INFO_FROM_TOKEN_HREF = "https://api.github.com/applications/" + CLIENT_ID + "/token";


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/auth")
    public String getAuthPage(Model model, HttpServletRequest request, UserRegisterDto userRegisterDto) {
        if (AuthUtils.isAuthenticated()) {
            return "redirect:/profile";
        }

        model.addAttribute("github", AUTHORISE_HREF);
        model.addAttribute("title", "Вход");

        AuthenticationException errorMessage = (AuthenticationException) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        if (errorMessage != null) {
            model.addAttribute("error", errorMessage.getMessage());
        }

        return "auth";
    }

    @GetMapping("/authGitHub")
    public String authWithGutHub(@RequestParam String code) {
        if (code != null && !code.equals("")) {
            userService.authWithGithub(code);
        }
        return "redirect:/profile";
    }

    @PostMapping("/reg")
    public String handleReg(@Valid UserRegisterDto dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("github", AUTHORISE_HREF);
            model.addAttribute("title", "Вход");
            return "auth";
        } else {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
            User user = dto.toUser();
            userService.save(user);
            logger.info("User " + user + " has been registered");

            redirectAttributes.addFlashAttribute("message", "Регистрация прошла успешно");
            return "redirect:/auth";
        }


    }
}
