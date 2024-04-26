package org.example.RepositoryAbstractions;

import org.example.Models.Cat;
import org.example.Models.CatOwner;
import org.example.Models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface JPACatRepository extends JpaRepository<Cat, Long> {
    public Cat findByCatOwnerNameAndName(String owner, String cat);
    public void deleteByCatOwnerNameAndName(String owner, String cat);
    public ArrayList<Cat> findCatsByColor(Color color);
    public ArrayList<Cat> findCatsByColorAndCatOwnerName(Color color, String owner);

}
