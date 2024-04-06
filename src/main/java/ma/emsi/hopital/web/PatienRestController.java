package ma.emsi.hopital.web;

import ma.emsi.hopital.entities.Patient;
import ma.emsi.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatienRestController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/patients")
    public List<Patient> patients(){
        return patientRepository.findAll();
    }
}
