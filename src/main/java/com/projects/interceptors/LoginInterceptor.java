package com.projects.interceptors;

import com.projects.utils.JwtUtil;
import com.projects.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        System.out.println("JWT Token:  " + token);
        // Verify JWT Token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);

            // Use ThreadLocal to Store User Data into Thread's Private Data Storage
            ThreadLocalUtil.set(claims);

            return true;
        } catch (Exception e) {
            // Return Http Code as 401 -> Unauthorized
            e.printStackTrace();
            response.setStatus(401);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Avoid Memory Leak
        ThreadLocalUtil.remove();
    }
}
