package com.github.example.config;

import com.github.example.utils.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CorsConfig implements Filter {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        String servletPath = request.getServletPath();
        logger.warn("加密前"+servletPath);
        String s = UrlUtil.enCryptAndEncode(servletPath);
        System.out.println("加密后"+s);
        String s1 = null;
        try {
            s1 = UrlUtil.deCryptAndDecode(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.warn("解密后"+s1);

        filterChain.doFilter(servletRequest, servletResponse);
    }
    public void destroy() {}
}
