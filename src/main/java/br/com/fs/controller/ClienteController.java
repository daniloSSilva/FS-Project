package br.com.fs.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fs.dto.ClienteDTO;
import br.com.fs.exceptions.ParametroObrigatorioException;
import br.com.fs.exceptions.ResourceNotFoundException;
import br.com.fs.model.Cliente;
import br.com.fs.service.ClienteService;

/**
 * Endpoint para métodos de cliente
 *
 * @author Danilo Silva
 * @since 2018-05-28
 */

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

	/**
	 * Retorna todos os clientes.
	 * 
	 * @return retorna todos os clientes
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/todos")
	public List<ClienteDTO> getAll() throws ResourceNotFoundException {
		LOGGER.info("Buscando todos os clientes...");

		List<Cliente> clientes = clienteService.getAll();
		return clientes.stream().map(cliente -> clienteService.convertToDTO(cliente)).collect(Collectors.toList());
	}

	/**
	 * Retorna cliente por id.
	 * 
	 * @param id.
	 *            id do cliente.
	 * @return retorna um cliente pelo id.
	 * @throws ResourceNotFoundException
	 *             error 404 não encontado.
	 * @throws ParametroObrigatorioException
	 *             error parametro obrigatório.
	 */
	@GetMapping("/{id}")
	public ClienteDTO getById(@PathVariable("id") Long id)
			throws ResourceNotFoundException, ParametroObrigatorioException {
		LOGGER.info("Buscando cliente por id: {}", id);

		Optional<Cliente> clienteDTO = clienteService.getById(id);
		return clienteService.convertToDTO(clienteDTO.get());
	}

	@PutMapping
	public void update(@RequestBody ClienteDTO clienteDTO)
			throws ResourceNotFoundException, ParametroObrigatorioException {
		LOGGER.info("Atualizando cliente id: {}", clienteDTO.getId());

		Cliente cliente = clienteService.convertToEntity(clienteDTO);
		clienteService.populaRelacionamentos(cliente);
		clienteService.save(cliente);
	}

	/**
	 * Insere uma nova compra e atualiza filhos.
	 * 
	 * @param compraDTO.
	 *            compraDTO.
	 * @throws ResourceNotFoundException
	 *             error 404 não encontado.
	 * @throws ParametroObrigatorioException
	 *             error parametro obrigatório.
	 */
	@PostMapping
	public void save(@RequestBody ClienteDTO clienteDTO)
			throws ResourceNotFoundException, ParametroObrigatorioException {
		LOGGER.info("Inserindo novo cliente...");

		Cliente cliente = clienteService.convertToEntity(clienteDTO);
		clienteService.populaRelacionamentos(cliente);
		clienteService.save(cliente);
	}

	/**
	 * Deleta uma compra.
	 * 
	 * @param id
	 *            id da compra
	 * @throws ResourceNotFoundException
	 *             error 404 não encontado.
	 * @throws ParametroObrigatorioException
	 *             error parametro obrigatório.
	 */
	@DeleteMapping("/{id}")
	@ExceptionHandler()
	public void delete(@PathVariable("id") Long id) throws ResourceNotFoundException, ParametroObrigatorioException {
		LOGGER.info("Deletando cliente id: {}", id);
		clienteService.delete(id);
	}

}