package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.entities.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjetServiceStub implements InterfProjetService{
    @Override
    public List<Projet> getProjets(Employe emp) {
        List<Projet> lpjr = new ArrayList<>();
        lpjr.add(new Projet(1,"Titre1", Date.valueOf("2020-12-10"),Date.valueOf("2022-11-09"),new BigDecimal(6000),emp));
        lpjr.add(new Projet(2,"Titre2", Date.valueOf("2020-09-11"),Date.valueOf("2024-01-08"),new BigDecimal(7000),emp));
        return lpjr;
    }




    @Override
    public Projet create(Projet projet) throws Exception {
        projet.setId_projet(1);
        return projet;
    }

    @Override
    public Projet read(Integer id) throws Exception {
        return new Projet(id,"TitreTest",Date.valueOf("2022-09-10"),Date.valueOf("2040-01-03"),new BigDecimal(9000),
                new Employe(1,"MatriculeTest","NomTest","PrenomTest","TelTest","MailTest",null));
    }

    @Override
    public Projet update(Projet projet) throws Exception {
        return projet;
    }

    @Override
    public void delete(Projet projet) throws Exception {

    }

    @Override
    public List<Projet> all() throws Exception {
        List<Projet> lep = new ArrayList<>();
        lep.add(new Projet(1,"Titre1",Date.valueOf("2022-12-12"),Date.valueOf("2025-12-09"),new BigDecimal(80254),new Employe(1,"MatriculeTest1","NomTest1","PrenomTest1","TelTest1","MailTest1",null)));
        lep.add(new Projet(2,"Titre2",Date.valueOf("2027-09-12"),Date.valueOf("2045-12-10"),new BigDecimal(90025),new Employe(2,"MatriculeTest2","NomTest2","PrenomTest2","TelTest2","MailTest2",null)));
        return lep;
    }

    @Override
    public Page<Projet> allp(Pageable pageable) throws Exception {
        return null;
    }
    @Override
    public Projet readtitre(String titre) {
        Projet pjr = new Projet(titre);
        pjr.getId_projet();
        return pjr;
    }

    public Employe read(String nom, String prenom, String telephone) {
        Employe emp = new Employe(nom,prenom,telephone);
        emp.getId_employe();
        return emp;
    }

}
