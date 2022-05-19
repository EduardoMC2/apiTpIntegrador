
package ar.com.yoprogramo.api.Controller;

import ar.com.yoprogramo.api.Models.Education;
import ar.com.yoprogramo.api.Models.Person;
import ar.com.yoprogramo.api.Services.EducationService;
import ar.com.yoprogramo.api.Services.PersonService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/education")
public class EducationController {

    @Autowired
    PersonService personService;

    @Autowired
    EducationService educationService;

    @GetMapping("/all")
    public ResponseEntity<List<Education>> getAllEducation() {
        List<Education> educationList = educationService.getAllEducation();
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Education> getEducationById(@PathVariable(value = "Id") Long Id) {
        Education education = educationService.getEducationById(Id);
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @GetMapping("/person/{person_Id}")
    public ResponseEntity<List<Education>> getAllEducationByPersonId(@PathVariable(value = "person_Id") Long personId) {
        List<Education> educationList = new ArrayList<>();
        if (personService.getPersonById(personId) != null) {
            educationList = educationService.getEducationByPersonId(personId);
            return new ResponseEntity<>(educationList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(educationList, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/person/{person_Id}")
    public ResponseEntity<Education> createEducation(@PathVariable(value = "person_Id") Long personId, @RequestBody Education educationRequest) {
        Person p = personService.findById(personId);
        educationRequest.setPerson(p);
        Education newEducation = educationService.save(educationRequest);
        return new ResponseEntity<>(newEducation, HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<Education> updateComment(@PathVariable("Id") long Id, @RequestBody Education educationRequest) {
        Education education = educationService.getEducationById(Id);
        education.setPerson(educationRequest.getPerson());
        educationRequest.setId(education.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(educationRequest, education);

        return new ResponseEntity<>(educationService.save(education), HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<HttpStatus> deleteEducation(@PathVariable("Id") long Id) {
        educationService.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
