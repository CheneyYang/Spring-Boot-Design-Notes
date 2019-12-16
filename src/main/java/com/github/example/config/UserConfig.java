package com.github.example.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("userconfig")
public class UserConfig {
    private PictureHost pictureHost =  new PictureHost();
    private VideoHost videoHost =  new VideoHost();

    public PictureHost getPictureHost() {
        return pictureHost;
    }

    public void setPictureHost(PictureHost pictureHost) {
        this.pictureHost = pictureHost;
    }

    public VideoHost getVideoHost() {
        return videoHost;
    }

    public void setVideoHost(VideoHost videoHost) {
        this.videoHost = videoHost;
    }




    public class PictureHost{
        private String ip;
        private int port;
        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

    public class VideoHost{
        private String ip;
        private int port;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

    }


}
