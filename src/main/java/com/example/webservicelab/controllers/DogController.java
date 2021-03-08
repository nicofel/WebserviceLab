package com.example.webservicelab.controllers;

import com.example.webservicelab.dtos.DogBreedDto;
import com.example.webservicelab.dtos.DogDto;
import com.example.webservicelab.entities.Dog;
import com.example.webservicelab.services.DogService;
import com.example.webservicelab.services.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
public class DogController {

    private com.example.webservicelab.services.Service Service;

    public DogController(Service Service) {
        this.Service = Service;
    }

    //Get requests to get all info from dogs
    @GetMapping("/dogs")
    public List<DogDto> all() {
        return Service.getAllDogs();
    }

    @GetMapping("/dogs/search/{name}")
    public List<DogDto> name(@PathVariable String name){
        return Service.getAllByName(name);
    }

    //Get request to find dogs by ID
    @GetMapping("/dogs/{id}")
    public DogDto one(@PathVariable Long id) {
         return Service.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id "
                + id + " not found"));

    }
    //Add dogs to my database
    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public DogDto create(@RequestBody DogDto dog) {
        return Service.createDog(dog);
    }

    //Delete a dog with ID number
    @DeleteMapping("/dogs/{id}")
    public void delete(@PathVariable Long id){
        Service.delete(id);
}

//Update everything
@PutMapping("/dogs/{id}")
    public DogDto replace(@RequestBody DogDto dogDto, @PathVariable Long id){
        return Service.replace(id, dogDto);
}
    //Update one thing
    @PatchMapping("/dogs/{id}")
    public DogDto update(@RequestBody DogBreedDto dogBreedDto, @PathVariable Long id){
        return Service.update(id, dogBreedDto);
    }


}

