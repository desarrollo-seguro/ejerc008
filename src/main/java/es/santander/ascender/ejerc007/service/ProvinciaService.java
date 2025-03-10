package es.santander.ascender.ejerc007.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;
import es.santander.ascender.ejerc007.model.Provincia;
import es.santander.ascender.ejerc007.repository.PersonaRepository;
import es.santander.ascender.ejerc007.repository.ProvinciaRepository;

@Service
@Transactional
public class ProvinciaService {

    private static final Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(ProvinciaService.class);

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private PersonaRepository personaRepository;

    // Create
    public Provincia createProvincia(Provincia provincia) {
        logger.info(String.format("La fecha actual: %s", LocalDateTime.now()));
        
        return provinciaRepository.save(provincia);
    }

    // Read (all)
    @Transactional(readOnly = true)
    public List<Provincia> getAllProvincias() {
        return provinciaRepository.findAll();
    }

    // Read (by ID)
    @Transactional(readOnly = true)
    public Optional<Provincia> getProvinciaById(Long id) {
        return provinciaRepository.findById(id);
    }

    // Update
    public Provincia updateProvincia(Long id, Provincia provinciaDetails) {
        Optional<Provincia> provinciaOptional = provinciaRepository.findById(id);
        if (provinciaOptional.isPresent()) {
            Provincia provincia = provinciaOptional.get();
            provincia.setNombre(provinciaDetails.getNombre());
            provincia.setCodigo(provinciaDetails.getCodigo());
            provincia.setDescripcion(provinciaDetails.getDescripcion());
            return provinciaRepository.save(provincia);
        }
        return null; // or throw an exception
    }

    public void deleteProvincia(Long id) {
    
        //personaRepository.findAll().forEach(p -> personaRepository.delete(p));
        personaRepository.limpiaProvincia(id);

        provinciaRepository.deleteById(id);
    }
}
