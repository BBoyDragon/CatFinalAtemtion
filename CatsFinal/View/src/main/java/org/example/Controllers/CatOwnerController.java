package org.example.Controllers;

import org.example.DTOSystem.CatOwnerDTO;
import org.example.ServiceAbstractions.JPAOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CatOwnerController {
    @Autowired
    private JPAOwnerService jpaOwnerService;
    @PostMapping("/CatOwner")
    public CatOwnerDTO saveCatOwner(@RequestBody CatOwnerDTO catOwnerDTO)
    {
        return jpaOwnerService.AddNewOwner(catOwnerDTO);
    }

    // Read operation
    @GetMapping("/CatOwner")
    public CatOwnerDTO fetchCatOwner(@RequestParam String ownerName)
    {
        return jpaOwnerService.GetOwner(ownerName);
    }

    // Update operation
    @PutMapping("/CatOwner")
    public CatOwnerDTO
    updateCatOwner(@RequestBody CatOwnerDTO catOwnerDTO, @RequestParam String catOwnerName)
    {
        return jpaOwnerService.UpdateUser(catOwnerName,catOwnerDTO);
    }

    // Delete operation
    @DeleteMapping("/CatOwner/")
    public String deleteCatOwnerByName(@RequestParam String catOwnerName)
    {
        jpaOwnerService.DeleteOwner(catOwnerName);
        return "Deleted Successfully";
    }
}
