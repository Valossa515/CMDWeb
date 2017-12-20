package br.com.cmdweb.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cmdweb.DAO.FabricanteDAO;
import br.com.cmdweb.DAO.MaterialDAO;
import br.com.cmdweb.domain.Fabricante;
import br.com.cmdweb.domain.Material;
import br.com.cmdweb.util.FacesUtil;

@ManagedBean
@ViewScoped
public class MaterialBean {
	
	private Material material;
	private List<Material> Lista_material;
	private List<Fabricante> lista_materiais_filtrados;
	private String acao;
	private Long codigo;
	private List<Fabricante> lista_fabricante;
	
	
	public List<Fabricante> getLista_fabricante() {
		return lista_fabricante;
	}
	public void setLista_fabricante(List<Fabricante> lista_fabricante) {
		this.lista_fabricante = lista_fabricante;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public List<Material> getLista_material() {
		return Lista_material;
	}
	public void setLista_material(List<Material> lista_material) {
		Lista_material = lista_material;
	}
	public List<Fabricante> getLista_materiais_filtrados() {
		return lista_materiais_filtrados;
	}
	public void setLista_materiais_filtrados(List<Fabricante> lista_materiais_filtrados) {
		this.lista_materiais_filtrados = lista_materiais_filtrados;
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
			
			MaterialDAO dao = new MaterialDAO();
			dao.Salvar(material);
			
			material = new Material();
			
			FacesUtil.MsgInfo("Material salvo com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao cadastrar Material: " + e.getMessage() + "Codigo já cadastrado");
		}
	}
	
	public void novo()
	{
		material = new Material();
	}
	
	public void carregarPesquisa()
	{
		try
		{
			MaterialDAO dao = new MaterialDAO();
			Lista_material = dao.Listar();
		}
		catch(RuntimeException e)
		{
			FacesUtil.MsgErro("Erro ao listar Material: " + e.getMessage());
		}
	}
	
	public void carregarCadastro()
	{
		try
		{
			
			if(codigo != null)
			{
			    MaterialDAO dao = new MaterialDAO();
				material = dao.BuscarporCodigo(codigo);
			}
			else
			{
				material = new Material();
			}
			FabricanteDAO dao = new FabricanteDAO();
			lista_fabricante = dao.Listar();
		}
		catch(RuntimeException e)
		{
			FacesUtil.MsgErro("Erro ao obter dados do Material: " + e.getMessage());
		}
		
	}
	
	public void ExcluirBean()
	{
		try 
		{
			MaterialDAO dao = new MaterialDAO();
			dao.Excluir(material);			
			FacesUtil.MsgInfo("Material excluido com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao excluir Material: " + e.getMessage());
		}
		
	}
	public void EditarBean()
	{
		try 
		{
			
			MaterialDAO dao = new MaterialDAO();
			dao.Editar(material);
			FacesUtil.MsgInfo("Material editado com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao editar Material: " + e.getMessage());
		}
	}
}
