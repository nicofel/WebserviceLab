package com.example.webservicelab.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//Unit tests, testing one thing at a time in isolation.
class DogControllerTest {


    @Test
    void callingOneWithValidIdReturnsOneDog(){
        DogController dogController = new DogController(new TestService());

        var dog= dogController.one(1L);

        assertThat(dog.getId()).isEqualTo(1);
        assertThat(dog.getName()).isEqualTo("Test");
        assertThat(dog.getBreed()).isEqualTo("Test");
    }

    @Test
    void callingOneWithInvalidIdThrowsExceptionWithResponseStatus404(){
        DogController dogController = new DogController(new TestService());

        var dog= dogController.one(2L);

        var exception = assertThrows(ResponseStatusException.class, () -> dogController.one(2L));
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}