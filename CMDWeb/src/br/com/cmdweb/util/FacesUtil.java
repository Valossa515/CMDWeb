package br.com.cmdweb.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class FacesUtil {
	
	public static void MsgInfo(String mensagem) 
	{
		FacesMessage fmessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externa = context.getExternalContext();
		Flash flash = externa.getFlash();
		flash.setKeepMessages(true);
		context.addMessage(null, fmessage);
	}
	public static void MsgErro(String mensagem) 
	{
		FacesMessage fmessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externa = context.getExternalContext();
		Flash flash = externa.getFlash();
		flash.setKeepMessages(true);
		context.addMessage(null, fmessage);
	}
	public static String getParam(String nome)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalcontext = context.getExternalContext();
		
		Map<String, String >parametros = externalcontext.getRequestParameterMap();
		
		String valor = parametros.get(nome);
		
		return valor;
	}

}
