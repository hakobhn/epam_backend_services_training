package com.epam.training.backend_services.authdemo.filter;

import com.epam.training.backend_services.authdemo.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class AttemptFilter extends HttpFilter {

    private final LoginAttemptService loginAttemptService;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String email = request.getParameter("email");
        if (null != email) {
            String userKey = email + request.getRemoteAddr();
            if (!loginAttemptService.canAttemptNow(userKey)) {
                String errorMsg = "Your account has been locked due to 3 failed attempts."
                        + " It will be unlocked after " + loginAttemptService.shouldWait(userKey) + " seconds.";
                response.sendRedirect("/login?error="+errorMsg);
                return;
            }
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
