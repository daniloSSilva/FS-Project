package br.com.fs.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fs.dto.ClienteDTO;
import br.com.fs.exceptions.ParametroObrigatorioException;
import br.com.fs.exceptions.ResourceNotFoundException;
import br.com.fs.model.Cliente;
import br.com.fs.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ModelMapper modelMapper;
	private ClienteRepository clienteRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public List<Cliente> getAll() throws ResourceNotFoundException {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}

	public void save(Cliente cliente) throws ResourceNotFoundException, ParametroObrigatorioException {
		clienteRepository.save(cliente);
	}

	public void update(Cliente cliente) throws ResourceNotFoundException, ParametroObrigatorioException {
		if (cliente.getId() == null) {
			LOGGER.error("Parametro id obrigatorio.");
			throw new ParametroObrigatorioException("Parametro obrigatorio");
		}
		clienteRepository.save(cliente);
	}

	public void delete(Long id) throws ResourceNotFoundException, ParametroObrigatorioException {
		if (id == null) {
			LOGGER.error("Parametro id obrigatorio.");
			throw new ParametroObrigatorioException("Parametro obrigatorio");
		}
		clienteRepository.deleteById(id);
	}

	public Optional<Cliente> getById(Long id) throws ResourceNotFoundException, ParametroObrigatorioException {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent()) {
			LOGGER.error("Nenhum cliente encontrado.");
			throw new ParametroObrigatorioException("Cliente não encontrado");
		}
		return cliente;
	}

	public void populaRelacionamentos(Cliente cliente) {
		cliente.getEndereco().setCliente(cliente);
		cliente.getContatos().forEach(contato -> contato.setCliente(cliente));

		if (cliente.getPessoaFisica() != null) {
			cliente.getPessoaFisica().setPessoaFisica(cliente);
		} else {
			cliente.getPessoaJuridica().setPessoaJuridica(cliente);
		}

		cliente.getUsuarios().forEach(usuario -> usuario.setCliente(cliente));
	}

	public ClienteDTO convertToDTO(Cliente cliente) throws MappingException {
		try {
			ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
			return clienteDTO;
		} catch (MappingException e) {
			LOGGER.error("Erro de-para objetos.", e.getMessage());
			throw new MappingException((List<ErrorMessage>) e.getErrorMessages());
		}
	}

	public Cliente convertToEntity(ClienteDTO clienteDTO) throws MappingException {
		try {
			Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
			return cliente;
		} catch (MappingException e) {
			LOGGER.error("Erro de-para objetos.", e.getMessage());
			throw new MappingException((List<ErrorMessage>) e.getErrorMessages());
		}
	}
}