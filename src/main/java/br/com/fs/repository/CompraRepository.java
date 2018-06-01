package br.com.fs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fs.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {
	Optional<Compra> findById(Long id);
}
