package com.example.webservicelab.mappers;

import com.example.webservicelab.dtos.DogDto;
import com.example.webservicelab.entities.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DogMapper {
    public DogDto mapp(Dog dog) {
        return new DogDto(dog.getId(), dog.getName(), dog.getBreed());
    }

    public Dog mapp(DogDto dogDto) {
        return new Dog(dogDto.getId(), dogDto.getName(), dogDto.getBreed());
    }

    public Optional<DogDto> mapp(Optional<Dog> optionalDog) {
        if (optionalDog.isEmpty())
            return Optional.empty();
        return Optional.of(mapp(optionalDog.get()));
    }

    public List<DogDto> mapp(List<Dog> all) {
        return all
                .stream()
                .map(this::mapp)
                .collect(Collectors.toList());
    }
}
