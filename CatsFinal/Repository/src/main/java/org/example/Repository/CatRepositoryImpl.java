package org.example.Repository;

import org.example.Models.Cat;
import org.example.Models.CatOwner;
import org.example.RepositoryAbstractions.CatRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CatRepositoryImpl implements CatRepository {
    SessionFactory sessionFactory;
    Session session;
    public CatRepositoryImpl(SessionFactory factory, Session session){
        sessionFactory = factory;
        this.session =session;
    }

    @Override
    public void WriteCat(Cat cat) {
        session.save(cat);
    }

    @Override
    public Cat ReadCat(String ownerName, String catName) {
        String hql = "SELECT c FROM Cat c " +
                "JOIN c.catOwner owner " +
                "WHERE c.name = :catName " +
                "AND owner.name = :ownerName";

        var result = session.createQuery(hql, Cat.class)
                .setParameter("catName", catName)
                .setParameter("ownerName", ownerName)
                .getSingleResult();
        return result;
    }
}
