package com.call.center.operator.controllers;

import com.call.center.operator.models.Patient;
import com.call.center.operator.repositories.PatientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Log4j2
@RestController
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity noValue(Exception ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    @GetMapping("/api/patient")
    public ResponseEntity getAll() {
//        log.info("Request GET to: /api/patient");
        return ResponseEntity.ok(patientRepository.findAll());
    }

    @GetMapping("/api/patient/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
//        log.info("Request GET to: /api/patient/{id} "+ id);
        return ResponseEntity.ok(patientRepository.findById(id).orElseThrow());
    }

    @GetMapping("/api/patient/phone/{num}")
    public ResponseEntity getByPhone (@PathVariable String num) {
//        log.info("Request GET to: /api/patient/phone/{num} "+ num);
        String crearNumber = num.replace("+","");
        return ResponseEntity.ok(patientRepository.findByPhone(crearNumber).orElseThrow());
    }

    @PostMapping("/api/patient")
    public ResponseEntity saveCall(@RequestBody @Valid Patient patient){
//        log.info("Request POST to: /api/patient/ "+ patient);
        return ResponseEntity.ok(patientRepository.save(patient));
    }
}
