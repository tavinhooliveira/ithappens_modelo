package com.ithappens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ithappens.model.Task;
import com.ithappens.repository.Tasks;
import com.ithappens.repository.filter.TaskFilter;

@Service
public class CadastroTaskService {
	
	@Autowired
	private Tasks tasks;


	//Metodo Salvar
	public void salvar(Task task) {
		try {
			tasks.save(task);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
		
	}
		
	
	//Metodo Listar
	public List<Task>filtrar(TaskFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return tasks.findByDescricaoContaining(descricao);
	}
	
	//Metodo Excluir
	public void excluir(Long codigo){
		tasks.delete(codigo);		
	}
	
	
	
}
