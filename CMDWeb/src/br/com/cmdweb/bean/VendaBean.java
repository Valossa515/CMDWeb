package br.com.cmdweb.bean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.cmdweb.DAO.FuncionarioDAO;
import br.com.cmdweb.DAO.ItemDAO;
import br.com.cmdweb.DAO.MaterialDAO;
import br.com.cmdweb.DAO.VendaDAO;
import br.com.cmdweb.domain.Funcionario;
import br.com.cmdweb.domain.Item;
import br.com.cmdweb.domain.Material;
import br.com.cmdweb.domain.Venda;
import br.com.cmdweb.filter.VendaFilter;
import br.com.cmdweb.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean 
{
	private Material material;
	private List<Material> Lista_material;
	private List<Material> lista_materiais_filtrados;
	private List<Item> lista_itens;
	private List<Venda> listar_venda_filtradas;
	private Venda venda;
	private VendaFilter filter;
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean auth;
	public VendaFilter getFilter() {
		if(filter == null)
		{
			filter = new VendaFilter();
		}
		return filter;
	}
	
	
	public List<Venda> getListar_venda_filtradas() {
		return listar_venda_filtradas;
	}


	public void setListar_venda_filtradas(List<Venda> listar_venda_filtradas) {
		this.listar_venda_filtradas = listar_venda_filtradas;
	}


	public void setFilter(VendaFilter filter) {
		this.filter = filter;
	}

	public AutenticacaoBean getAuth() {
		return auth;
	}

	public void setAuth(AutenticacaoBean auth) {
		this.auth = auth;
	}

	public Venda getVenda() 
	{
		if(venda == null)
		{
			venda = new Venda();
			venda.setValor_total(new BigDecimal("0.00"));
		}
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public List<Item> getLista_itens() {
		if(lista_itens == null)
		{
			lista_itens = new ArrayList<>();
		}
		return lista_itens;
	}
	public void setLista_itens(List<Item> lista_itens) {
		this.lista_itens = lista_itens;
	}
	public List<Material> getLista_materiais_filtrados() {
		return lista_materiais_filtrados;
	}
	public void setLista_materiais_filtrados(List<Material> lista_materiais_filtrados) {
		this.lista_materiais_filtrados = lista_materiais_filtrados;
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
	public void adicionar(Material mat)
	{
		int pos_econtrada = -1;
		for(int pos = 0; pos < lista_itens.size() && pos_econtrada < 0; pos++)
		{
			Item temp = lista_itens.get(pos);
			if(temp.getMaterial().equals(mat))
			{
				pos_econtrada = pos;
				
			}
		}
		
		Item item = new Item();
		item.setMaterial(mat);
		
		if(pos_econtrada < 0)
		{
			item.setQuantidade(1);
			item.setValor_parcial(mat.getPreco());
			lista_itens.add(item);
		}
		else
		{
			Item temp1 = lista_itens.get(pos_econtrada);
			item.setQuantidade(temp1.getQuantidade() + 1);
			item.setValor_parcial(mat.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			lista_itens.set(pos_econtrada, item);
		}
		venda.setValor_total(venda.getValor_total().add(mat.getPreco()));
		
	}
	public void remover(Item item)
	{
		int pos_econtrada = -1;
		for(int pos = 0; pos < lista_itens.size() && pos_econtrada < 0; pos++)
		{
			Item temp = lista_itens.get(pos);
			if(temp.getMaterial().equals(item.getMaterial()))
			{
				pos_econtrada = pos;				
			}
		}
		if(pos_econtrada > -1)
		{
			lista_itens.remove(pos_econtrada);
			venda.setValor_total(venda.getValor_total().subtract(item.getValor_parcial()));
		}
	}
	public void carregarvenda()
	{
		venda.setHorario(new Date());
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f = dao.BuscarporCodigo(auth.getFunc().getCodfuncionario());
		venda.setFuncionario(f);
	}
	public void salvar()
	{
		try 
		{
			VendaDAO dao =  new VendaDAO();
			Long cod = dao.Salvar(venda);
			Venda FK = dao.BuscarporCodigo(cod);
			for(Item it : lista_itens)
			{
				it.setVenda(FK);
				ItemDAO daoi = new ItemDAO();
				daoi.Salvar(it);
			}
			venda = new Venda();
			venda.setValor_total(new BigDecimal("0.00"));
			lista_itens = new ArrayList<>();	
			
			FacesUtil.MsgInfo("Venda salva com sucesso!!!");
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao realizar Venda: " + e.getMessage() + "Codigo já cadastrado");
		}
	}
	public void filtrar()
	{
		try 
		{
			VendaDAO dao =  new VendaDAO();
			listar_venda_filtradas = dao.FiltrarVenda(filter);
		}
		catch (RuntimeException e) 
		{
			FacesUtil.MsgErro("Erro ao listar Venda: " + e.getMessage() + "Intervalo de datas não encontrado!!!");
		}
	}
}
