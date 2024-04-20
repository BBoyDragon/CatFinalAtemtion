package org.example;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

    }
}