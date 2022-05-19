
package ar.com.yoprogramo.api.Services;

import ar.com.yoprogramo.api.Models.Person;
import ar.com.yoprogramo.api.Repositories.PersonRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    
    public ArrayList <Person> getAllPersons(){
        return (ArrayList<Person>) personRepository.findAll();
    }
    public Person savePerson (Person persona){
        return personRepository.save(persona);
    }

  

    public ArrayList<Person> getPersonByApellido(String apellido) {
        return personRepository.findByApellido(apellido);
    }

    public boolean removePerson(Long Id) {
        try {
           personRepository.deleteById(Id);
           return true;
        } catch (Exception e) {
            return false;
        }
    }

   public Person findById(Long personId) {
        return personRepository.findById(personId).get();
    }

    public ArrayList<Person> getPersonByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    public Person getPersonById(Long Id) {
        return personRepository.findById(Id).get();
    }

  
    
}
