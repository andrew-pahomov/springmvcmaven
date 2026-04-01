package ru.otus.qa.stub.springmvc.controller;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.qa.stub.springmvc.exception.NotFoundException;
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorMessage handleException(NotFoundException exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AnimalModel> gatAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping(path = "/{id}")
    public AnimalModel getAnimalById(@PathVariable("id") Long id) {
        return animalService.getAnimalById(id);
    }

    @PostMapping
    public AnimalModel addAnimal(@RequestBody AnimalModel animalModel) {
        return animalService.addAnimal(animalModel);
    }

    @PutMapping(path = "/{id}")
    public AnimalModel updateAnimalById(@PathVariable("id") Long id, @RequestParam("nickName") String nickName,
                                        @RequestParam("type") String type) {
        return animalService.updateAnimalById(id, nickName, type);
    }

    @DeleteMapping(path = "/{id}")
    public void removeAnimalById(@PathVariable("id") Long id) {
        animalService.deleteAnimalById(id);
    }

}