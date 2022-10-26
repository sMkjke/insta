package com.github.smkjke.insta.security;

import com.github.smkjke.insta.payload.responce.InvalidLoginResponce;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//catch autorization error and return 401
@Component
public class JWTAuthentificationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        InvalidLoginResponce loginResponce = new InvalidLoginResponce();
        String jsonLoginResponce = new Gson().toJson(loginResponce);
        response.setContentType(SecurityConstants.CONTENT_TYPE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().println(jsonLoginResponce);
    }
}
