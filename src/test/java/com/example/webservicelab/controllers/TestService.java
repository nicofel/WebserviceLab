package com.example.webservicelab.controllers;

import com.example.webservicelab.dtos.DogBreedDto;
import com.example.webservicelab.dtos.DogDto;
import com.example.webservicelab.services.Service;

import java.util.List;
import java.util.Optional;

public class TestService implements Service {
    @Override
    public List<DogDto> getAllDogs() {
        return List.of(new DogDto(1,"Test","Test"), new DogDto(2,"Test2","Test2"));    }

    @Override
    public Optional<DogDto> getOne(Long id) {
        if (id == 1)
        return Optional.of(new DogDto(1,"Test","Test"));
        return Optional.empty();
    }

    @Override
    public DogDto createDog(DogDto dog) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DogDto replace(Long id, DogDto dogDto) {
        return null;
    }

    @Override
    public DogDto update(Long id, DogBreedDto dogBreedDto) {
        return null;
    }

    @Override
    public List<DogDto> getAllByName(String name) {
        return null;
    }
}
