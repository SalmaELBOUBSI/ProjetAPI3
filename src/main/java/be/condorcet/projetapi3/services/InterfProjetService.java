package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.entities.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterfProjetService extends InterfService<Projet> {
    public List<Projet> getProjets(Employe emp);

    Projet readtitre(String titre);

}
