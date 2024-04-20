import org.example.DTOSystem.CatDTOConvertor;
import org.example.DTOSystem.CatOwnerDTOConvertor;
import org.example.Models.Cat;
import org.example.Models.CatOwner;
import org.example.Models.Color;
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
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class ServiceTesting {
    @Test
    public void AddOwner(){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        CatRepository catRepository = mock(CatRepositoryImpl.class);
        CatOwnerRepository catOwnerRepository = mock(CatOwnerRepository.class);

        CatDTOConvertor catDTOConvertor = new CatDTOConvertor();
        CatOwnerDTOConvertor catOwnerDTOConvertor = new CatOwnerDTOConvertor();

        CatService catService = new CatServiceImpl(catRepository, catOwnerRepository,session,catDTOConvertor);
        OwnerService ownerService = new CatOwnerServiceImpl(catOwnerRepository,session, catOwnerDTOConvertor);


        ownerService.AddNewOwner("AnanasAnanas", "2004-11-22");

        verify(catOwnerRepository).WriteCatOwner(any());

    }
    @Test
    public void AddCat(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        CatRepository catRepository = mock(CatRepositoryImpl.class);
        CatOwnerRepository catOwnerRepository = mock(CatOwnerRepository.class);

        CatDTOConvertor catDTOConvertor = new CatDTOConvertor();
        CatOwnerDTOConvertor catOwnerDTOConvertor = new CatOwnerDTOConvertor();

        CatService catService = new CatServiceImpl(catRepository, catOwnerRepository,session,catDTOConvertor);
        OwnerService ownerService = new CatOwnerServiceImpl(catOwnerRepository,session,catOwnerDTOConvertor);

        Mockito.when(catOwnerRepository.ReadCatOwner("AnanasAnanas")).thenReturn(new CatOwner("AnanasAnanas",LocalDate.parse("2004-11-22")));

        catService.AddNewCat("A","2004-05-23","black","AnanasAnanas");

        verify(catRepository).WriteCat(any());

    }

    @Test
    public void MergeCats(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        CatRepository catRepository = mock(CatRepositoryImpl.class);
        CatOwnerRepository catOwnerRepository = mock(CatOwnerRepository.class);

        CatDTOConvertor catDTOConvertor = new CatDTOConvertor();
        CatOwnerDTOConvertor catOwnerDTOConvertor = new CatOwnerDTOConvertor();

        CatService catService = new CatServiceImpl(catRepository, catOwnerRepository,session,catDTOConvertor);
        OwnerService ownerService = new CatOwnerServiceImpl(catOwnerRepository, session, catOwnerDTOConvertor);

        Mockito.when(catRepository.ReadCat("Ananas","a")).thenReturn(new Cat("a",LocalDate.parse("2004-11-22"), Color.black,new CatOwner("Ananas",LocalDate.parse("2004-11-22"))));
        Mockito.when(catRepository.ReadCat("Abricos","b")).thenReturn(new Cat("b",LocalDate.parse("2004-11-22"), Color.black,new CatOwner("Abricos",LocalDate.parse("2004-11-22"))));

        catService.MakeCatsBeFriends("Ananas","a","Abricos","b");
        verify(catRepository, times(2)).WriteCat(any());
        verify(catRepository, times(2)).ReadCat(any(),any());
    }
}
