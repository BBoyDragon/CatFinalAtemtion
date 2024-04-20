package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTOSystem.CatDTO;
import org.example.DTOSystem.CatDTOConvertor;
import org.example.Models.Cat;
import org.example.Models.CatOwner;
import org.example.Models.Color;
import org.example.RepositoryAbstractions.CatOwnerRepository;
import org.example.RepositoryAbstractions.CatRepository;
import org.example.ServiceAbstractions.CatService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
@AllArgsConstructor
public class CatServiceImpl implements CatService {


    private CatRepository catRepository;
    private CatOwnerRepository catOwnerRepository;
    private Session session;
    private CatDTOConvertor catDTOConvertor;
    @Override
    public void AddNewCat(String name, String birthday, String color, String ownerName) {
        Transaction transaction = session.beginTransaction();
        CatOwner owner = catOwnerRepository.ReadCatOwner(ownerName);
        Cat cat  = new Cat(name, LocalDate.parse(birthday),Color.valueOf(color),owner);
        owner.getCats().add(cat);
        catOwnerRepository.WriteCatOwner(owner);
        catRepository.WriteCat(cat);
        transaction.commit();
    }

    @Override
    public void MakeCatsBeFriends(String firstOwner, String firstCat, String secondOwner, String secondCat) {
        Transaction transaction = session.beginTransaction();
        Cat cat1 = catRepository.ReadCat(firstOwner,firstCat);
        Cat cat2 = catRepository.ReadCat(secondOwner,secondCat);
        cat1.getCatFriends().add(cat2);
        cat2.getCatFriends().add(cat1);

        catRepository.WriteCat(cat1);
        catRepository.WriteCat(cat2);
        transaction.commit();
    }

    @Override
    public CatDTO GetCat(String ownerName, String name) {
        Transaction transaction = session.beginTransaction();
        Cat cat1 = catRepository.ReadCat(ownerName,name);
        transaction.commit();
        return catDTOConvertor.Convert(cat1);
    }
}
