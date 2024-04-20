package org.example.Repository;

import org.example.Models.CatOwner;
import org.example.RepositoryAbstractions.CatOwnerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CatOwnerRepositoryImpl implements CatOwnerRepository {
    SessionFactory sessionFactory;
    Session session;
    public CatOwnerRepositoryImpl(SessionFactory factory, Session session){
        sessionFactory = factory;
        this.session = session;
    }
    @Override
    public void WriteCatOwner(CatOwner catOwner) {
        session.save(catOwner);
    }

    @Override
    public CatOwner ReadCatOwner(String ownerName) {
        var result = session.createQuery("select owner from CatOwner owner where owner.name = :ownerName", CatOwner.class)
                .setParameter("ownerName", ownerName)
                .getSingleResult();
        return result;
    }
}
