package org.example;

import org.example.Models.CatOwner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Создаем сессию Hibernate
        Session session = sessionFactory.openSession();

        // Создаем объект Cat
        CatOwner owner = new CatOwner("ANANAS", LocalDate.parse("2004-05-22"));

        // Начинаем транзакцию
        Transaction transaction = session.beginTransaction();

        session.save(owner);

        // Фиксируем транзакцию
        transaction.commit();

        // Закрываем сессию
        session.close();

        // Закрываем фабрику сессий
        sessionFactory.close();
    }
}