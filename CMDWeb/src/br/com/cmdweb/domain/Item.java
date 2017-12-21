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

@Entity
@Table(name = "item")
@NamedQueries({
	@NamedQuery(name = "Item.listar", query = "SELECT item FROM Item item"),
	@NamedQuery(name = "Item.buscar", query = "SELECT item FROM Item item WHERE item.coditem = :codigo")
})
public class Item 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "coditem")
	private Long coditem;
	
	@Column(name = "valor_parcial", precision = 8, scale = 2, nullable = false)
	private BigDecimal valor_parcial;
	
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codvenda", referencedColumnName = "codvenda", nullable = false)
	private Venda venda;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codmaterial", referencedColumnName = "codmaterial", nullable = false)
	private Material material;
	public Long getCoditem() {
		return coditem;
	}
	public void setCoditem(Long coditem) {
		this.coditem = coditem;
	}
	public BigDecimal getValor_parcial() {
		return valor_parcial;
	}
	public void setValor_parcial(BigDecimal valor_parcial) {
		this.valor_parcial = valor_parcial;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	@Override
	public String toString() {
		return "Item [coditem=" + coditem + ", valor_parcial=" + valor_parcial + ", quantidade=" + quantidade
				+ ", venda=" + venda + ", material=" + material + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coditem == null) ? 0 : coditem.hashCode());
		result = prime * result + ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((valor_parcial == null) ? 0 : valor_parcial.hashCode());
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		Item other = (Item) obj;
		if (coditem == null) {
			if (other.coditem != null)
				return false;
		} else if (!coditem.equals(other.coditem))
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (valor_parcial == null) {
			if (other.valor_parcial != null)
				return false;
		} else if (!valor_parcial.equals(other.valor_parcial))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}
}
