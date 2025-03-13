package es.santander.ascender.ejerc007;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc007.model.Expediente;
import es.santander.ascender.ejerc007.repository.ExpedienteRepository;
import es.santander.ascender.ejerc007.service.ProvinciaService;

@SpringBootTest
class Ejerc007ApplicationTests {

	@Autowired
	private ExpedienteRepository expedienteRepository;

	@Autowired
	private ProvinciaService provinciaService;

	@Test
	@Transactional
	@WithMockUser(username = "pepe")
	void contextLoads() {

		provinciaService.getProvinciaUsuario();


		Expediente expediente = new Expediente();
	
		expediente = expedienteRepository.save(expediente);

		assertNotNull(expediente.getId());
	
		Expediente expediente2 = expedienteRepository.findById(expediente.getId()).orElse(null);

		assertNotNull(expediente.getId());

		assertEquals(0, expediente2.getDocumento().size());
	}
}
