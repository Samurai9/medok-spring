package ru.kpfu.itis.nasibullin.medokspring.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.kpfu.itis.nasibullin.medokspring.entities.User;

public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       try {
           servletRequest.setAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
       } catch (NullPointerException | ClassCastException e){
           //ignore
       }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
