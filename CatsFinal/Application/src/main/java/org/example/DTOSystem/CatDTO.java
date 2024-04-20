package org.example.DTOSystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.Models.Color;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class CatDTO {

    private Long id;

    private String name;

    private LocalDate birthday;
    private Color color;
    private Long catOwner_id;
    private List<Long> catFriends_id;
}
