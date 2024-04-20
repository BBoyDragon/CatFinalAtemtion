package org.example.ServiceAbstractions;

import org.example.DTOSystem.CatOwnerDTO;

public interface OwnerService {
    public void AddNewOwner(String name, String birthDay);
    public CatOwnerDTO GetCatOwner(String name);
}
