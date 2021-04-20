package com.zilch.challengealicia.controller;

import com.zilch.challengealicia.model.Card;
import com.zilch.challengealicia.service.CardService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(CardController.class)
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CardService cardService;

    @WithMockUser(roles = "USER")
    @Test
    void createCard() throws Exception {

        String nameJson = "{\"name\":\"Test Test\"}";

        Card mockCard = new Card(1, "Test Test", 2, 12, 456, "1111222233334444");

        Mockito.when(cardService.generateCard(Mockito.anyString())).thenReturn(mockCard);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cards")
                .accept(MediaType.APPLICATION_JSON).content(nameJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        String expected = "{\"id\":1,\"number\":\"1111222233334444\",\"expireMonth\":2,\"expireYear\":12,\"name\":\"Test Test\",\"cvc\":456}";
        assertEquals(expected, response.getContentAsString());
    }

    @WithMockUser(roles = "USER")
    @Test
    void createCardNotJson() throws Exception {

        Card mockCard = new Card(1, "Test Test", 2, 12, 456, "1111222233334444");

        Mockito.when(cardService.generateCard(Mockito.anyString())).thenReturn(mockCard);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cards")
                .accept(MediaType.APPLICATION_JSON).content("Test Test")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @WithMockUser(roles = "USER")
    @Test
    void createCardWrongJson() throws Exception {
        String nameJson = "{\"id\":\"Test Test\"}";

        Card mockCard = new Card(1, "Test Test", 2, 12, 456, "1111222233334444");

        Mockito.when(cardService.generateCard(Mockito.anyString())).thenReturn(mockCard);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cards")
                .accept(MediaType.APPLICATION_JSON).content(nameJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @WithMockUser(roles = "USER")
    @Test
    void findEmptyCards() throws Exception {

        Mockito.when(cardService.findAllCards()).thenReturn(Collections.EMPTY_LIST);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cards").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String expected = "[]";
        assertEquals(Integer.toString(HttpStatus.OK.value()), Integer.toString(response.getStatus()));
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @WithMockUser(roles = "USER")
    @Test
    void findCards() throws Exception {
        Card card1 = new Card(1, "Test one test", 4, 23, 756, "1111222233334444");
        Card card2 = new Card(2, "Test two test", 4, 23, 971, "1111222233334444");

        Mockito.when(cardService.findAllCards()).thenReturn(Arrays.asList(card1, card2));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cards").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String expected = "[{\"id\":1,\"number\":\"1111222233334444\",\"expireMonth\":4,\"expireYear\":23,\"name\":\"Test one test\",\"cvc\":756},{\"id\":2,\"number\":\"1111222233334444\",\"expireMonth\":4,\"expireYear\":23,\"name\":\"Test two test\",\"cvc\":971}]";
        assertEquals(Integer.toString(HttpStatus.OK.value()), Integer.toString(response.getStatus()));
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @WithMockUser(roles = "USER")
    @Test
    void findCard() throws Exception {
        Card card1 = new Card(1, "Test one test", 4, 23, 756, "1111222233334444");

        Mockito.when(cardService.findCard(Mockito.anyInt())).thenReturn(java.util.Optional.of(card1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cards/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String expected = "{\"id\":1,\"number\":\"1111222233334444\",\"expireMonth\":4,\"expireYear\":23,\"name\":\"Test one test\",\"cvc\":756}";
        assertEquals(Integer.toString(HttpStatus.OK.value()), Integer.toString(response.getStatus()));
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

}