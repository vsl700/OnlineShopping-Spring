package com.vsl700.onlineshopping;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.vsl700.onlineshopping.data.models.Stock;

public class OnlineshoppingApplicationStocksTests extends OnlineshoppingApplicationMvcTests {
    @Test
    public void getStocksList() throws Exception{
        String uri = "/api/stocks";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        //Stock[] stocks = mapFromJson(content, Stock[].class);
        assertDoesNotThrow(() -> mapFromJson(content, Stock[].class));
        //assertTrue(stocks.length > 0);
    }

    @Test
    public void createStock() throws Exception{
        String uri = "/create";
        
        MockMultipartFile file = new MockMultipartFile("imageFile", "hello.png", MediaType.IMAGE_PNG_VALUE, "simple content".getBytes());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.multipart(uri).file(file).param("name", "Apple").param("description", "Fresh, green apple!")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(302, status);
    }

    @Test
    public void viewStock() throws Exception{
        String uri = "/api/stocks";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Stock[] stocks = mapFromJson(content, Stock[].class);

        String stockId = stocks[0].getId();

        String uri2 = "/view/%s".formatted(stockId);
        MvcResult mvcResult2 = mvc.perform(MockMvcRequestBuilders.get(uri2).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        int status = mvcResult2.getResponse().getStatus();
        assertEquals(200, status);
    }
}
