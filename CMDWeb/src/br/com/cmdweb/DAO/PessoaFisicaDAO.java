package br.com.cmdweb.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.cmdweb.domain.PessoaFisica;
import br.com.cmdweb.util.HibernateUtil;

public class PessoaFisicaDAO {
	
	public void Salvar(PessoaFisica pfisica) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.save(pfisica);
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
	public List<PessoaFisica> Listar() 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		List<PessoaFisica> pfisica = null;
		try 
		{
			Query consulta = sessão.getNamedQuery("PessoaFisica.listar");
			pfisica = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sessão.close();
		}
		return pfisica;

	}

	public PessoaFisica BuscarporCPF(String cpf) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		PessoaFisica pfisica = null;

		try 
		{
			Query consulta = sessão.getNamedQuery("PessoaFisica.buscar");
			consulta.setString("cpf", cpf);
			pfisica = (PessoaFisica) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sessão.close();
		}
		return pfisica;
	}

	public void Excluir(PessoaFisica pfisica) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.delete(pfisica);
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

	public void ExcluirporCPF(String cpf) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			PessoaFisica pfisica = BuscarporCPF(cpf);
			sessão.delete(pfisica);
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

	public void Editar(PessoaFisica pfisica) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sessão.beginTransaction();
			sessão.update(pfisica);
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
