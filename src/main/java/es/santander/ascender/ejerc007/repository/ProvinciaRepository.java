package es.santander.ascender.ejerc007.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.santander.ascender.ejerc007.model.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    // We don't need to add any custom methods here for just listing all provinces.
    // JpaRepository already provides findAll().
}
