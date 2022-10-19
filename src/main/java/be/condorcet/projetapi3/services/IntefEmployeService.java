package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Employe;

import java.util.List;

public interface IntefEmployeService extends  InterfService<Employe> {
    public List<Employe> read(String nom);

}
