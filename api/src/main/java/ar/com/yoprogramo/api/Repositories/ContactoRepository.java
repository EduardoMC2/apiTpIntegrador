
package ar.com.yoprogramo.api.Repositories;

import ar.com.yoprogramo.api.Models.Contacto;
import ar.com.yoprogramo.api.Models.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactoRepository extends JpaRepository<Contacto,Long> {
    public List<Contacto> findAllByPersonId(Long personId);
    public abstract ArrayList<Person> findByApellido(String apellido);
}
