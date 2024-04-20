package org.example.DTOSystem;

import org.example.Models.Cat;
import org.example.Models.CatOwner;

import java.util.stream.Collectors;

public class CatOwnerDTOConvertor {
    public CatOwnerDTO Convert(CatOwner catOwner){
        return new CatOwnerDTO(catOwner.getId(), catOwner.getName(),catOwner.getBirthday(),catOwner.getCats().stream().map(Cat::getId).collect(Collectors.toList()));
    }
}
