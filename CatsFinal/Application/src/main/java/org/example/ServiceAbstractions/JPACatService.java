package org.example.ServiceAbstractions;

import org.example.DTOSystem.CatDTO;
import org.example.Models.Cat;
import org.example.Models.Color;

import java.util.List;

public interface JPACatService {
    public CatDTO AddNewCat(CatDTO catDTO );
    public void MakeCatsBeFriends(String firstOwner, String firstCat, String secondOwner, String secondCat);
    public CatDTO GetCat(String firstOwner, String firstCat);
    public void DeleteCat(String firstOwner, String firstCat);
    public CatDTO UpdateCat(String ownerName,CatDTO catDTO);
    public List<CatDTO> GetCatsByColor(Color color);
    public List<CatDTO> GetCatsByColorAndUser(Color color, String ownerName);
}
