package org.example.ServiceAbstractions;

import org.example.DTOSystem.CatDTO;

public interface CatService {
    public void AddNewCat(String name, String birthday, String color, String ownerName);
    public void MakeCatsBeFriends(String firstOwner, String firstCat, String secondOwner, String secondCat);

    public CatDTO GetCat(String ownerName, String name);
}
