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
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		
		Transaction trans = null;
		try
		{
			trans = sessão.beginTransaction();
			sessão.save(f);
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
			sessão.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Funcionario> Listar()
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		List<Funcionario> func = null;
		try
		{
			Query consulta = sessão.getNamedQuery("Funcionario.listar");
			func = consulta.list();
		}
		catch(RuntimeException e)
		{
			throw e;
		}
		finally
		{
			sessão.close();
		}
		return func;
	}
	public Funcionario BuscarporCodigo(int codigo)
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Funcionario func = null;
		try
		{
			Query consulta = sessão.getNamedQuery("Funcionario.buscar");
			consulta.setInteger("codigo", codigo);	
			func = (Funcionario) consulta.uniqueResult();
		}
		catch(RuntimeException e)
		{
			throw e;
		}
		finally
		{
			sessão.close();
		}
		return func;
	}
	public void Excluir(Funcionario f)
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		
		Transaction trans = null;
		try
		{
			trans = sessão.beginTransaction();
			sessão.delete(f);
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
			sessão.close();
		}
	}
	public void Excluir(int codigo)
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		
		Transaction trans = null;
		try
		{
			trans = sessão.beginTransaction();
			Funcionario func = BuscarporCodigo(codigo);
			sessão.delete(func);
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
			sessão.close();
		}
	}
	public void Editar(Funcionario f)
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		
		Transaction trans = null;
		try
		{
			trans = sessão.beginTransaction();
			sessão.update(f);
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
			sessão.close();
		}
	}
}
