package com.example.linkconverter.service;

import com.example.linkconverter.model.Links;
import com.example.linkconverter.model.RequestUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ConverterServiceTest {
    @Mock
    private ConverterService converterService;
    private Links links;
    private RequestUrl requestUrl;

    @BeforeEach
    void setUp() {
        links = Links.builder()
                .deeplink("ty://?Page=Search&Query=eqlbse")
                .webUrl("https://www.trendyol.com/tum--urunler?q=eqlbse")
                .createDate(LocalDateTime.now())
                .build();

        requestUrl = RequestUrl.builder()
                .webUrl("https://www.trendyol.com/tum--urunler?q=eqlbse")
                .build();
    }

    @Test
    void givenWebUrlWhenConvertUrlThenSuccess() {
        //Arrange
        when(converterService.convertUrl(any())).thenReturn(links);

        //Act
        Links links = converterService.convertUrl(requestUrl);

        //Assert
        assertThat(links.getDeeplink()).isNotEmpty();
        assertThat(links.getWebUrl()).isNotEmpty();
        verify(converterService).convertUrl(any());
    }
}
