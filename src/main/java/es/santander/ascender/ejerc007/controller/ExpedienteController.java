package es.santander.ascender.ejerc007.controller;

import java.util.List;
import java.util.Optional;

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

import es.santander.ascender.ejerc007.model.Expediente;
import es.santander.ascender.ejerc007.service.ExpedienteService;

@RestController
@RequestMapping("/api/expedientes")
public class ExpedienteController {
    @Autowired
    private ExpedienteService expedienteService;

    // Create
    @PostMapping
    public ResponseEntity<Expediente> createExpediente(@RequestBody Expediente expediente) {
        Expediente createdExpediente = expedienteService.createExpediente(expediente);
        return new ResponseEntity<>(createdExpediente, HttpStatus.CREATED);
    }

    // Read (all)
    @GetMapping
    public ResponseEntity<List<Expediente>> getAllExpedientes() {
        List<Expediente> expedientes = expedienteService.getAllExpedientes();
        return new ResponseEntity<>(expedientes, HttpStatus.OK);
    }

    // Read (by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Expediente> getExpedienteById(@PathVariable Long id) {
        Optional<Expediente> expediente = expedienteService.getExpedienteById(id);
        if (expediente.isPresent()) {
            return new ResponseEntity<>(expediente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Expediente> updateExpediente(@PathVariable Long id, @RequestBody Expediente expedienteDetails) {
        Expediente updatedExpediente = expedienteService.updateExpediente(id, expedienteDetails);
        if (updatedExpediente != null) {
            return new ResponseEntity<>(updatedExpediente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpediente(@PathVariable Long id) {
        boolean deleted = expedienteService.deleteExpediente(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
