package org.example.ServiceAbstractions;

public interface CatService {
    public void AddNewCat(String name, String birthday, String color, String ownerName);
    public void MakeCatsBeFriends(String firstOwner, String firstCat, String secondOwner, String secondCat);
}
