package ma.emsi.hopital.service;

import ma.emsi.hopital.entities.Consultation;
import ma.emsi.hopital.entities.Medecin;
import ma.emsi.hopital.entities.Patient;
import ma.emsi.hopital.entities.RendezVous;
import ma.emsi.hopital.repository.ConsultationRepository;
import ma.emsi.hopital.repository.MedecinRepository;
import ma.emsi.hopital.repository.PatientRepository;
import ma.emsi.hopital.repository.RendezVousRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class IHospitalServiceImpl implements IHospitalService {
    PatientRepository patientRepository;
    MedecinRepository medecinRepository;
    RendezVousRepository rendezVousRepository;
    ConsultationRepository consultationRepository;

    public IHospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
