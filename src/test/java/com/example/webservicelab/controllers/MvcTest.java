package com.example.webservicelab.controllers;


import com.example.webservicelab.dtos.DogDto;
import com.example.webservicelab.services.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DogController.class)
//@Import(TestService.class)
public class MvcTest {

    @MockBean
    Service service;

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper jsonMapper;

    public static String asJsonString(final Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void getAllDogs() throws Exception{

        when(service.getAllDogs()).thenReturn(List.of(new DogDto(1,"","")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/dogs")
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void getOneDogById() throws Exception{
        when (service.getOne(1L)).thenReturn(Optional.of(new DogDto(1L,"","")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/dogs/{id}",1L)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
//        mockMvc.perform(MockMvcRequestBuilders.get("/dogs/{id}",1)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.dogId").value(1));

    }

    @Test
    void postCreateDog() throws Exception{
        //when(service.createDog(any(DogDto.class))).thenReturn(new DogDto(1,"",""));

        mockMvc.perform(MockMvcRequestBuilders.post("/dogs")
                .content(asJsonString(new DogDto(1, "name", "breed")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    void searchDog() throws Exception{
        when(service.getAllByName("test")).thenReturn(List.of(new DogDto(1,"name","breed")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/dogs/search/test")
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void deleteDog() throws Exception{
//                service.delete(1L);
//                verify(service).delete(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/dogs/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    void patchDog() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.patch("/dogs/{id}",1)
                .content(asJsonString(new DogDto(1,"name","breed")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk());
    }

    @Test
    void putUpdateDog() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/dogs/{id}",2)
                .content(asJsonString(new DogDto(2,"name2","breed2")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
