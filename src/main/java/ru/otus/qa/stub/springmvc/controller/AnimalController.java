package ru.otus.qa.stub.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.qa.stub.springmvc.model.AnimalModel;
import ru.otus.qa.stub.springmvc.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping(path = "/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AnimalModel> gatAllAnimals(){
        return animalService.getAllAnimals();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AnimalModel> getAnimalById(@PathVariable("id") Long id){
        AnimalModel animalModel = animalService.getAnimalById(id);
        return new ResponseEntity<>(animalModel, animalModel != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public AnimalModel addAnimal(@RequestBody AnimalModel animalModel){
        return animalService.addAnimal(animalModel);
    }

    @PutMapping(path = "/{id}")
    public AnimalModel updateAnimalById(@PathVariable("id") Long id, @RequestParam("nickName") String nickName,
                                        @RequestParam("type") String type) {
        return animalService.updateAnimalById(id, nickName, type);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity removeAnimalById(@PathVariable("id") Long id){
        return new ResponseEntity(animalService.deleteAnimalById(id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
