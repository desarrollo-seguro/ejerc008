package es.santander.ascender.ejerc007.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.santander.ascender.ejerc007.model.Expediente;

public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {

}
