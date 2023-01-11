package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.entities.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IntefEmployeService extends  InterfService<Employe> {
    public List<Employe> read(String nom);

    Employe read(String nom, String prenom,String telephone);

    Page<Employe> allp(Pageable pageable) throws Exception;

    List<Employe> getEmployes(Projet pjr);
}
