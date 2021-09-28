package com.example.linkconverter.strategy;

import com.example.linkconverter.MatchingParameters;
import com.example.linkconverter.model.Links;
import com.example.linkconverter.model.RequestUrl;

import java.time.LocalDateTime;

public class ProductDetailStrategy implements Strategy {

    @Override
    public Links convertUrl(RequestUrl webUrl) {
        StringBuilder deeplink = new StringBuilder();
        deeplink.append("ty://?Page=");
        MatchingParameters matchingParameters = new MatchingParameters();
        matchingParameters.matchContentId(webUrl.getWebUrl(), "p-").ifPresent(id -> deeplink.append("Product&ContentId=" + id));
        matchingParameters.matchParam(webUrl.getWebUrl(), "boutiqueId").ifPresent(id -> deeplink.append("&CampaignId=" + id));
        matchingParameters.matchParam(webUrl.getWebUrl(), "merchantId").ifPresent(id -> deeplink.append("&MerchantId=" + id));
        return Links.builder()
                .webUrl(webUrl.getWebUrl())
                .deeplink(deeplink.toString())
                .createDate(LocalDateTime.now())
                .build();
    }
}
