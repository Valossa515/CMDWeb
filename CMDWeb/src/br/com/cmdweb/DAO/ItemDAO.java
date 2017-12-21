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
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.save(item);
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
	public List<Item> Listar()
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		List<Item> item = null;
		try 
		{
			Query consulta = sessão.getNamedQuery("Item.listar");
			item = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sessão.close();
		}
		return item;
	}
	public Item BuscarporCodigo(Long codigo) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Item item = null;

		try 
		{
			Query consulta = sessão.getNamedQuery("Item.buscar");
			consulta.setLong("codigo", codigo);
			item = (Item) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sessão.close();
		}
		return item;
	}
	public void Excluir(Item item) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.delete(item);
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
	public void Editar(Item item) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sessão.beginTransaction();
			sessão.update(item);
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
