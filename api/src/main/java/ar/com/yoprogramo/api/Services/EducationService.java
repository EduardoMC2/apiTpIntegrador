
package ar.com.yoprogramo.api.Services;

import ar.com.yoprogramo.api.Models.Education;
import ar.com.yoprogramo.api.Repositories.EducationRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
     @Autowired
    EducationRepository educationRepository;

    public ArrayList<Education> getAllEducation() {
        return (ArrayList<Education>) educationRepository.findAll();
    }

    public Education save(Education education) {
        return educationRepository.save(education);
    }

    public Education getEducationById(Long Id) {
        return educationRepository.findById(Id).get();
    }

    public boolean deleteById(Long Id) {
        try {
            educationRepository.deleteById(Id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Education> getEducationByPersonId(Long personId) {
           return educationRepository.findAllByPersonId(personId);
    }
}
