package br.com.cmdweb.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cmdweb.domain.Material;

@ManagedBean
@ViewScoped
public class VendaBean 
{
	private Material material;
	private List<Material> Lista_material;
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
}
