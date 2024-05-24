package org.example.DTOSystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String username;
    private String password;
    private Set<String> roles;
    private Long catOwner_id;
}
