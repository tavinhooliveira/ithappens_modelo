package com.ithappens.model;

public enum StatusTask {
	
	OPEN("Open"),
	ON_HOLD("On_Hold"),
	REJECTED("Rejected"),
	CLOSED("Closed");
	
	private String descricao;
	
	StatusTask (String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
