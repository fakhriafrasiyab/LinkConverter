package com.example.linkconverter.strategy;

import com.example.linkconverter.model.Links;
import com.example.linkconverter.model.RequestUrl;

public class LinkConverterFactoryStrategy {
    private final LinkConverterStrategy productDetailStrategy = new ProductDetailStrategy();
    private final LinkConverterStrategy searchStrategy = new SearchStrategy();
    private final LinkConverterStrategy homeStrategy = new HomeStrategy();

    public Links linkConverterStrategy (RequestUrl webUrl) {
        Links links;
        if (webUrl.getWebUrl().contains("-p-")) {
            links = productDetailStrategy.convertUrl(webUrl);
        } else if (webUrl.getWebUrl().contains("tum--urunler")) {
            links = searchStrategy.convertUrl(webUrl);
        } else {
            links = homeStrategy.convertUrl(webUrl);
        }
        return links;
    }
}
