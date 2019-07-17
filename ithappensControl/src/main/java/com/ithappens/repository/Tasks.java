package com.ithappens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ithappens.model.Task;

public interface Tasks extends JpaRepository<Task, Long> {
	
	public List<Task> findByDescricaoContaining(String descricao);
	
	 @Query("SELECT count(codigo) FROM Task")
	    public List<Task> findByTaskTotalQTA();
	 
	 @Query("SELECT count(codigo) FROM Task where status = 'OPEN'")
	    public List<Task> findByTaskOpenQTA();
	 
	 @Query("SELECT count(codigo) FROM Task where status = 'ON_HOLD'")
	    public List<Task> findByTaskOnHoldQTA();
	 
	 @Query("SELECT count(codigo) FROM Task where status = 'CLOSED'")
	    public List<Task> findByTaskClosedQTA();
	 
	 @Query("SELECT count(codigo) FROM Task where status = 'REJECTED'")
	    public List<Task> findByTaskRejectedQTA();

}
