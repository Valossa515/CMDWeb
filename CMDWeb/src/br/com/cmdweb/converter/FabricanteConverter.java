package br.com.cmdweb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.cmdweb.DAO.FabricanteDAO;
import br.com.cmdweb.domain.Fabricante;

@FacesConverter("fabricanteConverter")
public class FabricanteConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facescontext, UIComponent component, String val) {
		
		try
		{
			Long codigo = Long.parseLong(val);
			FabricanteDAO dao = new FabricanteDAO();
			Fabricante fab = dao.BuscarporCodigo(codigo);
			return fab;
		}
		catch(RuntimeException e)
		{
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facescontext, UIComponent component, Object obj) {
		try
		{
			Fabricante fab = (Fabricante) obj;
			Long codigo = fab.getCodfabricante();
			return codigo.toString();
		}
		catch(RuntimeException e)
		{
			return null;
		}
	}

}
