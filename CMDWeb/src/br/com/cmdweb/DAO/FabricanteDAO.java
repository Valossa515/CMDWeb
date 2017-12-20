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
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.save(fab);
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
			sess�o.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> Listar() 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		List<Fabricante> fabs = null;
		try 
		{
			Query consulta = sess�o.getNamedQuery("Fabricantes.listar");
			fabs = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sess�o.close();
		}
		return fabs;

	}

	public Fabricante BuscarporCodigo(Long codigo) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Fabricante fabs = null;

		try 
		{
			Query consulta = sess�o.getNamedQuery("Fabricantes.buscar");
			consulta.setLong("codigo", codigo);
			fabs = (Fabricante) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sess�o.close();
		}
		return fabs;
	}

	public void Excluir(Fabricante fab) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.delete(fab);
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
			sess�o.close();
		}
	}

	public void ExcluirporCodigo(Long codigo) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			Fabricante fabs = BuscarporCodigo(codigo);
			sess�o.delete(fabs);
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
			sess�o.close();
		}

	}

	public void Editar(Fabricante fab) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.update(fab);
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
			sess�o.close();
		}
	}
}
