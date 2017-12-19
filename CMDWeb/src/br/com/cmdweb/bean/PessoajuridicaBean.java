package br.com.cmdweb.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cmdweb.DAO.PessoaJuridicaDAO;
import br.com.cmdweb.domain.PessoaJuridica;
import br.com.cmdweb.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PessoajuridicaBean {
	
	private PessoaJuridica pjuridica;
	private List<PessoaJuridica> Lista_pjuridica;
	private List<PessoaJuridica> lista_pjuridica_filtrados;
	private String acao;
	private String cnpj;
	
		
	public PessoaJuridica getPjuridica() {
		return pjuridica;
	}
	public void setPjuridica(PessoaJuridica pjuridica) {
		this.pjuridica = pjuridica;
	}
	public List<PessoaJuridica> getLista_pjuridica() {
		return Lista_pjuridica;
	}
	public void setLista_pjuridica(List<PessoaJuridica> lista_pjuridica) {
		Lista_pjuridica = lista_pjuridica;
	}
	public List<PessoaJuridica> getLista_pjuridica_filtrados() {
		return lista_pjuridica_filtrados;
	}
	public void setLista_pjuridica_filtrados(List<PessoaJuridica> lista_pjuridica_filtrados) {
		this.lista_pjuridica_filtrados = lista_pjuridica_filtrados;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void SalvarBean()
	{
		try 
		{
			
			PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
			dao.Salvar(pjuridica);
			
			pjuridica = new PessoaJuridica();
			
			FacesUtil.MsgInfo("Cliente salvo com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao cadastrar Cliente: " + e.getMessage() + "CNPJ já cadastrado");
		}
	}
	public void novo()
	{
		pjuridica = new PessoaJuridica();
	}
	
	public void carregarPesquisa()
	{
		try
		{
			PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
			Lista_pjuridica = dao.Listar();
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
			
			if(cnpj != null)
			{
			     PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
				pjuridica = dao.BuscarporCNPJ(cnpj);
			}
			else
			{
				pjuridica = new PessoaJuridica();
			}
		}
		catch(RuntimeException e)
		{
			FacesUtil.MsgErro("Erro ao obter dados do funcionario: " + e.getMessage());
		}
	}
	
	public void ExcluirBean()
	{
		try 
		{
			PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
			dao.Excluir(pjuridica);			
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
			
			PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
			dao.Editar(pjuridica);
			FacesUtil.MsgInfo("Cliente editado com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao editar Cliente: " + e.getMessage());
		}
	}
	
}
