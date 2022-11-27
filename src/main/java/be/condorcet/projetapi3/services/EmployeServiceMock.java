package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeServiceMock implements IntefEmployeService{

    private List<Employe> lemp= new ArrayList<>() ;
    private int num=0;
    @Override
    public Employe create(Employe emp) throws Exception {
        for(Employe emp2 : lemp){
            if(emp2.getMatricule().equals(emp.getMatricule())&&
                    (emp2.getNom().equals(emp.getNom()) &&
                            emp2.getPrenom().equals(emp.getPrenom()))) throw new Exception("doublon");}
        num++;
        emp.setId_employe(num);
        lemp.add(emp);
        return emp;
    }

    @Override
    public List<Employe> read(String nom) {
        List<Employe> lenom = new ArrayList<>();
        lemp.stream().filter(lemp->lemp.getNom().equals(nom)).forEach(emp->lenom.add(emp));
        return lenom;
    }

    @Override
    public Employe read(String nom, String prenom, String telephone) {
        return lemp.stream().filter(emp->emp.getNom().equals(nom) &&
                emp.getPrenom().equals(prenom)&&
                emp.getTelephone().equals(telephone)).findFirst().get();
    }



    @Override
    public Employe read(Integer id) throws Exception {
        for(Employe emp : lemp){
            if(emp.getId_employe().equals(id))
                return emp;
        }
        throw new Exception("code inconnu");
    }

    @Override
    public Employe update(Employe employe) throws Exception {
        Integer id = employe.getId_employe();
        Employe oldEmp= read(id);
        oldEmp.setMatricule(employe.getMatricule());
        oldEmp.setNom(employe.getNom());
        oldEmp.setPrenom(employe.getPrenom());
        oldEmp.setTelephone(employe.getTelephone());
        oldEmp.setMail(employe.getMail());
        return read(oldEmp.getId_employe());
    }

    @Override
    public void delete(Employe empdel) throws Exception {
        Iterator<Employe> itemp= lemp.iterator();
        while(itemp.hasNext()) {
            Employe emp = itemp.next();
            if(emp.getId_employe().equals(empdel.getId_employe())) {
                if(emp.getProjets() ==null || emp.getProjets().isEmpty())
                    itemp.remove();
                else throw new Exception("liste de projet non vide");
            }
        }
    }

    @Override
    public List<Employe> all() throws Exception {
        return lemp;
    }

    @Override
    public Page<Employe> allp(Pageable pageable) throws Exception {
        return null;
    }
}
