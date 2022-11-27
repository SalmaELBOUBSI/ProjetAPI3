package be.condorcet.projetapi3.repository;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Integer> {
    public List<Projet> findProjetByEmploye(Employe emp);
    public List<Projet> findById(int id_projet);

}
