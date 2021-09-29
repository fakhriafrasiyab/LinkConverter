package com.example.linkconverter.strategy;

import com.example.linkconverter.MatchingParameters;
import com.example.linkconverter.model.Links;
import com.example.linkconverter.model.RequestUrl;

import java.time.LocalDateTime;

public class SearchStrategy implements LinkConverterStrategy {

    @Override
    public Links convertUrl(RequestUrl webUrl) {
        StringBuilder deeplink = new StringBuilder();
        deeplink.append("ty://?Page=");
        MatchingParameters matchingParameters = new MatchingParameters();
        matchingParameters.matchParam(webUrl.getWebUrl(), "q").ifPresent(id -> deeplink.append("Search&Query=" + id));
        return Links.builder()
                .webUrl(webUrl.getWebUrl())
                .deeplink(deeplink.toString())
                .createDate(LocalDateTime.now())
                .build();
    }
}
