package com.ithappens.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ithappens.model.Modulo;

public interface Modulos extends JpaRepository<Modulo, Long> {
	
public List<Modulos> findByNomeContaining (String nome);
	
	public Optional<Modulos> findByNomeIgnoreCase (String nome);
	
	 @Query("SELECT count(codigo) FROM Modulo")
	    public List<Modulo> findBymodulosAllQTA();
	
	
		

}

