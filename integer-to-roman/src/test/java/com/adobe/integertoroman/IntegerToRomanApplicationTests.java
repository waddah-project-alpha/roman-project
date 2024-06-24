package com.adobe.integertoroman;

import com.adobe.integertoroman.converter.controller.IntegerToRomanConverterController;
import com.adobe.integertoroman.utils.PromotheusRegistryMockImplementation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(PromotheusRegistryMockImplementation.class)
class IntegerToRomanApplicationTests {

    @Autowired
    private IntegerToRomanConverterController integerToRomanConverterController;

    @Test
    void contextLoads() {
        Assert.assertNotNull(integerToRomanConverterController);
    }

}
