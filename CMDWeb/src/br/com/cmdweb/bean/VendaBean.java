package br.com.cmdweb.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cmdweb.DAO.MaterialDAO;
import br.com.cmdweb.domain.Item;
import br.com.cmdweb.domain.Material;
import br.com.cmdweb.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean 
{
	private Material material;
	private List<Material> Lista_material;
	private List<Material> lista_materiais_filtrados;
	private List<Item> lista_itens;
	
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
	}
}
