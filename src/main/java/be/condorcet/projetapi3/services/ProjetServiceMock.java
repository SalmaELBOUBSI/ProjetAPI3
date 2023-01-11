package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjetServiceMock implements InterfProjetService{
    private List<Projet>lpj = new ArrayList<>();
    private  int num=0;
    @Autowired
    public IntefEmployeService employeServiceImpl;
    @Override
    public List<Projet> getProjets(Employe emp) {
        return (List<Projet>) emp.getProjets();
    }



    @Override
    public Projet create(Projet projet) throws Exception {
        num++;
        projet.setId_projet(num);
        Employe emp = projet.getEmploye();
        if(emp.getProjets()==null) emp.setProjets(new ArrayList<>());
        emp.getProjets().add(projet);
        lpj.add(projet);
        return projet;
    }

    @Override
    public Projet read(Integer id) throws Exception {
        for(Projet p : lpj){
            if(p.getId_projet().equals(id)) return p;
        }
        throw new Exception("inconnu");
    }

    @Override
    public Projet update(Projet projet) throws Exception {
        Integer id = projet.getId_projet();
        Projet oldPjr= read(id);
        oldPjr.setId_projet(projet.getId_projet());
        oldPjr.setTitre(projet.getTitre());
        oldPjr.setDate_demarrage(projet.getDate_demarrage());
        oldPjr.setDate_butoir(projet.getDate_butoir());
        oldPjr.setCout(projet.getCout());
        return read(oldPjr.getId_projet());
    }

    @Override
    public void delete(Projet projet) throws Exception {
        Iterator<Projet> itp= lpj.iterator();
        while(itp.hasNext()) {
            Projet pjr = itp.next();
            if(pjr.getId_projet().equals(projet.getId_projet())){
                pjr.getEmploye().getProjets().remove(pjr);
                itp.remove();
            }
        }
    }

    @Override
    public List<Projet> all() throws Exception {
        return lpj;
    }

    @Override
    public Page<Projet> allp(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public Projet readtitre(String titre) {
        List<Projet> ltitre =new ArrayList<>();
        lpj.stream().filter(lpj->lpj.getTitre().equals(titre)).forEach(pjr->lpj.add(pjr));
        return (Projet) ltitre;
    }

}
