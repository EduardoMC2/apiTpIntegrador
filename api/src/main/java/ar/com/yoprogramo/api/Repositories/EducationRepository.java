
package ar.com.yoprogramo.api.Repositories;

import ar.com.yoprogramo.api.Models.Education;
import ar.com.yoprogramo.api.Models.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education,Long>{
    public List<Education> findAllByPersonId(Long personId);
    public abstract ArrayList<Person> findByApellido(String apellido);
}
