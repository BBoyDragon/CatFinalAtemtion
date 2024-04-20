package org.example.ServiceAbstractions;

import org.example.DTOSystem.CatDTO;
import org.example.Models.Cat;

public interface JPACatService {
    public CatDTO AddNewCat(CatDTO catDTO );
    public void MakeCatsBeFriends(String firstOwner, String firstCat, String secondOwner, String secondCat);
    public CatDTO GetCat(String firstOwner, String firstCat);
    public void DeleteCat(String firstOwner, String firstCat);
    public CatDTO UpdateCat(String ownerName,CatDTO catDTO);
}
