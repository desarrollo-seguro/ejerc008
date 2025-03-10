package es.santander.ascender.ejerc007.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc007.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    // Quiero actualizar todas las personas de una provicia_id estableciendo su provincia_id a null
    
    @Modifying
    @Transactional
    @Query("update Persona p set p.provincia.id = null where p.provincia.id = ?1")
    public int limpiaProvincia(Long provinciaId);
    

}
