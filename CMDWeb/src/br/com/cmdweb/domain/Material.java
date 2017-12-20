package br.com.cmdweb.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "materiais")
@NamedQueries({
	@NamedQuery(name = "Material.listar", query = "SELECT material FROM Material material"),
	@NamedQuery(name = "Material.buscar", query = "SELECT material FROM Material material WHERE material.codmaterial = :codigo")
})
public class Material {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codmaterial")
	private Long codmaterial;
	
	@NotNull(message = "O campo Descrição é Obrigatorio")
	@Size(min = 10, max = 100, message = "Tamanho inválido para o campo Descrição(7 - 100)")
	@Column(name = "descricao" , length = 100, nullable = false)
	private String descricao;
	
	@NotNull(message = "O campo Unidade é Obrigatorio")
	@Size(min = 2, max = 100, message = "Tamanho inválido para o campo Unidade(2 - 100)")
	@Column(name = "Unidade" , length = 10, nullable = false)
	private String unidade;
	
	@NotNull(message = "O campo Preço é Obrigatorio")
	@DecimalMin(value = "0.00", message = "Informe um valor maior ou igual a 0.00 para o campo Preço")
	@DecimalMax(value = "99999.99", message = "informe um valor menor que 99999.99 para o campo preço")
	@Column(name = "preço" , precision = 8, scale = 2, nullable = false)
	private BigDecimal preco;
	
	
	@NotNull(message = "O campo Opcional é Obrigatorio")
	@Size(min = 3, max = 100, message = "Tamanho inválido para o campo Opcional(3 - 100)")
	@Column(name = "opcional", length = 3, nullable = false)
	private String opcional;
	
	@NotNull(message = "O campo Quantidade é Obrigatorio")
	@Min(value = 0, message = "informe um valor maior ou igual a zero para o campo Quantidade")
	@Max(value = 9999, message = "informeum valor menor que dez mil para o campo Quantidade")
	@Column(name = "quantidade", nullable = false)
	private Long quantidade;
	
	@NotNull(message = "O campo Tipo é Obrigatorio")
	@Size(min = 1, message = "Tamanho inválido para o campo Tipo(min 1)")
	@Column(name = "tipo", length = 100, nullable = false)
	private String tipo;
	
	@NotNull(message = "O campo Quantidade minima é Obrigatorio")
	@Min(value = 0, message = "informe um valor maior ou igual a zero para o campo Quantidade")
	@Max(value = 9999, message = "informeum valor menor que dez mil para o campo Quantidade")
	@Column(name = "quantidademinima")
	private Integer qtd_min;
	
	@NotNull(message = "O campo Fabricante minima é Obrigatorio")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codfabricante", referencedColumnName = "codfabricante", nullable = false)
	private Fabricante fabricante;

	@NotNull(message = "O campo Constante por metro é Obrigatorio")
	@DecimalMin(value = "0.00", message = "Informe um valor maior ou igual a 0.00000 para o campo Preço")
	@DecimalMax(value = "1", message = "informe um valor menor que 1 para o campo preço")
	@Column(name = "constantemetro" , precision = 5, scale = 5, nullable = false)
	private BigDecimal constantemetro;
	
	
	public BigDecimal getConstantemetro() {
		return constantemetro;
	}

	public void setConstantemetro(BigDecimal constantemetro) {
		this.constantemetro = constantemetro;
	}

	public Long getCodmaterial() {
		return codmaterial;
	}

	public void setCodmaterial(Long codmaterial) {
		this.codmaterial = codmaterial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getOpcional() {
		return opcional;
	}

	public void setOpcional(String opcional) {
		this.opcional = opcional;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getQtd_min() {
		return qtd_min;
	}

	public void setQtd_min(Integer qtd_min) {
		this.qtd_min = qtd_min;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codmaterial == null) ? 0 : codmaterial.hashCode());
		result = prime * result + ((constantemetro == null) ? 0 : constantemetro.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((opcional == null) ? 0 : opcional.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((qtd_min == null) ? 0 : qtd_min.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		Material other = (Material) obj;
		if (codmaterial == null) {
			if (other.codmaterial != null)
				return false;
		} else if (!codmaterial.equals(other.codmaterial))
			return false;
		if (constantemetro == null) {
			if (other.constantemetro != null)
				return false;
		} else if (!constantemetro.equals(other.constantemetro))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (opcional == null) {
			if (other.opcional != null)
				return false;
		} else if (!opcional.equals(other.opcional))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (qtd_min == null) {
			if (other.qtd_min != null)
				return false;
		} else if (!qtd_min.equals(other.qtd_min))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Material [codmaterial=" + codmaterial + ", descricao=" + descricao + ", unidade=" + unidade + ", preco="
				+ preco + ", opcional=" + opcional + ", quantidade=" + quantidade + ", tipo=" + tipo + ", qtd_min="
				+ qtd_min + ", fabricante=" + fabricante + ", constantemetro=" + constantemetro + "]";
	}


	
}
