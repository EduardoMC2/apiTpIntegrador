
package ar.com.yoprogramo.api.Repositories;

import ar.com.yoprogramo.api.Models.Person;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
    public abstract ArrayList<Person> findByApellido (String apellido);
}
