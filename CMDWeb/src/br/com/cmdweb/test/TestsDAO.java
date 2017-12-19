package br.com.cmdweb.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.cmdweb.DAO.FuncionarioDAO;
import br.com.cmdweb.DAO.PessoaFisicaDAO;
import br.com.cmdweb.domain.Funcionario;
import br.com.cmdweb.domain.PessoaFisica;

public class TestsDAO 

{
	@Test
	@Ignore
	public void funcionariosalvar()
	{
		Funcionario f = new Funcionario();
		f.setLogin("Felipe");
		f.setSenha("6282542");
		
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.Salvar(f);
	}
	@Test
	@Ignore
	public void listar()
	{
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> func = dao.Listar();
		
		for(Funcionario f : func)
		{
			System.out.println(f);
		}
	}
	@Test
	@Ignore
	public void buscar()
	{
		Funcionario f = new Funcionario();
		FuncionarioDAO dao = new FuncionarioDAO();
		f = dao.BuscarporCodigo(1);
		System.out.println(f);
	}
	
	@Test
	@Ignore
	public void editar()
	{
		Funcionario f = new Funcionario();
		f.setCodfuncionario(1);
		f.setLogin("Felipe Martins");
		f.setSenha("felipe515");
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.Editar(f);
	}
	@Test
	public void addpessoa()
	{
		PessoaFisica p = new PessoaFisica();
		p.setCpf("442.446.478-48");
		p.setCep("09520660");
		p.setBairro("Fundação");
		p.setComplemto("apto 11");
		p.setLogradouro("Rua Perrella");
		p.setDatanascimento("01-06-1994");
		p.setNome("Felipe Martins");
		p.setNumero("145");
		p.setUf("SP");
		
		PessoaFisicaDAO dao = new PessoaFisicaDAO();
		dao.Salvar(p);
	}
}
