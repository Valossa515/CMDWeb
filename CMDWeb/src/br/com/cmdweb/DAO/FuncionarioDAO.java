package br.com.cmdweb.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.cmdweb.domain.Funcionario;
import br.com.cmdweb.util.HibernateUtil;

public class FuncionarioDAO 
{
	public void Salvar(Funcionario f)
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		
		Transaction trans = null;
		try
		{
			trans = sess�o.beginTransaction();
			sess�o.save(f);
			trans.commit();	
		}
		catch(RuntimeException e)
		{
			if(trans != null)
			{
				trans.rollback();
			}
			throw e;
		}
		finally
		{
			sess�o.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Funcionario> Listar()
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		List<Funcionario> func = null;
		try
		{
			Query consulta = sess�o.getNamedQuery("Funcionario.listar");
			func = consulta.list();
		}
		catch(RuntimeException e)
		{
			throw e;
		}
		finally
		{
			sess�o.close();
		}
		return func;
	}
	public Funcionario BuscarporCodigo(int codigo)
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Funcionario func = null;
		try
		{
			Query consulta = sess�o.getNamedQuery("Funcionario.buscar");
			consulta.setInteger("codigo", codigo);	
			func = (Funcionario) consulta.uniqueResult();
		}
		catch(RuntimeException e)
		{
			throw e;
		}
		finally
		{
			sess�o.close();
		}
		return func;
	}
	public void Excluir(Funcionario f)
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		
		Transaction trans = null;
		try
		{
			trans = sess�o.beginTransaction();
			sess�o.delete(f);
			trans.commit();	
		}
		catch(RuntimeException e)
		{
			if(trans != null)
			{
				trans.rollback();
			}
			throw e;
		}
		finally
		{
			sess�o.close();
		}
	}
	public void Excluir(int codigo)
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		
		Transaction trans = null;
		try
		{
			trans = sess�o.beginTransaction();
			Funcionario func = BuscarporCodigo(codigo);
			sess�o.delete(func);
			trans.commit();	
		}
		catch(RuntimeException e)
		{
			if(trans != null)
			{
				trans.rollback();
			}
			throw e;
		}
		finally
		{
			sess�o.close();
		}
	}
	public void Editar(Funcionario f)
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		
		Transaction trans = null;
		try
		{
			trans = sess�o.beginTransaction();
			sess�o.update(f);
			trans.commit();	
		}
		catch(RuntimeException e)
		{
			if(trans != null)
			{
				trans.rollback();
			}
			throw e;
		}
		finally
		{
			sess�o.close();
		}
	}
}
