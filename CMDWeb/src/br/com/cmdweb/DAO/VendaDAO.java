package br.com.cmdweb.DAO;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.cmdweb.domain.Venda;
import br.com.cmdweb.filter.VendaFilter;
import br.com.cmdweb.util.HibernateUtil;

public class VendaDAO {
	
	public Long Salvar(Venda venda) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		Long cod = null;
		try 
		{
			trans = sess�o.beginTransaction();
			cod = (Long)sess�o.save(venda);
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
		return cod;
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
	
	@SuppressWarnings("unchecked")
	public List<Venda> FiltrarVenda(VendaFilter filter)
	{
		List<Venda> venda = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT venda FROM Venda venda ");
		
		if(filter.getData_inicial() != null && filter.getData_final() != null)
		{
			sql.append("WHERE venda.horario BETWEEN :data_inicial AND :data_final ");
		}
		sql.append("ORDER BY venda.horario ");
		
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		try 
		{
			Query consulta = sess�o.createQuery(sql.toString());
			if(filter.getData_inicial() != null && filter.getData_final() != null)
			{
				consulta.setDate("data_inicial", filter.getData_inicial());
				consulta.setDate("data_final", filter.getData_final());
			}			
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
}
