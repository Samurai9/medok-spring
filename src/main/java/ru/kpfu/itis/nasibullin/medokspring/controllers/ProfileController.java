package ru.kpfu.itis.nasibullin.medokspring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.nasibullin.medokspring.dto.user.UserEditDto;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;
import ru.kpfu.itis.nasibullin.medokspring.services.user.UserService;
import ru.kpfu.itis.nasibullin.medokspring.utils.AuthUtils;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        model.addAttribute("title", "Профиль");
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/edit")
    public String getProfileEditPage(Model model, UserEditDto userEditDto) {
        model.addAttribute("title", "Редактирование");
        return "profileEdit";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/edit")
    public String editProfile(UserEditDto userEditDto) {
        //TODO validation
        User user = userService.editProfile(AuthUtils.getUser(), userEditDto);
        if (user == null) {
            return "redirect:/logout";
        }
        return "redirect:/profile/edit";
    }
}
