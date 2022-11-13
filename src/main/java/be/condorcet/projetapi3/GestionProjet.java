package be.condorcet.projetapi3;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.entities.Projet;
import be.condorcet.projetapi3.services.EmployeServiceImpl;
import be.condorcet.projetapi3.services.IntefEmployeService;
import be.condorcet.projetapi3.services.InterfProjetService;

import be.condorcet.projetapi3.services.ProjetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/projets")
public class GestionProjet {
    @Autowired
    private EmployeServiceImpl employeServiceImpl;
    @Autowired
    private ProjetServiceImpl projetServiceImpl;

    public GestionProjet() {
    }

    @RequestMapping("/rechparemp")
    public String read(@RequestParam int id_employe, Map<String, Object> model) {
        System.out.println("recherche de l'employé n° " + id_employe);
        try {
            Employe emp = employeServiceImpl.read(id_employe);
            List<Projet> lpj= projetServiceImpl.getProjets(emp);
            model.put("monemp",emp);
            model.put("empipj", lpj);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche ----- --- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affemppj";
    }
}
