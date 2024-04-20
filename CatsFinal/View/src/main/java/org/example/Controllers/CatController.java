package org.example.Controllers;

import org.example.DTOSystem.CatDTO;
import org.example.DTOSystem.CatOwnerDTO;
import org.example.ServiceAbstractions.JPACatService;
import org.example.ServiceAbstractions.JPAOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CatController {
    @Autowired
    private JPACatService jpaCatService;
    @PostMapping("/Cat")
    public CatDTO saveCat(@RequestBody CatDTO catDTO)
    {
        return jpaCatService.AddNewCat(catDTO);
    }

    // Read operation
    @GetMapping("/Cat")
    public CatDTO fetchCatByName(@RequestParam String ownerName, @RequestParam String catName)
    {
        return jpaCatService.GetCat(ownerName, catName);
    }

    // Update operation
    @PutMapping("/Cat")
    public CatDTO
    updateCat(@RequestBody CatDTO catDTO, @RequestParam String catOwnerName)
    {
        return jpaCatService.UpdateCat(catOwnerName,catDTO);
    }
    @PutMapping("/CatFriends")
    public String updateCatFriends(@RequestParam String catOwnerName, @RequestParam String catName, @RequestParam String secondCatOwnerName, @RequestParam String secondCatName)
    {
        jpaCatService.MakeCatsBeFriends(catOwnerName,catName,secondCatOwnerName,secondCatName);
        return "Коты теперь друзья";
    }

    // Delete operation
    @DeleteMapping("/Cat")
    public String deleteCattByName(@RequestParam String catOwnerName, @RequestParam String catName)
    {
        jpaCatService.DeleteCat(catOwnerName, catName);
        return "Deleted Successfully";
    }

}
