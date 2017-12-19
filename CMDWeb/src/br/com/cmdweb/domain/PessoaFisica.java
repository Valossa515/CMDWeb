package br.com.cmdweb.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "pessoasfisicas")
@NamedQueries({
	@NamedQuery(name = "PessoaFisica.listar", query = "SELECT pessoafisica FROM PessoaFisica pessoafisica"),
	@NamedQuery(name = "PessoaFisica.buscar", query = "SELECT pessoafisica FROM PessoaFisica pessoafisica WHERE pessoafisica.cpf = :cpf")
})
public class PessoaFisica {
	
	@Id
	@CPF
	@Column(name = "cpf", length = 14, nullable = false )
	private String cpf;
	
	
	@NotNull(message = "O campo Nome é Obrigatorio")
	@Size(min = 7, max = 100, message = "Tamanho inválido para o campo Nome(7 - 100)")
	@Column(name = "nome", length = 40, nullable = true)
	private String nome;
	
	@NotNull(message = "O campo Data de nascimento é Obrigatorio")
	@Column(name = "datanascimento", nullable = true)
	private String datanascimento;
	
	@NotNull(message = "O campo Endereço é Obrigatorio")
	@Size(min = 7, max = 100, message = "Tamanho inválido para o campo Logradouro(7 - 100)")
	@Column(name = "logradouro", length = 100, nullable = true)
	private String logradouro;
	
	@NotNull(message = "O campo Numero é Obrigatorio")
	@Size(min = 1 , message = "Tamanho inválido para o campo Numero(1 +)")
	@Column(name = "numero", length = 100, nullable = true)
	private String numero;
	
	@NotNull(message = "O campo CEP é Obrigatorio")
	@Size(max = 9, message = "Tamanho inválido para o campo CEP(9)")
	@Column(name = "cep", length = 100, nullable = true)
	private String cep;
	
	@NotNull(message = "O campo Uf é Obrigatorio")
	@Size(min = 2, message = "Tamanho inválido para o campo Uf(2)")
	@Column(name = "uf", length = 100, nullable = true)
	private String uf;
	
	@NotNull(message = "O campo Complemto é Obrigatorio")
	@Size(min = 7, max = 100, message = "Tamanho inválido para o campo Complemento(7 - 100)")
	@Column(name = "complemento", length = 100, nullable = true)
	private String complemto;
	
	@NotNull(message = "O campo Bairro é Obrigatorio")
	@Size(min = 7, max = 100, message = "Tamanho inválido para o campo Biarro(7 - 100)")
	@Column(name = "bairro", length = 100, nullable = true)
	private String bairro;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getComplemto() {
		return complemto;
	}

	public void setComplemto(String complemto) {
		this.complemto = complemto;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Override
	public String toString() {
		return "PessoaFisica [cpf=" + cpf + ", nome=" + nome + ", datanascimento=" + datanascimento + ", logradouro="
				+ logradouro + ", numero=" + numero + ", cep=" + cep + ", uf=" + uf + ", complemto=" + complemto
				+ ", bairro=" + bairro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((complemto == null) ? 0 : complemto.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((datanascimento == null) ? 0 : datanascimento.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		PessoaFisica other = (PessoaFisica) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (complemto == null) {
			if (other.complemto != null)
				return false;
		} else if (!complemto.equals(other.complemto))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (datanascimento == null) {
			if (other.datanascimento != null)
				return false;
		} else if (!datanascimento.equals(other.datanascimento))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}
}
