package br.com.cmdweb.test;

import org.junit.Test;

import br.com.cmdweb.util.HibernateUtil;

public class GerarTabelasTest 
{
	@Test
	public void gerar()
	{
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}

}
