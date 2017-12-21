package br.com.cmdweb.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.cmdweb.domain.Venda;
import br.com.cmdweb.util.HibernateUtil;

public class VendaDAO {
	
	public void Salvar(Venda venda) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.save(venda);
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
	public List<Venda> Listar()
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		List<Venda> venda = null;
		try 
		{
			Query consulta = sessão.getNamedQuery("Venda.listar");
			venda = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sessão.close();
		}
		return venda;
	}
	public Venda BuscarporCodigo(Long codigo) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Venda venda = null;

		try 
		{
			Query consulta = sessão.getNamedQuery("Venda.buscar");
			consulta.setLong("codigo", codigo);
			venda = (Venda) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sessão.close();
		}
		return venda;
	}
	public void Excluir(Venda venda) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.delete(venda);
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
	public void Editar(Venda venda) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sessão.beginTransaction();
			sessão.update(venda);
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
