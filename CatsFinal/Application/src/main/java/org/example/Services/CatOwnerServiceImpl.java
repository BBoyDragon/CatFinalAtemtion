package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.Models.CatOwner;
import org.example.RepositoryAbstractions.CatOwnerRepository;
import org.example.ServiceAbstractions.OwnerService;

import java.time.LocalDate;
@AllArgsConstructor

public class CatOwnerServiceImpl implements OwnerService {
    private CatOwnerRepository catOwnerRepository;
    @Override
    public void AddNewOwner(String name, String birthDay) {
        CatOwner owner = new CatOwner(name, LocalDate.parse(birthDay));
        catOwnerRepository.WriteCatOwner(owner);
    }
}
