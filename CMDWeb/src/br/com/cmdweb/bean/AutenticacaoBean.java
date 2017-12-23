package br.com.cmdweb.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.cmdweb.DAO.FuncionarioDAO;
import br.com.cmdweb.domain.Funcionario;
import br.com.cmdweb.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Funcionario func;

	public Funcionario getFunc() {
		if(func == null)
		{
			func = new Funcionario();
		}
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}
	
	public String entrar()
	{
		try
		{
			FuncionarioDAO dao = new FuncionarioDAO();
			func = dao.Logar(func.getLogin(), func.getSenha());
			
			if(func == null)
			{
				FacesUtil.MsgErro("Erro ao tentar entrar no sistema: ");
				return null;
			}
			else
			{
				FacesUtil.MsgInfo("Logado com sucesso!!!");
				return "/pages/principal.xhtml?faces-redirect=true";
			}
		}
		catch(RuntimeException e)
		{
			FacesUtil.MsgErro("Erro ao tentar entrar no sistema: " + e.getMessage() + "Login e/ou Senha incorretos");
			return null;
		}
	}
	public String deslogar()
	{
		func = null;
		return "/pages/Login.xhtml?faces-redirect=true";
	}
}
