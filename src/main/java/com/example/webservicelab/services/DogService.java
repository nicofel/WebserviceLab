package com.example.webservicelab.services;

import com.example.webservicelab.dtos.DogBreedDto;
import com.example.webservicelab.dtos.DogDto;
import com.example.webservicelab.entities.Dog;
import com.example.webservicelab.mappers.DogMapper;
import com.example.webservicelab.repositories.DogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DogService implements com.example.webservicelab.services.Service {

    private final DogMapper dogMapper;
    private DogRepository dogRepository;

    public DogService(DogRepository dogRepository, DogMapper dogMapper){
        this.dogRepository = dogRepository;
        this.dogMapper = dogMapper;
    }

    @Override
    public List<DogDto> getAllDogs(){
        return dogMapper.mapp(dogRepository.findAll());
    }

    @Override
    public Optional<DogDto> getOne(Long id) {
        return dogMapper.mapp(dogRepository.findById(id));
    }

    @Override
    public DogDto createDog(DogDto dog){
        if(dog.getName().isEmpty())
            throw new RuntimeException();
        return dogMapper.mapp(dogRepository.save(dogMapper.mapp(dog)));
    }

    @Override
    public void delete(Long id) {
        dogRepository.deleteById(id);
    }

    @Override
    public DogDto replace(Long id, DogDto dogDto) {
        Optional<Dog> dog = dogRepository.findById(id);
        if(dog.isPresent())
        {
            Dog updatedDog = dog.get();
            updatedDog.setName(dogDto.getName());
            updatedDog.setBreed(dogDto.getBreed());
            return dogMapper.mapp(dogRepository.save(updatedDog));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id "
                    + id + " not found");
    }

    @Override
    public DogDto update(Long id, DogBreedDto dogBreedDto) {
        Optional<Dog> dog = dogRepository.findById(id);
        if(dog.isPresent())
        {
            Dog updatedDog = dog.get();
            if(dogBreedDto.breed !=null)
                updatedDog.setBreed(dogBreedDto.breed);
            return dogMapper.mapp(dogRepository.save(updatedDog));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id "
                    + id + " not found");

    }

    @Override
    public List<DogDto> getAllByName(String name) {
        return dogMapper.mapp(dogRepository.findAllByName(name));
    }
}
