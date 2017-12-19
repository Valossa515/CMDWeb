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
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.save(material);
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
	public List<Material> Listar()
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		List<Material> material = null;
		try 
		{
			Query consulta = sessão.getNamedQuery("Material.listar");
			material = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sessão.close();
		}
		return material;

	}
	public Material BuscarporCodigo(Long codigo) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Material material = null;

		try 
		{
			Query consulta = sessão.getNamedQuery("Material.buscar");
			consulta.setLong("codigo", codigo);
			material = (Material) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sessão.close();
		}
		return material;
	}
	public void Excluir(Material material) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.delete(material);
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
	public void Editar(Material material) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sessão.beginTransaction();
			sessão.update(material);
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
