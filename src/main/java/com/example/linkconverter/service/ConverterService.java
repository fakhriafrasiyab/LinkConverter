package com.example.linkconverter.service;

import com.example.linkconverter.model.Links;
import com.example.linkconverter.model.RequestUrl;
import com.example.linkconverter.repository.LinksRepository;
import com.example.linkconverter.strategy.LinkConverterFactoryStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConverterService {

    private final LinksRepository linksRepository;

    public Links convertUrl(RequestUrl webUrl) {
        LinkConverterFactoryStrategy linkConverterFactoryStrategy = new LinkConverterFactoryStrategy();
        Links links = linkConverterFactoryStrategy.linkConverterStrategy(webUrl);
        linksRepository.save(links);
        return links;
    }
}
