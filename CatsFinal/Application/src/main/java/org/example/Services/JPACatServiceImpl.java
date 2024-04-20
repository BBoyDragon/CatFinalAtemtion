package org.example.Services;

import org.example.DTOSystem.CatDTO;
import org.example.DTOSystem.CatDTOConvertor;
import org.example.Models.Cat;
import org.example.Models.CatOwner;
import org.example.Models.Color;
import org.example.RepositoryAbstractions.JPACatOwnerRepository;
import org.example.RepositoryAbstractions.JPACatRepository;
import org.example.ServiceAbstractions.JPACatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JPACatServiceImpl implements JPACatService {

    private final JPACatOwnerRepository jpaCatOwnerRepository;
    private final JPACatRepository jpaCatRepository;
    private CatDTOConvertor catDTOConvertor;

    @Autowired
    public JPACatServiceImpl(JPACatOwnerRepository jpaCatOwnerRepository, JPACatRepository jpaCatRepository) {
        this.jpaCatOwnerRepository = jpaCatOwnerRepository;
        this.jpaCatRepository = jpaCatRepository;
    }

    @Override
    public CatDTO AddNewCat(CatDTO catDTO) {
        CatOwner owner = jpaCatOwnerRepository.getReferenceById(catDTO.getCatOwner_id());
        Cat cat  = new Cat(catDTO.getName(),catDTO.getBirthday(),catDTO.getColor(),owner);
        owner.getCats().add(cat);
        jpaCatOwnerRepository.save(owner);
        jpaCatRepository.save(cat);
        return catDTO;
    }

    @Override
    public void MakeCatsBeFriends(String firstOwner, String firstCat, String secondOwner, String secondCat) {
        Cat cat1 = jpaCatRepository.findByCatOwnerNameAndName(firstOwner,firstCat);
        Cat cat2 = jpaCatRepository.findByCatOwnerNameAndName(secondOwner,secondCat);
        cat1.getCatFriends().add(cat2);
        cat2.getCatFriends().add(cat1);

        jpaCatRepository.save(cat1);
        jpaCatRepository.save(cat2);
    }

    @Override
    public CatDTO GetCat(String firstOwner, String firstCat) {
        return catDTOConvertor.Convert(jpaCatRepository.findByCatOwnerNameAndName(firstOwner,firstCat));
    }

    @Override
    public void DeleteCat(String firstOwner, String firstCat) {
        jpaCatRepository.deleteByCatOwnerNameAndName(firstOwner,firstCat);
    }

    @Override
    public CatDTO UpdateCat(String ownerName, CatDTO catDTO) {
        Cat cat1 = jpaCatRepository.findByCatOwnerNameAndName(ownerName,catDTO.getName());
        cat1.setBirthday(catDTO.getBirthday());
        cat1.setColor(catDTO.getColor());
        jpaCatRepository.save(cat1);
        return catDTO;
    }
}
