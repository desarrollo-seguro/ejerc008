package es.santander.ascender.ejerc007;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc007.model.Expediente;
import es.santander.ascender.ejerc007.repository.ExpedienteRepository;

@SpringBootTest
class Ejerc007ApplicationTests {

	@Autowired
	private ExpedienteRepository expedienteRepository;

	@Test
	@Transactional
	void contextLoads() {
		Expediente expediente = new Expediente();
	
		expediente = expedienteRepository.save(expediente);

		assertNotNull(expediente.getId());
	
		Expediente expediente2 = expedienteRepository.findById(expediente.getId()).orElse(null);

		assertNotNull(expediente.getId());

		assertEquals(0, expediente2.getDocumento().size());
	}
}
