package com.github.example.controller;

import com.github.example.config.UserProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/")
public class User {
//    private static final Logger logger = LogManager.getLogger(User.class);
    private static Logger logger = LoggerFactory.getLogger(User.class);
    @Autowired
    UserProperties userConfig;

    @RequestMapping("/getUser")
    public String getUser(ServletRequest request){
        UserProperties.PictureHost pictureHost = userConfig.getPictureHost();
        UserProperties.VideoHost videoHost = userConfig.getVideoHost();
        logger.info("pictureHost-ip: "+pictureHost.getIp() + "pictureHost-port: "+pictureHost.getPort());
        logger.info("videoHost-ip: "+videoHost.getIp() + "videoHost-port: "+videoHost.getPort());
        return "getUser";
    }
    @RequestMapping("/urlCrypt")
    public String getUrlCrypt(ServletRequest request){
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String servletPath = httpRequest.getServletPath();
        return "urlCrypt";
    }

}
