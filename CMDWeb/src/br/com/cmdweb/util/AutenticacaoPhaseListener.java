package br.com.cmdweb.util;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import br.com.cmdweb.bean.AutenticacaoBean;
import br.com.cmdweb.domain.Funcionario;


@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		UIViewRoot root = context.getViewRoot();
		String pagina_atual = root.getViewId();
		boolean eh_pagina_auth = pagina_atual.contains("Login.xhtml");
		
		if(!eh_pagina_auth)
		{
			ExternalContext external = context.getExternalContext();
			Map<String, Object> mapa = external.getSessionMap();
			AutenticacaoBean auth = (AutenticacaoBean)mapa.get("autenticacaoBean");
			
			Funcionario func = auth.getFunc();
			
			if(func.getFuncao() == null)
			{
				FacesUtil.MsgErro("Funcionário não autenticacdo. Favor efetue seu Login.");
				
				Application app = context.getApplication();
				NavigationHandler handler = app.getNavigationHandler();
				handler.handleNavigation(context, null, "/pages/Login.xhtml?faces-redirect=true");
			}
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
		
	}
	
}
