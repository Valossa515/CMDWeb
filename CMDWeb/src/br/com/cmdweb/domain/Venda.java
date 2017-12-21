package br.com.cmdweb.domain;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "vendas")
@NamedQueries({
	@NamedQuery(name = "Venda.listar", query = "SELECT venda FROM Venda venda"),
	@NamedQuery(name = "Venda.buscar", query = "SELECT venda FROM Venda venda WHERE venda.codvenda = :codigo")
})
public class Venda 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codvenda")
	private Long codvenda;
	
	@Column(name = "horario", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date horario;
	
	@Column(name = "valor_total", precision = 8, scale = 2)
	private BigDecimal valor_total;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codfuncionario", referencedColumnName = "codfuncionario", nullable = false)
	private Funcionario funcionario;

	public Long getCodvenda() {
		return codvenda;
	}

	public void setCodvenda(Long codvenda) {
		this.codvenda = codvenda;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codvenda == null) ? 0 : codvenda.hashCode());
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((valor_total == null) ? 0 : valor_total.hashCode());
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
		Venda other = (Venda) obj;
		if (codvenda == null) {
			if (other.codvenda != null)
				return false;
		} else if (!codvenda.equals(other.codvenda))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (valor_total == null) {
			if (other.valor_total != null)
				return false;
		} else if (!valor_total.equals(other.valor_total))
			return false;
		return true;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Venda [codvenda=" + codvenda + ", horario=" + horario + ", valor_total=" + valor_total
				+ ", funcionario=" + funcionario + "]";
	}
	
	
}
