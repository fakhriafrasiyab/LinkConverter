package com.example.linkconverter.strategy;

import com.example.linkconverter.model.Links;
import com.example.linkconverter.model.RequestUrl;

public interface Strategy {
    Links convertUrl(RequestUrl webUrl);
}
