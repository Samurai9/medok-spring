package ru.kpfu.itis.nasibullin.medokspring.utils;

import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;
import ru.kpfu.itis.nasibullin.medokspring.exceptions.UserNotAuthorizedException;

public class AuthUtils {
    public static User getUser() {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
            throw new UserNotAuthorizedException("User is not authorized");
        }
    }

    public static boolean isAuthenticated() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user != null;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public static void authenticate(User user) {
        RunAsUserToken token = new RunAsUserToken(user.getUsername(), user, user.getPassword(), user.getAuthorities(), UsernamePasswordAuthenticationToken.class);
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
