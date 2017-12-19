package br.com.cmdweb.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.cmdweb.domain.Fabricante;
import br.com.cmdweb.util.HibernateUtil;

public class FabricanteDAO {
	
	public void Salvar(Fabricante fab) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.save(fab);
			trans.commit();
		}
		catch (RuntimeException e) 
		{
			if (trans != null) 
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
	public List<Fabricante> Listar() 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		List<Fabricante> fabs = null;
		try 
		{
			Query consulta = sessão.getNamedQuery("Fabricantes.listar");
			fabs = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sessão.close();
		}
		return fabs;

	}

	public Fabricante BuscarporCodigo(Long codigo) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Fabricante fabs = null;

		try 
		{
			Query consulta = sessão.getNamedQuery("Fabricantes.buscar");
			consulta.setLong("codigo", codigo);
			fabs = (Fabricante) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sessão.close();
		}
		return fabs;
	}

	public void Excluir(Fabricante fab) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.delete(fab);
			trans.commit();
		} 
		catch (RuntimeException e) 
		{
			if (trans != null) 
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

	public void ExcluirporCodigo(Long codigo) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			Fabricante fabs = BuscarporCodigo(codigo);
			sessão.delete(fabs);
			trans.commit();
		} 
		catch (RuntimeException e) 
		{
			if (trans != null) 
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

	public void Editar(Fabricante fab) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sessão.beginTransaction();
			sessão.update(fab);
			trans.commit();
		} 
		catch (RuntimeException e) 
		{
			if (trans != null) 
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
