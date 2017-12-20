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
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.save(pfisica);
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
	public List<PessoaFisica> Listar() 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		List<PessoaFisica> pfisica = null;
		try 
		{
			Query consulta = sess�o.getNamedQuery("PessoaFisica.listar");
			pfisica = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sess�o.close();
		}
		return pfisica;

	}

	public PessoaFisica BuscarporCPF(String cpf) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		PessoaFisica pfisica = null;

		try 
		{
			Query consulta = sess�o.getNamedQuery("PessoaFisica.buscar");
			consulta.setString("cpf", cpf);
			pfisica = (PessoaFisica) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sess�o.close();
		}
		return pfisica;
	}

	public void Excluir(PessoaFisica pfisica) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.delete(pfisica);
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

	public void ExcluirporCPF(String cpf) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			PessoaFisica pfisica = BuscarporCPF(cpf);
			sess�o.delete(pfisica);
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

	public void Editar(PessoaFisica pfisica) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.update(pfisica);
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
