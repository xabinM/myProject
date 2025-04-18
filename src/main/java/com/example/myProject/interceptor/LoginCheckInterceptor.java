package com.example.myProject.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);    // 세션이 없으면 null

        if (session == null || session.getAttribute("loginMember") == null) {
            // 없다면 로그인 페이지(/login)로 보내고, 원래 가려던 URL도 같이 넘겨줘서 로그인 후 이동 가능하게 함
            response.sendRedirect("/login?redirectURL=" + request.getRequestURI());
            return false;
        }

        return true;
    }
}
