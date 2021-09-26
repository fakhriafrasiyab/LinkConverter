package com.example.linkconverter.service;

import com.example.linkconverter.model.Links;
import com.example.linkconverter.model.RequestUrl;
import com.example.linkconverter.repository.LinksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ConverterService {

    private final LinksRepository linksRepository;

    private Optional<String> matchParam(String source, String paramName) {
        Pattern logEntry = Pattern.compile(paramName + "=(.*?)(&|$)");

        Matcher matchPattern = logEntry.matcher(source);

        while (matchPattern.find()) {
            return Optional.of(matchPattern.group(1));
        }
        return Optional.empty();
    }

    public Links convertUrl(RequestUrl webUrl) {
        StringBuilder deeplink = new StringBuilder();
        deeplink.append("ty://?Page=");
        if (webUrl.getWebUrl().contains("-p-")) {
            deeplink.append("Product&ContentId=");
            int beginIndex = webUrl.getWebUrl().lastIndexOf("-") + 1;
            deeplink.append(webUrl.getWebUrl(), beginIndex, beginIndex + 7);
            matchParam(webUrl.getWebUrl(), "boutiqueId").ifPresent(id -> deeplink.append("&CampaignId=" + id));
            matchParam(webUrl.getWebUrl(), "merchantId").ifPresent(id -> deeplink.append("&MerchantId=" + id));
        } else if (webUrl.getWebUrl().contains("tum--urunler")) {
            matchParam(webUrl.getWebUrl(), "q").ifPresent(id -> deeplink.append("Search&Query=" + id));
        } else {
            deeplink.append("Home");
        }
        Links links = Links.builder()
                .webUrl(webUrl.getWebUrl())
                .deeplink(deeplink.toString())
                .createDate(LocalDateTime.now())
                .build();
        linksRepository.save(links);
        return links;
    }
}
