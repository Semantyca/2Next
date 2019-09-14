package com.toonext.ftengine;

public class ElasticSearchConfiguration {

    private String host = "localhost";
    private int port = 9200;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
