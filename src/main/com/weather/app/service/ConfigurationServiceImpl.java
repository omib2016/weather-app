package com.weather.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by omib on 20/06/2016.
 */
@Component
@PropertySource(value = "application.properties")
public class ConfigurationServiceImpl implements ConfigurationService
{
    @Value("${url}")
    private String url;

    @Value("${appId}")
    private String appId;

    @Override
    public String getUrl()
    {
        return url;
    }

    @Override
    public String getAppid()
    {
        return appId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
