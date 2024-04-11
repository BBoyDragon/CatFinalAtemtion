package org.example.RepositoryAbstractions;

import org.example.Models.CatOwner;

public interface CatOwnerRepository {
    public void WriteCatOwner(CatOwner catOwner);
    public CatOwner ReadCatOwner(String ownerName);
}
