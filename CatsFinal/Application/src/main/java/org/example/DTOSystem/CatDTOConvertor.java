package org.example.DTOSystem;

import org.example.Models.Cat;

import java.util.stream.Collectors;

public class CatDTOConvertor {
    public CatDTO Convert(Cat cat){
        return new CatDTO(cat.getId(),cat.getName(),cat.getBirthday(),cat.getColor(),cat.getCatOwner().getId(),cat.getCatFriends().stream().map(Cat::getId).collect(Collectors.toList()));
    }
}
