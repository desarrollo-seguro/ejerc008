package es.santander.ascender.ejerc007.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.santander.ascender.ejerc007.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
