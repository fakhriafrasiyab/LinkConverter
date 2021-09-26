package com.example.linkconverter.controller;

import com.example.linkconverter.model.Links;
import com.example.linkconverter.model.RequestUrl;
import com.example.linkconverter.service.ConverterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConverterController {

    private final ConverterService converterService;

    @PostMapping("/convert")
    public Links convertUrl(@RequestBody RequestUrl webUrl) {
        return converterService.convertUrl(webUrl);
    }
}
