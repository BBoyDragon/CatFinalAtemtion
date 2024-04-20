package org.example.Services;

import org.example.DTOSystem.CatOwnerDTO;
import org.example.DTOSystem.CatOwnerDTOConvertor;
import org.example.Models.Cat;
import org.example.Models.CatOwner;
import org.example.RepositoryAbstractions.JPACatOwnerRepository;
import org.example.ServiceAbstractions.JPAOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JPACatOwnerServiceImpl implements JPAOwnerService {

    private final JPACatOwnerRepository jpaCatOwnerRepository;
    private CatOwnerDTOConvertor catOwnerDTOConvertor;
    @Autowired
    public JPACatOwnerServiceImpl(JPACatOwnerRepository jpaCatOwnerRepository){

        this.jpaCatOwnerRepository = jpaCatOwnerRepository;
        catOwnerDTOConvertor = new CatOwnerDTOConvertor();
    }
    @Override
    public CatOwnerDTO AddNewOwner(CatOwnerDTO catOwnerDTO) {
        CatOwner owner = new CatOwner(catOwnerDTO.getName(), catOwnerDTO.getBirthday());
        jpaCatOwnerRepository.save(owner);
        return catOwnerDTO;
    }

    @Override
    public CatOwnerDTO GetOwner(String name) {
       return catOwnerDTOConvertor.Convert(jpaCatOwnerRepository.findCatOwnerByName(name));
    }

    @Override
    public void DeleteOwner(String name) {
        jpaCatOwnerRepository.deleteByName(name);
    }

    @Override
    public CatOwnerDTO UpdateUser(String name, CatOwnerDTO catOwnerDTO) {
       CatOwner owner = jpaCatOwnerRepository.findCatOwnerByName(name);
       owner.setBirthday(catOwnerDTO.getBirthday());
       jpaCatOwnerRepository.save(owner);
       return catOwnerDTO;
    }
}
