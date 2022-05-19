
package ar.com.yoprogramo.api.Services;

import ar.com.yoprogramo.api.Models.Contacto;
import ar.com.yoprogramo.api.Repositories.ContactoRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContactoService {
    @Autowired
    ContactoRepository contactoRepository;
    
    public ArrayList<Contacto> getAllContacto() {
        return (ArrayList<Contacto>) contactoRepository.findAll();
    }
    
    public Contacto save(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    public Contacto getContactoById(Long Id) {
        return contactoRepository.findById(Id).get();
    }

    public boolean deleteById(Long Id) {
        try {
            contactoRepository.deleteById(Id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

   
}
 