package br.com.fs.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fs.dto.CompraDTO;
import br.com.fs.dto.UsuarioDTO;
import br.com.fs.exceptions.ParametroObrigatorioException;
import br.com.fs.exceptions.ResourceNotFoundException;
import br.com.fs.model.Compra;
import br.com.fs.service.CompraService;
import br.com.fs.service.LicencaService;
import br.com.fs.service.UsuarioService;

/**
 * Endpoint para métodos de compra
 *
 * @author Danilo Silva
 * @since 2018-05-28
 */

@RestController
@RequestMapping("/compra")
public class CompraController implements Serializable {

	@Autowired
	private CompraService compraService;

	@Autowired
	private LicencaService licencaService;

	@Autowired
	private UsuarioService usuarioService;
	
	private static final long serialVersionUID = -4566642518896523105L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CompraController.class);

	/**
	 * Retorna todas as compras.
	 * 
	 * @return retorna todas as compras
	 */
	@GetMapping("/todos")
	public List<CompraDTO> getAll() throws ResourceNotFoundException {
		LOGGER.info("Buscando todas as compras...");

		List<Compra> compras = compraService.getAll();
		return compras.stream().map(compra -> compraService.convertToDTO(compra)).collect(Collectors.toList());
	}

	/**
	 * Retorna uma compra por id.
	 * 
	 * @param id.
	 *            id da compra.
	 * @return retorna a compra pelo id.
	 * @throws ResourceNotFoundException
	 *             error 404 não encontado.
	 * @throws ParametroObrigatorioException
	 *             error parametro obrigatório.
	 */
	@GetMapping("/{id}")
	public CompraDTO getById(@PathVariable("id") Long id)
			throws ResourceNotFoundException, ParametroObrigatorioException {

		LOGGER.info("Buscando compra por id: {}", id);
		Optional<Compra> compraDTO = compraService.getById(id);
		return compraService.convertToDTO(compraDTO.get());
	}

	/**
	 * Insere uma nova compra e atualiza filhos.
	 * 
	 * @param compraDTO.
	 *            compraDTO.
	 */
	@PostMapping
	public void save(@RequestBody CompraDTO compraDTO)
			throws ResourceNotFoundException, ParametroObrigatorioException, InvocationTargetException {
		List<UsuarioDTO> usuarios = new ArrayList<>();
		Compra compra = new Compra();

		LOGGER.info("Inserindo compra...");
		usuarios = licencaService.obtemLicenca(compraDTO);

		compraDTO.getCliente().setUsuarios(usuarios);
		compra = compraService.convertToEntity(compraDTO);
		compraService.populaRelacionamentos(compra);
		
		usuarioService.saveAll(compra.getCliente().getUsuarios());
		
		compraService.save(compra);
	}


}
