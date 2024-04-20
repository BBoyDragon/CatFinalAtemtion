package org.example.RepositoryAbstractions;

import org.example.Models.Cat;
import org.example.Models.CatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPACatRepository extends JpaRepository<Cat, Long> {
    public Cat findByCatOwnerNameAndName(String owner, String cat);
    public void deleteByCatOwnerNameAndName(String owner, String cat);
}
