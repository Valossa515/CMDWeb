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
@Table(name = "funcionarios")
@NamedQueries({
	@NamedQuery(name = "Funcionario.listar", query = "SELECT funcionario FROM Funcionario funcionario"),
	@NamedQuery(name = "Funcionario.buscar", query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.codfuncionario = :codigo")
})
public class Funcionario 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CodFuncionario")
	private int codfuncionario;
	
	@NotNull(message = "O campo Login é Obrigatorio")
	@Size(min = 7, max = 40, message = "Tamanho inválido para o campo Login(7 - 40)")
	@Column(name = "Login", length = 40, nullable = false)
	private String login;
	
	@NotNull(message = "O campo Senha é obrigatorio")
	@Size(min = 7, max = 40, message = "Tamanho inválido para o campo Senha(7 - 40)")
	@Column(name = "Senha", length = 40, nullable = false)
	private String senha;
	
	@NotNull(message = "O campo Função é obrigatorio")
	@Size(min = 7, max = 40, message = "Tamanho inválido para o campo Função(7 - 40)")
	@Column(name = "funcao", length = 40, nullable = false)
	private String funcao;
	
	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public int getCodfuncionario() {
		return codfuncionario;
	}

	public void setCodfuncionario(int codfuncionario) {
		this.codfuncionario = codfuncionario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Funcionario [codfuncionario=" + codfuncionario + ", login=" + login + ", senha=" + senha + ", funcao="
				+ funcao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codfuncionario;
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (codfuncionario != other.codfuncionario)
			return false;
		if (funcao == null) {
			if (other.funcao != null)
				return false;
		} else if (!funcao.equals(other.funcao))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
}
