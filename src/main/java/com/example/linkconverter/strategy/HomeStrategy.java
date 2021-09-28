package com.example.linkconverter.strategy;

import com.example.linkconverter.model.Links;
import com.example.linkconverter.model.RequestUrl;

import java.time.LocalDateTime;

public class HomeStrategy implements Strategy{
    @Override
    public Links convertUrl(RequestUrl webUrl) {
        StringBuilder deeplink = new StringBuilder();
        deeplink.append("ty://?Page=Home");
        return Links.builder()
                .webUrl(webUrl.getWebUrl())
                .deeplink(deeplink.toString())
                .createDate(LocalDateTime.now())
                .build();
    }
}
