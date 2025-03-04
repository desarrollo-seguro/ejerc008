package es.santander.ascender.ejerc007.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santander.ascender.ejerc007.model.Documento;
import es.santander.ascender.ejerc007.model.Expediente;
import es.santander.ascender.ejerc007.repository.DocumentoRepository;
import es.santander.ascender.ejerc007.repository.ExpedienteRepository;

@Service
public class ExpedienteService {
    @Autowired
    private ExpedienteRepository expedienteRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    // Create
    public Expediente createExpediente(Expediente expediente) {
        return expedienteRepository.save(expediente);
    }

    // Read (all)
    public List<Expediente> getAllExpedientes() {
        return expedienteRepository.findAll();
    }

    // Read (by ID)
    public Optional<Expediente> getExpedienteById(Long id) {
        return expedienteRepository.findById(id);
    }

    // Update
    public Expediente updateExpediente(Long id, Expediente expedienteDetails) {
        Optional<Expediente> expedienteOptional = expedienteRepository.findById(id);
        if (expedienteOptional.isPresent()) {
            Expediente expediente = expedienteOptional.get();
            expediente.setReferencia(expedienteDetails.getReferencia());
            List<Documento> documentos = expediente.getDocumento();

            documentos.clear();
            documentos.addAll(expedienteDetails.getDocumento());
            documentos.stream().forEach(d -> d.setExpediente(expediente));

            //Do not update the document list in this endpoint
            return expedienteRepository.save(expediente);
        }
        return null;
    }

    // Delete
    public boolean deleteExpediente(Long id) {
        Optional<Expediente> expedienteOptional = expedienteRepository.findById(id);
        if (expedienteOptional.isPresent()) {
            expedienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
