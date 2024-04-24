package org.example.DTOSystem;

import org.example.Models.User;

public class UserDTOConvertor {
    public UserDTO Convert(User user){
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRoles(),user.getCatOwner().getId());
    }
}
