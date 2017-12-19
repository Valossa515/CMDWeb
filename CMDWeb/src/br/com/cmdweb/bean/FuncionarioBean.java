package br.com.cmdweb.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;

import br.com.cmdweb.DAO.FuncionarioDAO;
import br.com.cmdweb.domain.Funcionario;
import br.com.cmdweb.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {
	
	private Funcionario func;
	private List<Funcionario> Lista_func;
	private List<Funcionario> lista_func_filtrados;
	private String acao;
	private int codigo;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public List<Funcionario> getLista_func() {
		return Lista_func;
	}

	public void setLista_func(List<Funcionario> lista_func) {
		Lista_func = lista_func;
	}

	public List<Funcionario> getLista_func_filtrados() {
		return lista_func_filtrados;
	}

	public void setLista_func_filtrados(List<Funcionario> lista_func_filtrados) {
		this.lista_func_filtrados = lista_func_filtrados;
	}

	public Funcionario getFunc() 
	{
		return func;
	}
	
	public void setFunc(Funcionario func) {
		
		this.func = func;
	}
	
	public void salvar()
	{
		try 
		{
			
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.Salvar(func);
			
			func = new Funcionario();
			
			FacesUtil.MsgInfo("Funcionário salvo com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao cadastrar funcionario: " + e.getMessage());
		}
		
	}
	public void novo()
	{
		func = new Funcionario();
	}
	
	public void carregarPesquisa()
	{
		try
		{
			FuncionarioDAO dao = new FuncionarioDAO();
			Lista_func = dao.Listar();
		}
		catch(RuntimeException e)
		{
			FacesUtil.MsgErro("Erro ao listar funcionario: " + e.getMessage());
		}
	}
	public void carregarCadastro()
	{
		try
		{
			
			if(codigo != 0)
			{
				FuncionarioDAO dao = new FuncionarioDAO();
				func = dao.BuscarporCodigo(codigo);
			}
			else
			{
				func = new Funcionario();
			}
		}
		catch(RuntimeException e)
		{
			FacesUtil.MsgErro("Erro ao obter dados do funcionario: " + e.getMessage());
		}
	}
	
	public void excluir()
	{
		try 
		{
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.Excluir(func);			
			FacesUtil.MsgInfo("Funcionário excluido com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao excluir funcionario: " + e.getMessage());
		}
		
	}
	public void editar()
	{
		try 
		{
			
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.Editar(func);
			FacesUtil.MsgInfo("Funcionário editado com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao editar funcionario: " + e.getMessage());
		}
	}
	
}
