package br.com.cmdweb.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.cmdweb.domain.Item;
import br.com.cmdweb.util.HibernateUtil;

public class ItemDAO {
	public void Salvar(Item item) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.save(item);
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
	public List<Item> Listar()
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		List<Item> item = null;
		try 
		{
			Query consulta = sess�o.getNamedQuery("Item.listar");
			item = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sess�o.close();
		}
		return item;
	}
	public Item BuscarporCodigo(Long codigo) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Item item = null;

		try 
		{
			Query consulta = sess�o.getNamedQuery("Item.buscar");
			consulta.setLong("codigo", codigo);
			item = (Item) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sess�o.close();
		}
		return item;
	}
	public void Excluir(Item item) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.delete(item);
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
	public void Editar(Item item) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.update(item);
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
