package es.santander.ascender.ejerc007.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santander.ascender.ejerc007.model.Provincia;
import es.santander.ascender.ejerc007.service.ProvinciaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Provincia>> getAllProvincias() {
        List<Provincia> provincias = provinciaService.getAllProvincias();
        return new ResponseEntity<>(provincias, HttpStatus.OK);
    }

    // Read (by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Provincia> getProvinciaById(@PathVariable Long id) {
        if (id.longValue() == -1) {
            throw new MyBadDataException("No es válido", id);
        }
        Optional<Provincia> provincia = provinciaService.getProvinciaById(id);
        return provincia.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create
    @PostMapping
    public ResponseEntity<Provincia> createProvincia(@Valid @RequestBody Provincia provincia) {
        Provincia createdProvincia = provinciaService.createProvincia(provincia);
        return new ResponseEntity<>(createdProvincia, HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Provincia> updateProvincia(@PathVariable Long id, @Valid @RequestBody Provincia provinciaDetails) {
        Provincia updatedProvincia = provinciaService.updateProvincia(id, provinciaDetails);
        if (updatedProvincia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedProvincia, HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvincia(@PathVariable Long id) {
        provinciaService.deleteProvincia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
