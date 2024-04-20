package org.example.DTOSystem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.Models.Cat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CatOwnerDTO {
    private Long id;
    private String name;
    private LocalDate birthday;
    private List<Long> cats_id;
}
