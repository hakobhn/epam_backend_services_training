package com.epam.training.backend_services.authdemo.component;

import com.epam.training.backend_services.authdemo.exception.BadRequestException;
import com.epam.training.backend_services.authdemo.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginAttemptService loginAttemptService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String email = request.getParameter("email");
        if (email == null) {
            throw new BadRequestException("Invalid data submitted...");
        }
        String userKey = email + request.getRemoteAddr();
        loginAttemptService.clearHistory(userKey);

        super.setDefaultTargetUrl("/");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
