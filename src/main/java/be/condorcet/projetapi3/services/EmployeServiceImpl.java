package be.condorcet.projetapi3.services;
import be.condorcet.projetapi3.entities.Employe;
import be.condorcet.projetapi3.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional(rollbackOn = Exception.class)
public class EmployeServiceImpl implements IntefEmployeService{
    @Autowired
    private EmployeRepository employeRepository;
    @Override
    public List<Employe> read(String nom) {
        return employeRepository.findEmployesByNomLike(nom+"%");
    }



    @Override
    public Employe create(Employe employe) throws Exception {
        employeRepository.save(employe);
        return employe;
    }
    @Override
    public Employe read(Integer id) throws Exception {
        Optional<Employe> ocl= employeRepository.findById(id);
        return ocl.get();
    }
    @Override
    public Employe update(Employe employe) throws Exception {
        employeRepository.save(employe);
        return employe;
    }
    @Override
    public void delete(Employe employe) throws Exception {
        employeRepository.deleteById(employe.getId_employe());
    }
    @Override
    public List<Employe> all() throws Exception {
        return employeRepository.findAll();
    }


}
