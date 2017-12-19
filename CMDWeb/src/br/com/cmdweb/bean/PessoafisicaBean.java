package br.com.cmdweb.bean;

import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.cmdweb.DAO.PessoaFisicaDAO;
import br.com.cmdweb.domain.PessoaFisica;
import br.com.cmdweb.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PessoafisicaBean {
	
	private PessoaFisica pfisica;
	private List<PessoaFisica> Lista_pfisica;
	private List<PessoaFisica> lista_pfisica_filtrados;
	private String acao;
	private String cpf;
	
	public PessoaFisica getPfisica() {
		return pfisica;
	}
	public void setPfisica(PessoaFisica pfisica) {
		this.pfisica = pfisica;
	}
	public List<PessoaFisica> getLista_pfisica() {
		return Lista_pfisica;
	}
	public void setLista_pfisica(List<PessoaFisica> lista_pfisica) {
		Lista_pfisica = lista_pfisica;
	}
	public List<PessoaFisica> getLista_pfisica_filtrados() {
		return lista_pfisica_filtrados;
	}
	public void setLista_pfisica_filtrados(List<PessoaFisica> lista_pfisica_filtrados) {
		this.lista_pfisica_filtrados = lista_pfisica_filtrados;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	public void SalvarBean()
	{
		try 
		{
			
			PessoaFisicaDAO dao = new PessoaFisicaDAO();
			dao.Salvar(pfisica);
			
			pfisica = new PessoaFisica();
			
			FacesUtil.MsgInfo("Cliente salvo com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao cadastrar Cliente: " + e.getMessage() + "CPF já cadastrado");
		}
	}
	public void novo()
	{
		pfisica = new PessoaFisica();
	}
	
	public void carregarPesquisa()
	{
		try
		{
			PessoaFisicaDAO dao = new PessoaFisicaDAO();
			Lista_pfisica = dao.Listar();
		}
		catch(RuntimeException e)
		{
			FacesUtil.MsgErro("Erro ao listar Clientes: " + e.getMessage());
		}
	}
	public void carregarCadastro()
	{
		try
		{
			
			if(cpf != null)
			{
			     PessoaFisicaDAO dao = new PessoaFisicaDAO();
				pfisica = dao.BuscarporCPF(cpf);
			}
			else
			{
				pfisica = new PessoaFisica();
			}
		}
		catch(RuntimeException e)
		{
			FacesUtil.MsgErro("Erro ao obter dados do Cliente: " + e.getMessage());
		}
	}
	
	public void ExcluirBean()
	{
		try 
		{
			PessoaFisicaDAO dao = new PessoaFisicaDAO();
			dao.Excluir(pfisica);			
			FacesUtil.MsgInfo("Cliente excluido com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao excluir Cliente: " + e.getMessage());
		}
		
	}
	public void EditarBean()
	{
		try 
		{
			
			PessoaFisicaDAO dao = new PessoaFisicaDAO();
			dao.Editar(pfisica);
			FacesUtil.MsgInfo("Cliente editado com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao editar Cliente: " + e.getMessage());
		}
	}
	
	
}
