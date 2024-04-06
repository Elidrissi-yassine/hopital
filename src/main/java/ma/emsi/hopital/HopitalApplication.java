package ma.emsi.hopital;

import ma.emsi.hopital.entities.*;
import ma.emsi.hopital.repository.ConsultationRepository;
import ma.emsi.hopital.repository.MedecinRepository;
import ma.emsi.hopital.repository.PatientRepository;
import ma.emsi.hopital.repository.RendezVousRepository;
import ma.emsi.hopital.service.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HopitalApplication {
    @Autowired
    private PatientRepository patientRepository;
    private ConsultationRepository consultationRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private IHospitalService hospitalService;

    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);
    }

    @Bean
    CommandLineRunner start(IHospitalService hospitalService, PatientRepository patientRepository, ConsultationRepository consultationRepository
                            , MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository){
        this.hospitalService = hospitalService;
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        return args -> {
            Stream.of("patient1","patient2","patient3")
                    .forEach(name->{
                        Patient patient=new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(false);
                        hospitalService.savePatient(patient);
                    });
            Stream.of("medecin1","medecin2","medecin3")
                    .forEach(name->{
                        Medecin medecin=new Medecin();
                        medecin.setNom(name);
                        medecin.setEmail(name+"@gmail.com");
                        medecin.setSpécialité(Math.random()>0.5?"cardio":"dentiste");
                        hospitalService.saveMedecin(medecin);
                    });
            Patient patient=patientRepository.findById(Long.valueOf(1)).orElse(null);
            Patient patient2=patientRepository.findByNom("patient1");
            Medecin medecin1=medecinRepository.findByNom("medecin1");
            RendezVous rv=new RendezVous();
            rv.setDate(new Date());
            rv.setMedecin(medecin1);
            rv.setPatient(patient);
            rv.setStatus(StatusRV.PENDING);
            RendezVous rv1=hospitalService.saveRendezVous(rv);
            System.out.println(rv1.getDate());

            Consultation consultation=Consultation.builder()
                    .rendez_vous(rendezVousRepository.findAll().get(0))
                    .dateconsultation(new Date())
                    .rapport("rapport de la consultation1")
                    .build();
            hospitalService.saveConsultation(consultation);
            System.out.println(rv1.getDate());
        };

    }

    /**
     * @Override
     *     public void run(String... args) throws Exception {
     *         //patientRepository.save(new Patient(null,"Yassine",new Date(),false,123));
     *         //patientRepository.save(new Patient(null,"Amine",new Date(),false,123));
     *         //patientRepository.save(new Patient(null,"sanaa",new Date(),false,123));
     *
     *
     *
     *         Patient patient=new Patient();
     *         patient.setId(null);
     *         patient.setNom("mohamed");
     *         patient.setDateNaissance(new Date());
     *         patient.setMalade(false);
     *         patient.setScore(23);
     *         Patient patient2=new Patient(null,"Yassine",new Date(),false,123);
     *         Patient patient3=Patient.builder()
     *                 .nom("Amie")
     *                 .dateNaissance(new Date())
     *                 .malade(true)
     *                 .score(15)
     *                 .build();
     *
             *
             *
}
     */
}
