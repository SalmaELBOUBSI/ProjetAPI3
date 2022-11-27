package be.condorcet.projetapi3.webservices;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.services.IntefEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employes")
public class RestEmploye {
    @Autowired
    private IntefEmployeService employeServiceImpl;

    //-------------------Retrouver l'employe correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employe> getEmploye(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("recherche de l'employe d' id " + id);
        Employe employe = employeServiceImpl.read(id);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    //-------------------Retrouver les employes portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Employe>> listEmployesNom(@PathVariable(value = "nom") String nom) throws Exception {
        System.out.println("recherche de " + nom);
        List<Employe> employes;
        employes = employeServiceImpl.read(nom);
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }
    //-------------------Retrouver un employe correspondant à un triplet (nom,prénom,tel) unique donné--------------------------------------------------------
    @RequestMapping(value = "/{nom}/{prenom}/{telephone}", method = RequestMethod.GET)
    public ResponseEntity<Employe> getEmployeUnique(@PathVariable(value = "nom") String nom, @PathVariable(value = "prenom") String prenom,@PathVariable(value = "telephone") String telephone)  throws Exception{
        System.out.println("recherche de l'employe "+nom+" "+prenom+" "+telephone+"");
        Employe employe = employeServiceImpl.read(nom,prenom,telephone);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    //-------------------Retrouver tous les employes --------------------------------------------------------
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Employe>> listEmploye() throws Exception{
        System.out.println("recherche de tous les employes");
        return new ResponseEntity<>(employeServiceImpl.all(), HttpStatus.OK);
    }

    //-------------------Créer un employe --------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Employe> createClient(@RequestBody Employe employe) throws Exception {
        System.out.println("Création de l'employe " + employe.getNom());
        employeServiceImpl.create(employe);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }
    //-------------------Mettre à jour un employe d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employe> majEmploye(@PathVariable(value = "id") int id_employe, @RequestBody Employe nouvemp) throws Exception{
        System.out.println("maj de l'employe id = " + id_employe);
        nouvemp.setId_employe(id_employe);
        Employe empact = employeServiceImpl.update(nouvemp);
        return new ResponseEntity<>(empact, HttpStatus.OK);
    }
    //-------------------Effacer un employe d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE
    )
    public ResponseEntity<Void> deleteEmploye(@PathVariable(value = "id") int id_employe) throws Exception{
        System.out.println("effacement de l'employe d'id " + id_employe);
        Employe employe = employeServiceImpl.read(id_employe);
        employeServiceImpl.delete(employe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }

    @RequestMapping(value = "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Employe>> listEmploye(Pageable pageable) throws Exception{
        System.out.println("recherche de tous les employes");
        return new ResponseEntity<>(employeServiceImpl.allp(pageable), HttpStatus.OK);
    }

}
