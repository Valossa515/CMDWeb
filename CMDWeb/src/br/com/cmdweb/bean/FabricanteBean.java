package br.com.cmdweb.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.cmdweb.DAO.FabricanteDAO;
import br.com.cmdweb.domain.Fabricante;
import br.com.cmdweb.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean {
	
	private Fabricante fabricante;
	private List<Fabricante> Lista_fabricante;
	private List<Fabricante> lista_fabricantes_filtrados;
	private String acao;
	private Long codigo;
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	public List<Fabricante> getLista_fabricante() {
		return Lista_fabricante;
	}
	public void setLista_fabricante(List<Fabricante> lista_fabricante) {
		Lista_fabricante = lista_fabricante;
	}
	public List<Fabricante> getLista_fabricantes_filtrados() {
		return lista_fabricantes_filtrados;
	}
	public void setLista_fabricantes_filtrados(List<Fabricante> lista_fabricantes_filtrados) {
		this.lista_fabricantes_filtrados = lista_fabricantes_filtrados;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
	public void SalvarBean()
	{
		try 
		{
			
			FabricanteDAO dao = new FabricanteDAO();
			dao.Salvar(fabricante);
			
			fabricante = new Fabricante();
			
			FacesUtil.MsgInfo("Fabricante salvo com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao cadastrar Fabricante: " + e.getMessage() + "Codigo já cadastrado");
		}
	}
	public void novo()
	{
		fabricante = new Fabricante();
	}
	
	public void carregarPesquisa()
	{
		try
		{
			FabricanteDAO dao = new FabricanteDAO();
			Lista_fabricante = dao.Listar();
		}
		catch(RuntimeException e)
		{
			FacesUtil.MsgErro("Erro ao listar Fabricante: " + e.getMessage());
		}
	}
	public void carregarCadastro()
	{
		try
		{
			
			if(codigo != null)
			{
			    FabricanteDAO dao = new FabricanteDAO();
				fabricante = dao.BuscarporCodigo(codigo);
			}
			else
			{
				fabricante = new Fabricante();
			}
		}
		catch(RuntimeException e)
		{
			FacesUtil.MsgErro("Erro ao obter dados do Fabricante: " + e.getMessage());
		}
	}
	
	public void ExcluirBean()
	{
		try 
		{
			FabricanteDAO dao = new FabricanteDAO();
			dao.Excluir(fabricante);			
			FacesUtil.MsgInfo("Fabricante excluido com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao excluir Fabricante: " + e.getMessage());
		}
		
	}
	public void EditarBean()
	{
		try 
		{
			
			FabricanteDAO dao = new FabricanteDAO();
			dao.Editar(fabricante);
			FacesUtil.MsgInfo("Fabricante editado com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao editar Fabricante: " + e.getMessage());
		}
	}
	
}
