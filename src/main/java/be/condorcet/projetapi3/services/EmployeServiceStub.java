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

public class EmployeServiceStub implements IntefEmployeService{


    @Override
    public List<Employe> read(String nom) {
        List<Employe> lemp = new ArrayList<>();
        lemp.add(new Employe(1,"12",nom,"PrenomTest","TelTest","MailTest",null));
        lemp.add(new Employe(2,"13",nom,"PrenomTest2","TelTest2","MailTest2", null));
        return lemp;
    }

    @Override
    public Employe read(String nom, String prenom, String telephone) {
        Employe emp = new Employe(nom,prenom,telephone);
        emp.getId_employe();
        return emp;
    }

    @Override
    public Employe create(Employe employe) throws Exception {
        employe.setId_employe(1);
        return employe;
    }

    @Override
    public Employe read(Integer id) throws Exception {
        Employe emp = new Employe(id,"12","NomTest","PrenomTest","TelTest","MailTest",new ArrayList<>());
        emp.getProjets().add(new Projet(1,"TitreTest", Date.valueOf("2020-12-11"),Date.valueOf("2022-11-10"),new BigDecimal(1000),emp));
        return emp;
    }

    @Override
    public Employe update(Employe employe) throws Exception {
        return employe;
    }

    @Override
    public void delete(Employe employe) throws Exception {
    }

    @Override
    public List<Employe> all() throws Exception {
        List<Employe>lemp = new ArrayList<>();
        lemp.add(new Employe(1,"12","NomTest","PrnomTest","TelTest","MailTest",null));
        lemp.add(new Employe(2,"13","NomTest2","PrnomTest2","TelTest2","MailTest2",null));

        return lemp;
    }

    @Override
    public Page<Employe> allp(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public List<Employe> getEmployes(Projet pjr) {
        List<Employe> lemp = new ArrayList<>();
        lemp.add(new Employe("A23","Jules","Dup"));
        lemp.add(new Employe("A56","Marie","Lenoi"));
        return lemp;
    }

}
