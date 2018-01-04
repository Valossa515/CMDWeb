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
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		Long cod = null;
		try 
		{
			trans = sessão.beginTransaction();
			cod = (Long)sessão.save(venda);
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
		return cod;
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
		
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		try 
		{
			Query consulta = sessão.createQuery(sql.toString());
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
			sessão.close();
		}
		return venda;
	}
}
