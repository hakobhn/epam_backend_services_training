package com.epam.training.backend_services.authdemo.component;

import com.epam.training.backend_services.authdemo.exception.BadRequestException;
import com.epam.training.backend_services.authdemo.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final LoginAttemptService loginAttemptService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws ServletException, IOException {

        String email = request.getParameter("email");

        if (email == null) {
            throw new BadRequestException("Invalid data submitted...");
        }
        String userKey = email + request.getRemoteAddr();
        if (loginAttemptService.canAttemptNow(userKey)) {
            loginAttemptService.registerFailedAttempt(userKey);
            super.setDefaultFailureUrl("/login?error");
        } else {
            String errorMsg = "Your account has been locked due to 3 failed attempts."
                    + " It will be unlocked after " + loginAttemptService.shouldWait(userKey) + " seconds.";
            super.setDefaultFailureUrl("/login?error="+errorMsg);
        }

        super.onAuthenticationFailure(request, response, exception);
    }
}
