package com.ithappens.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Size(max = 12, message = "O Código do cliente não pode conter mais de 12 caracteres")
	private String cd_cliente;
		
	@Size(max = 7, message = "A DCR Relacionada não pode conter mais de 7 caracteres")
	private String dcr_relacionada;
	
//	@Size(max = 700, message = "A Descrição não pode conter mais de 700 caracteres")
	private String descricao;
	
	@NotNull(message = "Data Inicial é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataInicial;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataConclusao;
		
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataPrevista;
		
	private String link;
	
	private String linksvn;
	
	@Size(max = 200, message = "O Titulo não pode conter mais de 200 caracteres")
	@NotEmpty(message = "O Titulo é obrigatória")
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_responsaveis")
	private Responsavel responsaveis;
	
	@ManyToOne
	@JoinColumn(name = "codigo_lideres")
	private Lider lideres;
	
	@ManyToOne
	@JoinColumn(name = "codigo_modulos")
	private Modulo modulos;
		
	@OneToMany(mappedBy = "tasks")
	private List<Hora> horas;
	
	@OneToMany(mappedBy = "tasks")
	private List<Anexo> anexo;
			
	public List<Hora> getHoras() {
		return horas;
	}

	public void setHoras(List<Hora> horas) {
		this.horas = horas;
	}
	
	public List<Anexo> getAnexo() {
		return anexo;
	}

	public void setAnexo(List<Anexo> anexo) {
		this.anexo = anexo;
	}


	@Enumerated(EnumType.STRING)
	private StatusTask status;
	
	@Enumerated(EnumType.STRING)
	private TipoTask tipo;

	public boolean isOpen() {
		return StatusTask.OPEN.equals(this.status);
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCd_cliente() {
		return cd_cliente;
	}

	public void setCd_cliente(String cd_cliente) {
		this.cd_cliente = cd_cliente;
	}

	public String getDcr_relacionada() {
		return dcr_relacionada;
	}

	public void setDcr_relacionada(String dcr_relacionada) {
		this.dcr_relacionada = dcr_relacionada;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public TipoTask getTipo() {
		return tipo;
	}

	public void setTipo(TipoTask tipo) {
		this.tipo = tipo;
	}
	
	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public StatusTask getStatus() {
		return status;
	}

	public void setStatus(StatusTask status) {
		this.status = status;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public String getLinksvn() {
		return linksvn;
	}

	public void setLinksvn(String linksvn) {
		this.linksvn = linksvn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Responsavel getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(Responsavel responsaveis) {
		this.responsaveis = responsaveis;
	}
	
	
	public Lider getLideres() {
		return lideres;
	}

	
	public void setLideres(Lider lideres) {
		this.lideres = lideres;
	}
	

	public Modulo getModulos() {
		return modulos;
	}

	public void setModulos(Modulo modulos) {
		this.modulos = modulos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
