package com.ithappens.model;

public enum TipoTask {
	
	OUTROS(""),
	DESENVOLVIMENTO("DEV"),
	BUG("BUG"),
	MELHORIA("Melhoria"),
	MERGE("Merge"),
	TREINAMENTO("Treinamento"),
	TAREFAS_ADM("Tarefas_ADM");
	
	private String descricao;
	
	TipoTask(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
