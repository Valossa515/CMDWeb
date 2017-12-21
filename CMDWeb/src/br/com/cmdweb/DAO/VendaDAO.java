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
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.save(venda);
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
	public List<Venda> Listar()
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		List<Venda> venda = null;
		try 
		{
			Query consulta = sess�o.getNamedQuery("Venda.listar");
			venda = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sess�o.close();
		}
		return venda;
	}
	public Venda BuscarporCodigo(Long codigo) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Venda venda = null;

		try 
		{
			Query consulta = sess�o.getNamedQuery("Venda.buscar");
			consulta.setLong("codigo", codigo);
			venda = (Venda) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sess�o.close();
		}
		return venda;
	}
	public void Excluir(Venda venda) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.delete(venda);
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
	public void Editar(Venda venda) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.update(venda);
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
