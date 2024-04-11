package org.example.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "owners")
public class CatOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

    @OneToMany(cascade= CascadeType.ALL)
    @Column(name = "Cat")
    private List<Cat> cats;
    public CatOwner(String name, LocalDate birthday){
        this.name=name;
        this.birthday=birthday;
        cats = new ArrayList<>();
    }

    public CatOwner() {

    }
}
