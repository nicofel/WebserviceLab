package com.example.webservicelab.services;

import com.example.webservicelab.dtos.DogBreedDto;
import com.example.webservicelab.dtos.DogDto;

import java.util.List;
import java.util.Optional;

public interface Service {
    List<DogDto> getAllDogs();

    Optional<DogDto> getOne(Long id);

    DogDto createDog(DogDto dog);

    void delete(Long id);

    DogDto replace(Long id, DogDto dogDto);

    DogDto update(Long id, DogBreedDto dogBreedDto);

    List<DogDto> getAllByName(String name);
}
