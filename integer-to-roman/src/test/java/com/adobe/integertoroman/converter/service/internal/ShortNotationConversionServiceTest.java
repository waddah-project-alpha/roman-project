package com.adobe.integertoroman.converter.service.internal;

import com.adobe.integertoroman.utils.PromotheusRegistryMockImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(PromotheusRegistryMockImplementation.class)
public class ShortNotationConversionServiceTest {
    @Autowired
    private ShortNotationConversionService service;

    @Test
    public void testMapIsNotEmpty() {
        Assertions.assertNotNull(service.getMap());
    }
}
