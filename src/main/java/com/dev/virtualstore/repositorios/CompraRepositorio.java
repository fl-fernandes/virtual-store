package com.dev.virtualstore.repositorios;

import com.dev.virtualstore.modelos.Compra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepositorio extends JpaRepository<Compra, Long> {
	
}
