package com.livraria.bookstore.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.livraria.bookstore.entities.Bookstore;

public class BookstoreUtils {

    private static final Faker faker = Faker.instance();

    public static Bookstore createFakeBookstore(){
        return Bookstore.builder()
                .id(faker.number().randomNumber())
                .trade("Bookstore Fantasy")
                .company("Bookstore LTDA")
                .CNPJ("123456789")
                .CEP("00000000").build();
    }

    public static String asJsonString(Bookstore bookstore){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(bookstore);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
