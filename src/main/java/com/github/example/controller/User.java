package com.github.example.controller;

import com.github.example.config.UserConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class User {
    private static final Logger logger = LogManager.getLogger(User.class);

    @Autowired
    UserConfig userConfig;
    @RequestMapping("/getUser")
    public String getUser(){
        UserConfig.PictureHost pictureHost = userConfig.getPictureHost();
        UserConfig.VideoHost videoHost = userConfig.getVideoHost();
        logger.info("pictureHost-ip: "+pictureHost.getIp() + "pictureHost-port: "+pictureHost.getPort());
        logger.info("videoHost-ip: "+videoHost.getIp() + "videoHost-port: "+videoHost.getPort());
        return "getUser";
    }
}
