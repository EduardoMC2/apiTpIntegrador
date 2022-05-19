
package ar.com.yoprogramo.api.Controller;

import ar.com.yoprogramo.api.Models.Person;
import ar.com.yoprogramo.api.Services.PersonService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person") 
public class PersonController {
    @Autowired
    PersonService personService;
    
    @GetMapping("/all")
    public ArrayList<Person> getAllPersons(){
        return personService.getAllPersons();
    }
    @PostMapping()
    public Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
        
    }
    @GetMapping("/{Id}")
    public Person getPersonById(@PathVariable("Id")Long Id){
        return personService.getPersonById(Id);
    }

  
    @GetMapping("/query")
    public ArrayList <Person> getPersonByApellido(@RequestParam("apellido") String apellido){
        return personService.getPersonByApellido(apellido);
    }
    @DeleteMapping ("/{Id}")
    public String removePerson(@PathVariable("Id")Long Id){
        if(personService.removePerson(Id)){
            return  "Se eliminó a la persona escogida por Id "+ Id +"correctamente";
        }else{
            return "La persona no exsite o no pudo ser eliminada";
        }
    }
    
}
