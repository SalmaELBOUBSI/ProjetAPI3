package be.condorcet.projetapi3.repository;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe,Integer> {
    public List<Employe> findEmployesByNomLike(String nom);

    public List<Employe> findByNomLikeAndPrenomLikeAndTelephoneLike(String nom, String prenom, String telephone);

    Employe findClientsByNomAndPrenomAndTelephone(String nom, String prenom, String telephone);

    List<Employe> findEmployeeByTitre(Projet pjr);
}
