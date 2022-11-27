package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.entities.Projet;
import be.condorcet.projetapi3.services.IntefEmployeService;
import be.condorcet.projetapi3.services.InterfProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projets")
public class RestProjet {
    @Autowired
    private InterfProjetService projetServiceImpl;
    @Autowired
    private IntefEmployeService employeServiceImpl;

    //-------------------Retrouver le projet correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Projet> getProjet(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche du projet n° " + id);
        Projet pjr = projetServiceImpl.read(id);
        return new ResponseEntity<>(pjr, HttpStatus.OK);
    }

    //-------------------Retrouver le projet correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/idemploye={id}", method = RequestMethod.GET)
    public ResponseEntity<List<Projet>> getProjetEmploye(@PathVariable(value = "id") int id_employe)  throws Exception{
        System.out.println("recherche des projets de l'employe d'id " + id_employe);
        Employe emp = employeServiceImpl.read(id_employe);
        List<Projet> lep = projetServiceImpl.getProjets(emp);
        return new ResponseEntity<>(lep, HttpStatus.OK);
    }

    //-------------------Créer un projet--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Projet> createProjet(@RequestBody Projet pjr) throws Exception {
        System.out.println("Création du projet de l'employe" + pjr.getEmploye());
        projetServiceImpl.create(pjr);
        return new ResponseEntity<>(pjr, HttpStatus.OK);
    }

    //-------------------Mettre à jour un projet d'un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Projet> majProjet(@PathVariable(value = "id") int id,@RequestBody Projet nouvpjr) throws Exception{
        System.out.println("maj du projet n° " + id);
        nouvpjr.setId_projet(id);
        Projet pjtact = projetServiceImpl.update(nouvpjr);
        return new ResponseEntity<>(pjtact, HttpStatus.OK);
    }

    //-------------------Effacer un projet d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/Idprojet={id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProjet(@PathVariable(value = "id") int id_projet) throws Exception{
        System.out.println("effacement du projet n°" + id_projet);
        Projet pjr = projetServiceImpl.read(id_projet);
        projetServiceImpl.delete(pjr);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver toutes les projets --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Projet>> listEmploye() throws Exception{
        System.out.println("recherche de tous les projets");
        return new ResponseEntity<>(projetServiceImpl.all(), HttpStatus.OK);
    }

    //-------------------Retrouver tout les projets paginées--------------------------------------------------------
    @RequestMapping(value =  "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Projet>> listProjet(Pageable pageable) throws Exception{
        System.out.println("recherche de toutes les commandes");
        return new ResponseEntity<>(projetServiceImpl.allp(pageable), HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
