package com.ithappens.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ithappens.model.Hora;

public interface Horas extends JpaRepository<Hora,Long> {
	

}
