package com.example.linkconverter.controller;

import static java.lang.String.format;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.linkconverter.model.Links;
import com.example.linkconverter.model.RequestUrl;
import com.example.linkconverter.service.ConverterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

@SuppressWarnings("JavadocMethod")
@WebMvcTest(ConverterController.class)
@RunWith(SpringRunner.class)
class ConvertControllerTest {

    public static final String SUCCESS = "success";
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    private ConverterService converterService;
    private Links links;
    private RequestUrl requestUrl;

    @BeforeEach
    @SuppressWarnings("MethodLength")
    void setUp() {
        links = Links.builder()
                .createDate(LocalDateTime.now())
                .webUrl("https://www.trendyol.com/tum--urunler?q=eqlbse")
                .deeplink("ty://?Page=Search&Query=eqlbse")
                .build();
        requestUrl = RequestUrl.builder()
                .webUrl("https://www.trendyol.com/tum--urunler?q=eqlbse")
                .build();
    }

    @Test
    void givenWebUrlWhenConvertUrlThenSuccess() throws Exception {
        when(converterService.convertUrl(any())).thenReturn(links);

        mockMvc.perform(post("/localhost:8080/convert")
                        .content(objAsJson(requestUrl))
                        .characterEncoding("UTF-8")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(format("{'message':%s}", SUCCESS)));
    }

    private String objAsJson(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
