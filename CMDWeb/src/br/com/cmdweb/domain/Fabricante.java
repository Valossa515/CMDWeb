package br.com.cmdweb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fabricantes")
@NamedQueries({
	@NamedQuery(name = "Fabricantes.listar", query = "SELECT fabricante FROM Fabricante fabricante"),
	@NamedQuery(name = "Fabricantes.buscar", query = "SELECT fabricante FROM Fabricante fabricante WHERE fabricante.codfabricante = :codigo")
})
public class Fabricante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codfabricante")
	private Long codfabricante;
	
	@NotNull(message = "O campo Descrição é Obrigatorio")
	@Size(min = 10, max = 100, message = "Tamanho inválido para o campo Descrição(10 - 100)")
	@Column(name = "descricao", length = 100, nullable = false)
	private String descricao;
	
	public Long getCodfabricante() {
		return codfabricante;
	}

	public void setCodfabricante(Long codfabricante) {
		this.codfabricante = codfabricante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Fabricante [codfabricante=" + codfabricante + ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codfabricante == null) ? 0 : codfabricante.hashCode());
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
		Fabricante other = (Fabricante) obj;
		if (codfabricante == null) {
			if (other.codfabricante != null)
				return false;
		} else if (!codfabricante.equals(other.codfabricante))
			return false;
		return true;
	}
}
