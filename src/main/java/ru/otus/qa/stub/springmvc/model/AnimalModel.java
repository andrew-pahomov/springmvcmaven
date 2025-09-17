package ru.otus.qa.stub.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AnimalModel {
    Long id;
    String nickName;
    String type;
}
