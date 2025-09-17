package ru.otus.qa.stub.springmvc.service;

import org.springframework.stereotype.Service;
import ru.otus.qa.stub.springmvc.model.AnimalModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@Service
public class AnimalService {
    private final List<AnimalModel> animals = new ArrayList<>();

    public AnimalService() {
        LongStream.range(0, 15).forEach(i -> animals.add(new AnimalModel(i, "Кличка " + i, "Вид " + i)));
    }

    public List<AnimalModel> getAllAnimals() {
        return animals;
    }

    public AnimalModel getAnimalById(Long id){
        return animals.stream().filter(animal -> animal.getId().equals(id)).findFirst().orElse(null);
    }

    public boolean deleteAnimalById(Long id){
        return animals.remove(getAnimalById(id));
    }

    public AnimalModel addAnimal(AnimalModel animalModel){
        animals.stream().map(AnimalModel::getId).max(Long::compareTo).ifPresent(id ->{
            animalModel.setId(id + 1);
            animals.add(animalModel);
        });
        return animalModel;
    }

    public AnimalModel updateAnimalById(Long id, String nickName, String type){
        AnimalModel animalModel = getAnimalById(id);
        animalModel.setNickName(nickName);
        animalModel.setType(type);
        return animalModel;
    }
}
