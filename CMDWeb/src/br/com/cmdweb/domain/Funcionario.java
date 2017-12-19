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
	@Size(min = 7, max = 40, message = "Tamanho inválido para o campo Login(5 - 40)")
	@Column(name = "Login", length = 40, nullable = false)
	private String login;
	
	@NotNull(message = "O campo Senha é obrigatorio")
	@Size(min = 7, max = 40, message = "Tamanho inválido para o campo Senha(5 - 40)")
	@Column(name = "Senha", length = 40, nullable = false)
	private String senha;

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
		return "Funcionario [codfuncionario=" + codfuncionario + ", login=" + login + ", senha=" + senha + "]";
	}
	
	
}
