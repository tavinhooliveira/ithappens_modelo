package com.ithappens.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ithappens.model.Responsavel;

public interface Responsaveis extends JpaRepository<Responsavel, Long>{
	
	public List<Responsaveis> findByNomeContaining(String nome);
	
	public Optional<Responsaveis> findByNomeIgnoreCase(String nome);
	
	@Query("SELECT count(codigo) FROM Responsavel")
    public List<Responsavel> findBypessoasContResponsavelQTA();
	
	

}
