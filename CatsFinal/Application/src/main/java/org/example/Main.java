package org.example;

import org.example.Models.Cat;
import org.example.Models.CatOwner;
import org.example.ServiceAbstractions.JPACatService;
import org.example.ServiceAbstractions.JPAOwnerService;
import org.example.Services.JPACatOwnerServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("ananas"));
        //$2a$10$iahdSK9mBHrer9vjEbJnuuD7Z1jDXytfqF59F1n5nu1TFvYmo15IC
        //jpaCatOwnerService.AddNewOwner("ananas","2004-11-22");
        //CatOwner owner = jpaCatOwnerService.GetOwner("AnanasAnanas");
        //Cat cat = jpaCatService.GetCat("AnanasAnanas","ananas");
        //System.out.print(cat.getCatFriends().get(1).getName());

    }
}