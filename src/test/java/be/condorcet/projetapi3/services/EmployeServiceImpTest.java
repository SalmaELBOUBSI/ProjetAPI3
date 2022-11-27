package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.entities.Projet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeServiceImpTest {
    @Autowired
    private EmployeServiceImpl employeServiceImp;
    @Autowired
    private ProjetServiceImpl projetServiceImp;

    Employe emp = new Employe(null,"matTest9","nomTest","prenomTest","TelTest","emailTest",new ArrayList<>());
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
    void create() {
        assertNotEquals(0,emp.getId_employe(),"id employé non incrémenté");
        assertEquals("matTest9",emp.getMatricule(),"matricule employé non incrémenté");
        assertEquals("nomTest",emp.getNom(),"nom employé non enregistré : "+emp.getNom()+ " au lieu de NomTest");
        assertEquals("prenomTest",emp.getPrenom(),"prénom employé non enregistré : "+emp.getPrenom()+" au lieu de PrenomTest");
    }


    @Test
    void creationDoublon(){
        Employe emp2 = new Employe(null,"matTest5","nomTest","prenomTest","TelTest","emailTest",new ArrayList<>());
        Assertions.assertThrows(Exception.class, () -> {
            employeServiceImp.create(emp2);
        },"création d'un doublon");
    }

    @Test
    void read() {
        try{
            int numemp=emp.getId_employe();
            Employe emp2=employeServiceImp.read(numemp);
            assertEquals("nomTest",emp2.getNom(),"noms différents "+"nomTest"+"-"+emp.getNom());
            assertEquals("prenomTest",emp2.getPrenom(),"prénoms différents "+"prenomTest"+"-"+emp2.getNom());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            emp.setTelephone("telTest2");
            emp.setMail("emailTest2");

            emp= employeServiceImp.update(emp);
            assertEquals("telTest2",emp.getTelephone(),"telephone différents "+"telTest2-"+emp.getTelephone());
            assertEquals("emailTest2",emp.getMail(),"email différents "+"emailTest2-"+emp.getMail());

        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            employeServiceImp.delete(emp);
            Assertions.assertThrows(Exception.class, () -> {employeServiceImp.read(emp.getId_employe());},"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void delAvecProj(){
        try{
            Projet pj = new Projet(null,"web", Date.valueOf("2020-12-11"),Date.valueOf("2022-11-10"),new BigDecimal(1000),emp);
            projetServiceImp.create(pj);
            Assertions.assertThrows(Exception.class, () -> {
                employeServiceImp.delete(emp);
            },"effacement réalisé malgré le projet lie");
            projetServiceImp.delete(pj);
        }catch (Exception e){
            fail("erreur de création "+ e);
        }
    }

    @Test
    void rechNom() {
        List<Employe> lemp = employeServiceImp.read("nomTest");
        boolean trouve=false;
        for(Employe emp : lemp){
            if(emp.getNom().equals("nomTest")) trouve=true;
            else fail("un record ne correspond pas , nom = "+emp.getNom());
        }
        assertTrue(trouve,"record non trouvé dans la liste");
    }

    @Test
    void all() {
            try {
                List<Employe> lemp = employeServiceImp.all();
                assertNotEquals(0,lemp.size(),"la liste ne contient aucun élément");
            }catch (Exception e){
                fail("erreur de recherche de tous les employés " + e);
            }
        }


}