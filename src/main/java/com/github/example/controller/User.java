package com.github.example.controller;

import com.github.example.config.UserProperties;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


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
    //签发JWT
    private static final String SECRET_KEY = "CRM_API_Key";
    String jwtToken;
    @RequestMapping("/cryptJwt")
    public String cryptJwt(ServletRequest request){
        // 生成token
        jwtToken = Jwts.builder()
                // 头部
                .setHeaderParam("type", "JWT")

                // jwt 标注中的申明
                .setIssuedAt(new Date()) // 签发时间
                .setExpiration(new Date(new Date().getTime() + 10000L))// 过期时间
                .setIssuer("StrategyTeam")// jwt的签发者
                .setSubject("crm")// jwt面向的客户

                // 公共申明和私有申明
                .claim("user_id", "admin")
                .claim("phone", "18251421000")
                .claim("age", 25)
                .claim("sex", "男")
                // 签证算法
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();

        System.out.println("生成的 jwt token 如下:" + jwtToken);

        return "urlCrypt";
    }

    @RequestMapping("/decryptJwt")
    public String decryptJwt(ServletRequest request){

        Jws<Claims> claimsJws = Jwts.parser()
                // 验证签发者字段iss 必须是 huan
                .require("iss", "StrategyTeam")
                // 设置私钥
                .setSigningKey(SECRET_KEY.getBytes())
                // 解析jwt字符串
                .parseClaimsJws(jwtToken);

        // 获取头部信息
        JwsHeader header = claimsJws.getHeader();
        // 获取载荷信息
        Claims payload = claimsJws.getBody();

        System.out.println("解析过来的jwt的header如下:" + header.toString());
        System.out.println("解析过来的jwt的payload如下:" + payload.toString());
        return "urlCrypt";
    }





}
