package com.bmcotuk.emr.service;


import com.bmcotuk.emr.model.Patient;
import com.bmcotuk.emr.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(String id, Patient patient) {
        Patient existingPatient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        existingPatient.setName(patient.getName());
        existingPatient.setAge(patient.getAge());
        existingPatient.setMedicalHistory(patient.getMedicalHistory());
        return patientRepository.save(existingPatient);
    }

    @Transactional
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> searchPatients(String query) {
        return patientRepository.findAll().stream()
                .filter(patient -> patient.getName().contains(query) || patient.getMedicalHistory().contains(query))
                .collect(Collectors.toList());
    }
}