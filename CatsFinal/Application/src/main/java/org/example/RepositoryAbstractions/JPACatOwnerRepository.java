package org.example.RepositoryAbstractions;

import org.example.Models.CatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPACatOwnerRepository extends JpaRepository<CatOwner, Long> {

   public CatOwner findCatOwnerByName(String Name);
   public void deleteByName(String Name);
}
