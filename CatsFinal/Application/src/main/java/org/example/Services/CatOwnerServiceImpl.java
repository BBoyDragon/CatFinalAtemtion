package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTOSystem.CatOwnerDTO;
import org.example.DTOSystem.CatOwnerDTOConvertor;
import org.example.Models.CatOwner;
import org.example.RepositoryAbstractions.CatOwnerRepository;
import org.example.ServiceAbstractions.OwnerService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
@AllArgsConstructor

public class CatOwnerServiceImpl implements OwnerService {
    private CatOwnerRepository catOwnerRepository;
    private Session session;
    private CatOwnerDTOConvertor catOwnerDTOConvertor;
    @Override
    public void AddNewOwner(String name, String birthDay) {
        Transaction transaction = session.beginTransaction();
        CatOwner owner = new CatOwner(name, LocalDate.parse(birthDay));
        catOwnerRepository.WriteCatOwner(owner);
        transaction.commit();
    }

    @Override
    public CatOwnerDTO GetCatOwner(String name) {
        Transaction transaction = session.beginTransaction();
        CatOwner owner = catOwnerRepository.ReadCatOwner(name);
        transaction.commit();
        return catOwnerDTOConvertor.Convert(owner);
    }

}
