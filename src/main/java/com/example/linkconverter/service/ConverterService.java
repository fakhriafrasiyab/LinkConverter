package com.example.linkconverter.service;

import com.example.linkconverter.MatchingParameters;
import com.example.linkconverter.model.Links;
import com.example.linkconverter.model.RequestUrl;
import com.example.linkconverter.repository.LinksRepository;
import com.example.linkconverter.strategy.HomeStrategy;
import com.example.linkconverter.strategy.ProductDetailStrategy;
import com.example.linkconverter.strategy.SearchStrategy;
import com.example.linkconverter.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConverterService {

    private final LinksRepository linksRepository;

    public Links convertUrl(RequestUrl webUrl) {
        StringBuilder deeplink = new StringBuilder();
        deeplink.append("ty://?Page=");
        Links links;
        MatchingParameters matchingParameters = new MatchingParameters();
        if (webUrl.getWebUrl().contains("-p-")) {
            Strategy strategy = new ProductDetailStrategy();
            links = strategy.convertUrl(webUrl);
        } else if (webUrl.getWebUrl().contains("tum--urunler")) {
            Strategy strategy = new SearchStrategy();
            links = strategy.convertUrl(webUrl);
        } else {
            Strategy strategy = new HomeStrategy();
            links = strategy.convertUrl(webUrl);
        }
        linksRepository.save(links);
        return links;
    }
}
