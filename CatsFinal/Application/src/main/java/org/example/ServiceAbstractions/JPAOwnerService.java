package org.example.ServiceAbstractions;

import org.example.DTOSystem.CatOwnerDTO;
import org.example.Models.CatOwner;

public interface JPAOwnerService {
    public CatOwnerDTO AddNewOwner(CatOwnerDTO catOwnerDTO);
    public CatOwnerDTO GetOwner(String name);
    public void DeleteOwner(String name);
    public CatOwnerDTO UpdateUser(String name,CatOwnerDTO catOwnerDTO);
}
