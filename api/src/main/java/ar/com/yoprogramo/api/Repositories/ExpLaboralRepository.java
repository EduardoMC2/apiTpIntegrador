
package ar.com.yoprogramo.api.Repositories;

import ar.com.yoprogramo.api.Models.ExpLaboral;
import ar.com.yoprogramo.api.Models.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpLaboralRepository  extends JpaRepository<ExpLaboral,Long>{
    public List<ExpLaboral> findAllByPersonId(Long personId);
    public abstract ArrayList<Person> findByApellido(String apellido);
}
