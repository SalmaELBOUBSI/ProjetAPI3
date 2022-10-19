package be.condorcet.projetapi3;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.services.EmployeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/employes")
public class GestionEmploye {

    @Autowired
    private EmployeServiceImp employeServiceImp;
    @RequestMapping("/tous")
    public String afftous(Map<String,Object> model){
        System.out.println("Recherche employés");
        try{
            Collection<Employe> lemp= employeServiceImp.all();
            model.put("mesEmployes",lemp);
        }catch (Exception e){
            System.out.println("----------erreur lors de la recherche------ -- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affichagetousemploye";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String matricule, @RequestParam String
            nom, @RequestParam String prenom, Map<String, Object> model){
        System.out.println("création d'un employé");
        Employe emp = new Employe(matricule,nom,prenom);
        try {
            employeServiceImp.create(emp);//mise à jour de l'employe avec son id par l'environnement
            emp = employeServiceImp.read(emp.getIdemploye());
            emp.setTelephone("Aucun");
            emp.setEmail("Aucun");
            employeServiceImp.update(emp);
            model.put("nouvemp",emp);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "nouvelEmploye";
    }

    @RequestMapping("/read")
    public String read(@RequestParam int idemploye, Map<String, Object> model){
        System.out.println("recherche de l'employé n° "+idemploye);
        try {
            Employe emp = employeServiceImp.read(idemploye);
            model.put("monemp",emp);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche ----- --- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affemploye";
    }

    @RequestMapping("/delete")
    public String delete (@RequestParam int idemploye,Map<String,Object> model){
        System.out.println("Supprimer l'employé n° : "+idemploye);
        try{
            Employe emp = employeServiceImp.read(idemploye);
            employeServiceImp.delete(emp);
            model.put("supempl",emp);
        }catch(Exception e){
            System.out.println("----------erreur lors de la suppression ----- --- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "deletEmploye";
    }

    @RequestMapping("/update")
    public String update (@RequestParam int idemploye,@RequestParam String email,@RequestParam String telephone, Map<String,Object> model){
        System.out.println("Update de l'employé n° : "+idemploye);
        try{
            Employe emp = employeServiceImp.read(idemploye);
            emp.setEmail(email);
            emp.setTelephone(telephone);
            employeServiceImp.update(emp);
            model.put("upempl",emp);
        }catch(Exception e){
            System.out.println("----------erreur lors du update ----- --- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "updateEmploye";
    }


}
