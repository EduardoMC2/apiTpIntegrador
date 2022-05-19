
package ar.com.yoprogramo.api.Controller;

import ar.com.yoprogramo.api.Models.Contacto;
import ar.com.yoprogramo.api.Models.Person;
import ar.com.yoprogramo.api.Services.ContactoService;
import ar.com.yoprogramo.api.Services.PersonService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacto") 
public class ContactoController {
    
    @Autowired
    PersonService personService;
    
    @Autowired
    ContactoService contactoService;

    @GetMapping("/all")
    public ResponseEntity<List<Contacto>> getAllContacto() {
        List<Contacto> contactoList = contactoService.getAllContacto();
        return new ResponseEntity<>(contactoList, HttpStatus.OK);
    }
    @GetMapping("/query")
    public ArrayList <Person> getPersonByEmail(@RequestParam("email") String email){
        return personService.getPersonByEmail(email);
    }
    @PostMapping("/person/{person_Id}")
    public ResponseEntity<Contacto> createContacto(@PathVariable(value = "person_Id") Long personId, @RequestBody Contacto contactoRequest) {
        Person p = personService.findById(personId);
        contactoRequest.setPerson(p);
        Contacto newContacto = contactoService.save(contactoRequest);
        return new ResponseEntity<>(newContacto, HttpStatus.CREATED);
        
    }
    
    @PutMapping("/{Id}")
    public ResponseEntity<Contacto> updateComment(@PathVariable("Id") long Id, @RequestBody Contacto contactoRequest) {
        Contacto contacto = contactoService.getContactoById(Id);
        contacto.setPerson(contactoRequest.getPerson());
        contactoRequest.setId(contacto.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(contactoRequest, contacto);

        return new ResponseEntity<>(contactoService.save(contacto), HttpStatus.OK);
    }
    @DeleteMapping("/{Id}")
    public ResponseEntity<HttpStatus> deleteContacto(@PathVariable("Id") long Id) {
        contactoService.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
