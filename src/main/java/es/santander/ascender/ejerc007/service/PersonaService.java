package es.santander.ascender.ejerc007.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc007.model.Persona;
import es.santander.ascender.ejerc007.model.Provincia;
import es.santander.ascender.ejerc007.repository.PersonaRepository;
import es.santander.ascender.ejerc007.repository.ProvinciaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    /*
     * Los métodos CRUD sobre personaRepository
     */

    public Persona createPersona(Persona persona) {
        estableceProvincia(persona);
        return personaRepository.save(persona);
    }

    @Transactional(readOnly = true)
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Persona> getPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    public Persona updatePersona(Long id, Persona personaDetails) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            Persona persona = personaOptional.get();
            persona.setNombre(personaDetails.getNombre());
            persona.setApellidos(personaDetails.getApellidos());
            persona.setEmail(personaDetails.getEmail());
            persona.setTelefono(personaDetails.getTelefono());
            
            estableceProvincia(personaDetails, persona);
            
            return personaRepository.save(persona);
        }
        return null; // or throw an exception
    }

    private void estableceProvincia(Persona personaDetails) {
        estableceProvincia(personaDetails, personaDetails);
    }

    private void estableceProvincia(Persona personaDetails, Persona persona) {
        Provincia provincia = null;
        if (personaDetails.getProvincia()!= null && personaDetails.getProvincia().getId() != null) {
            provincia = provinciaRepository.findById(personaDetails.getProvincia().getId()).orElse(null);
            
        } 
        persona.setProvincia(provincia);
    }

    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }
}
