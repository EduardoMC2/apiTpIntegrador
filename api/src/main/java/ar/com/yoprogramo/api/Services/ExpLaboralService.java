
package ar.com.yoprogramo.api.Services;

import ar.com.yoprogramo.api.Models.ExpLaboral;
import ar.com.yoprogramo.api.Repositories.ExpLaboralRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpLaboralService {
     @Autowired
    ExpLaboralRepository expLaboralRepository;

    public ArrayList<ExpLaboral> getAllExpLaboral() {
        return (ArrayList<ExpLaboral>) expLaboralRepository.findAll();
    }

    public ExpLaboral save(ExpLaboral expLaboral) {
        return expLaboralRepository.save(expLaboral);
    }

    public ExpLaboral getExpLaboralById(Long Id) {
        return expLaboralRepository.findById(Id).get();
    }

    public boolean deleteById(Long Id) {
        try {
            expLaboralRepository.deleteById(Id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ExpLaboral> getExpLaboralByPersonId(Long personId) {
           return expLaboralRepository.findAllByPersonId(personId);
    }
}
