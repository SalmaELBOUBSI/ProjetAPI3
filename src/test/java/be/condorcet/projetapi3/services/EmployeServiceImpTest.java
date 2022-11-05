package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Employe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeServiceImpTest {
    @Autowired
    private EmployeServiceImp employeServiceImp;

    Employe emp = new Employe(550,"matTest","nomTest","prenomTest","TelTest","emailTest");
    @BeforeEach
    void setUp() {
        try{
            employeServiceImp.create(emp);
            System.out.println("création de l'employé : "+emp);
        }
        catch (Exception e){
            System.out.println("erreur de création de l'employé : "+emp +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
           employeServiceImp.delete(emp);
            System.out.println("effacement de l'employé : "+emp);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement de l'employé :"+emp+" erreur : "+e);
        }
    }

    @Test
    void read() {
        try{
            int numemp=emp.getIdemploye();
            Employe emp2=employeServiceImp.read(numemp);
            assertEquals("nomTest",emp2.getNom(),"noms différents "+"nomTest"+"-"+emp.getNom());
            assertEquals("prenomTest",emp2.getPrenom(),"prénoms différents "+"prnomTest"+"-"+emp2.getNom());
            //etc
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,emp.getIdemploye(),"id employé non incrémenté");
        assertEquals("matTest",emp.getMatricule(),"matricule employé non incrémenté");
        assertEquals("nomTest",emp.getNom(),"nom employé non enregistré : "+emp.getNom()+ " au lieu de NomTest");
        assertEquals("prenomTest",emp.getPrenom(),"prénom employé non enregistré : "+emp.getPrenom()+" au lieu de PrenomTest");
    }

    @Test
    void update() {
        try{
            emp.setTelephone("telTest2");
            emp.setEmail("emailTest2");
            //etc
            emp= employeServiceImp.update(emp);
            assertEquals("telTest2",emp.getTelephone(),"telephone différents "+"telTest2-"+emp.getTelephone());
            assertEquals("emailTest2",emp.getEmail(),"email différents "+"emailTest2-"+emp.getEmail());
            //etc
        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            employeServiceImp.delete(emp);
            Assertions.assertThrows(Exception.class, () -> {
                employeServiceImp.read(emp.getIdemploye());
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
            try {
                List<Employe> lemp = employeServiceImp.all();
                assertNotEquals(0,lemp.size(),"la liste ne contient aucun élément");
            }catch (Exception e){
                fail("erreur de recherche de tous les employés "+e);
            }
        }

    @Test
    void creationDoublon(){
        Employe emp2 = new Employe(0,"matTest","nomTest","prenomTest","TelTest","emailTest");
        Assertions.assertThrows(Exception.class, () -> {
            employeServiceImp.create(emp2);
        },"création d'un doublon");
    }
}