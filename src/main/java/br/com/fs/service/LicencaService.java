package br.com.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.fs.dto.CompraDTO;
import br.com.fs.dto.UsuarioDTO;

@Service
public class LicencaService {

	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	public List<UsuarioDTO> obtemLicenca(CompraDTO compraDTO) {
		try {
			List<UsuarioDTO> usuarios = restTemplate.postForObject("http://localhost:8888/licenses",
					compraDTO.getCliente().getUsuarios(), List.class);
			return usuarios;
		} catch (RestClientException e) {
			throw new RestClientException("Serviço obtemLicença não disponível", e.getCause());
		}
	}

}
