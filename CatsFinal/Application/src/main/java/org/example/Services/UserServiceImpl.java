package org.example.Services;

import lombok.AllArgsConstructor;
import org.example.DTOSystem.UserDTO;
import org.example.DTOSystem.UserDTOConvertor;
import org.example.Models.User;
import org.example.RepositoryAbstractions.JPACatOwnerRepository;
import org.example.RepositoryAbstractions.UserRepository;
import org.example.Security.CustomUserDetails;
import org.example.ServiceAbstractions.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private JPACatOwnerRepository catOwnerRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO SaveUser(UserDTO userDTO) {
        User user = new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRoles(), catOwnerRepository.getReferenceById(userDTO.getCatOwner_id()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRoles(),user.getCatOwner().getId());
    }
}
