package ru.kpfu.itis.nasibullin.medokspring.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {IllegalArgumentException.class})
    private String handleIllegalArgument(RuntimeException ex, WebRequest request, Model model) {
        logger.error("Request: " + request.toString() + "; Exception: " + ex.toString());
        model.addAttribute("title", "Ошибка");
        model.addAttribute("message", "Что-то пошло не так");
        return "errorPage";
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    private String handleAccessDeniedException(RuntimeException ex, WebRequest request, Model model) {
        logger.error("Request: " + request.toString() + "; Exception: " + ex.toString());
        model.addAttribute("title", "Доступ запрещен");
        model.addAttribute("message", "Доступ запрещен");
        return "errorPage";
    }

    @ExceptionHandler(value = {UserNotAuthorizedException.class})
    private String handleUserNotAuthorized() {
        return "redirect:/auth";
    }

    @ExceptionHandler(value = {IllegalBasketActionException.class, IllegalEditAccountActionException.class})
    private String handleWrongFormAction(RuntimeException ex, WebRequest request, Model model) {
        logger.error("Request: " + request.toString() + "; Exception: " + ex.toString());
        model.addAttribute("title", "Ошибка");
        model.addAttribute("message", "Неверные данные");
        return "errorPage";
    }

    @ExceptionHandler(value = {IllegalProductIdException.class, NotFoundException.class, MethodArgumentTypeMismatchException.class})
    private String handleNotFound(RuntimeException ex, WebRequest request, Model model) {
        logger.error("Request: " + request.toString() + "; Exception: " + ex.toString());
        model.addAttribute("title", "Не найдено");
        model.addAttribute("message", "Такой страницы не существует");
        return "errorPage";
    }

    @ExceptionHandler(value = {Exception.class})
    private String handleOtherExceptions(RuntimeException ex, WebRequest request, Model model) {
        logger.error("Request: " + request.toString() + "; Exception: " + ex.toString());
        model.addAttribute("title", "Ошибка");
        model.addAttribute("message", "Что-то пошло не так");
        return "errorPage";
    }
}
