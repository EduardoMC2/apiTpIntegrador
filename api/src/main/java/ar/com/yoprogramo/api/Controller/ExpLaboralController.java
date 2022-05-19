
package ar.com.yoprogramo.api.Controller;

import ar.com.yoprogramo.api.Models.ExpLaboral;
import ar.com.yoprogramo.api.Models.Person;
import ar.com.yoprogramo.api.Services.ExpLaboralService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expLaboral")
public class ExpLaboralController {

    @Autowired
    PersonService personService;

    @Autowired
    ExpLaboralService expLaboralService;

    @GetMapping("/all")
    public ResponseEntity<List<ExpLaboral>> getAllExpLaboral() {
        List<ExpLaboral> expLaboralList = expLaboralService.getAllExpLaboral();
        return new ResponseEntity<>(expLaboralList, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<ExpLaboral> getExpLaboralById(@PathVariable(value = "Id") Long Id) {
        ExpLaboral expLaboral = expLaboralService.getExpLaboralById(Id);
        return new ResponseEntity<>(expLaboral, HttpStatus.OK);
    }

    @GetMapping("/person/{person_Id}")
    public ResponseEntity<List<ExpLaboral>> getAllExpLaboralByPersonId(@PathVariable(value = "person_Id") Long personId) {
        List<ExpLaboral> expLaboralList = new ArrayList<>();
        if (personService.getPersonById(personId) != null) {
            expLaboralList = expLaboralService.getExpLaboralByPersonId(personId);
            return new ResponseEntity<>(expLaboralList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(expLaboralList, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/person/{person_Id}")
    public ResponseEntity<ExpLaboral> createExpLaboral(@PathVariable(value = "person_Id") Long personId, @RequestBody ExpLaboral expLaboralRequest) {
        Person p = personService.findById(personId);
        expLaboralRequest.setPerson(p);
        ExpLaboral newExpLaboral = expLaboralService.save(expLaboralRequest);
        return new ResponseEntity<>(newExpLaboral, HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<ExpLaboral> updateComment(@PathVariable("Id") long Id, @RequestBody ExpLaboral expLaboralRequest) {
        ExpLaboral expLaboral = expLaboralService.getExpLaboralById(Id);
        expLaboral.setPerson(expLaboralRequest.getPerson());
        expLaboralRequest.setId(expLaboral.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(expLaboralRequest, expLaboral);

        return new ResponseEntity<>(expLaboralService.save(expLaboral), HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<HttpStatus> deleteEducation(@PathVariable("Id") long Id) {
        expLaboralService.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}