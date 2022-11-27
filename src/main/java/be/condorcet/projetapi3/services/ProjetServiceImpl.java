package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.entities.Projet;
import be.condorcet.projetapi3.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProjetServiceImpl implements InterfProjetService{

    @Autowired
    private static ProjetRepository projetRepository;
    @Autowired
    private EmployeServiceImpl employeService;


    @Override
    public List<Projet> getProjets(Employe emp) {
        List<Projet> lpj = projetRepository.findProjetByEmploye(emp);
        return lpj;
    }

    @Override
    public Projet create(Projet projet) throws Exception {
        projetRepository.save(projet);
        return projet;
    }
    @Override
    public Projet update(Projet projet) throws Exception {
        read(projet.getId_projet());
        projetRepository.save(projet);
        return projet;
    }

    @Override
    public void delete(Projet projet) throws Exception {
        projetRepository.deleteById(projet.getId_projet());
    }
    @Override
    public List<Projet> all() throws Exception {
        return projetRepository.findAll();
    }

    @Override
    public Projet read(Integer id_projet) throws Exception {
        return projetRepository.findById(id_projet).get();
    }

    @Override
    public Page<Projet> allp(Pageable pageable) throws Exception {
        return projetRepository.findAll(pageable);
    }


}
