package org.example.Controllers;

import org.example.DTOSystem.CatDTO;
import org.example.DTOSystem.CatOwnerDTO;
import org.example.Models.Color;
import org.example.ServiceAbstractions.JPACatService;
import org.example.ServiceAbstractions.JPAOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatController {
    @Autowired
    private JPACatService jpaCatService;
    @PostMapping("/user/CatSave")//user
    public CatDTO saveCat(@RequestBody CatDTO catDTO)
    {
        return jpaCatService.AddNewCat(catDTO);
    }

    // Read operation
    @GetMapping("/user/CatFetch")//user
    public CatDTO fetchCatByName(@RequestParam String ownerName, @RequestParam String catName)
    {
        return jpaCatService.GetCat(ownerName, catName);
    }

    // Update operation
    @PutMapping("/admin/CatUpdate")//Admin
    public CatDTO updateCat(@RequestBody CatDTO catDTO, @RequestParam String catOwnerName)
    {
        return jpaCatService.UpdateCat(catOwnerName,catDTO);
    }
    @PutMapping("/user/CatUpdate")//user
    public CatDTO updateMyCat(@RequestBody CatDTO catDTO)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return jpaCatService.UpdateCat(authentication.getName(), catDTO);
    }
    @PutMapping("/user/CatFriends")//user
    public String updateCatFriends(@RequestParam String catOwnerName, @RequestParam String catName, @RequestParam String secondCatOwnerName, @RequestParam String secondCatName)
    {
        jpaCatService.MakeCatsBeFriends(catOwnerName,catName,secondCatOwnerName,secondCatName);
        return "Коты теперь друзья";
    }

    // Delete operation
    @DeleteMapping("/admin/CatDelete")//admin
    public String deleteCatByName(@RequestParam String catOwnerName, @RequestParam String catName)
    {
        jpaCatService.DeleteCat(catOwnerName, catName);
        return "Deleted Successfully";
    }
    @DeleteMapping("/user/CatDelete")//user
    public String deleteMyCatByName(@RequestParam String catName)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        jpaCatService.DeleteCat(authentication.getName(), catName);
        return "Deleted Successfully";
    }

    @GetMapping("/admin/CatColorFetch")
    public List<CatDTO> GetCatsBuyColor(@RequestParam Color color){
        return jpaCatService.GetCatsByColor(color);
    }
    @GetMapping("/user/CatColorFetch")
    public List<CatDTO> GetMyCatsBuyColor(@RequestParam Color color){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return jpaCatService.GetCatsByColorAndUser(color, authentication.getName());
    }

}
