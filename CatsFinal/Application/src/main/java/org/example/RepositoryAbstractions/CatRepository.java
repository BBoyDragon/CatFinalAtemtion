package org.example.RepositoryAbstractions;

import org.example.Models.Cat;

public interface CatRepository {

    public void WriteCat(Cat cat);
    public Cat ReadCat(String ownerName, String catName);
}
