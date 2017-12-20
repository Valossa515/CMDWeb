package br.com.cmdweb.main;

import br.com.cmdweb.util.HibernateUtil;

public class GerarTabela {

	public static void main(String[] args) 
	{
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}
}
