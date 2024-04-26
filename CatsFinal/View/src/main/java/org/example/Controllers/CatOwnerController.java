package org.example.Controllers;

import org.example.DTOSystem.CatOwnerDTO;
import org.example.DTOSystem.UserDTO;
import org.example.ServiceAbstractions.JPAOwnerService;
import org.example.Security.CustomUserDetailsService;
import org.example.ServiceAbstractions.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
public class CatOwnerController {
    @Autowired
    private JPAOwnerService jpaOwnerService;
    @Autowired
    private UserService userService;
    @PostMapping("/CatOwner")
    public CatOwnerDTO saveCatOwner(@RequestBody CatOwnerDTO catOwnerDTO, @RequestParam String password, @RequestParam ArrayList<String> role)
    {
        UserDTO userDTO = new UserDTO(catOwnerDTO.getId(), catOwnerDTO.getName(),password,new HashSet<>(role), catOwnerDTO.getId());
        CatOwnerDTO newCatOwnerDto = jpaOwnerService.AddNewOwner(catOwnerDTO);
        userDTO.setCatOwner_id(newCatOwnerDto.getId());
        userService.SaveUser(userDTO);
        return newCatOwnerDto;
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
    @DeleteMapping("/CatOwner")
    public String deleteCatOwnerByName(@RequestParam String catOwnerName)
    {
        jpaOwnerService.DeleteOwner(catOwnerName);
        return "Deleted Successfully";
    }
}
