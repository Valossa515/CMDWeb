package br.com.cmdweb.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.cmdweb.domain.Material;
import br.com.cmdweb.util.HibernateUtil;

public class MaterialDAO {
	public void Salvar(Material material) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.save(material);
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
	public List<Material> Listar()
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		List<Material> material = null;
		try 
		{
			Query consulta = sess�o.getNamedQuery("Material.listar");
			material = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sess�o.close();
		}
		return material;

	}
	public Material BuscarporCodigo(Long codigo) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Material material = null;

		try 
		{
			Query consulta = sess�o.getNamedQuery("Material.buscar");
			consulta.setLong("codigo", codigo);
			material = (Material) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sess�o.close();
		}
		return material;
	}
	public void Excluir(Material material) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.delete(material);
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
	public void Editar(Material material) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.update(material);
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
