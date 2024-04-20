package org.example;

import org.example.DTOSystem.CatDTOConvertor;
import org.example.DTOSystem.CatOwnerDTOConvertor;
import org.example.Repository.CatOwnerRepositoryImpl;
import org.example.Repository.CatRepositoryImpl;
import org.example.RepositoryAbstractions.CatOwnerRepository;
import org.example.RepositoryAbstractions.CatRepository;
import org.example.ServiceAbstractions.CatService;
import org.example.ServiceAbstractions.OwnerService;
import org.example.Services.CatOwnerServiceImpl;
import org.example.Services.CatServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        CatRepository catRepository = new CatRepositoryImpl(sessionFactory, session);
        CatOwnerRepository catOwnerRepository = new CatOwnerRepositoryImpl(sessionFactory,session);

        CatDTOConvertor catDTOConvertor = new CatDTOConvertor();
        CatOwnerDTOConvertor catOwnerDTOConvertor = new CatOwnerDTOConvertor();

        CatService catService = new CatServiceImpl(catRepository, catOwnerRepository, session,catDTOConvertor);
        OwnerService ownerService = new CatOwnerServiceImpl(catOwnerRepository,session, catOwnerDTOConvertor);

        ownerService.AddNewOwner("Abr", "2004-11-22");
        ownerService.AddNewOwner("Ana", "2004-11-22");
        catService.AddNewCat("abr","2004-05-23","black","Abr");
        catService.AddNewCat("ana","2004-05-23","black","Ana");
        //catService.MakeCatsBeFriends("AnanasAnanas","ananas","AbricosAbricos","abricos");

        session.close();
        sessionFactory.close();
    }
}