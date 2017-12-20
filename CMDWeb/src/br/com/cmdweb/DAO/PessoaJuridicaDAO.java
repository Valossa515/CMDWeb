package br.com.cmdweb.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.cmdweb.domain.PessoaJuridica;
import br.com.cmdweb.util.HibernateUtil;

public class PessoaJuridicaDAO {
	public void Salvar(PessoaJuridica pjuridica) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.save(pjuridica);
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
	public List<PessoaJuridica> Listar() 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		List<PessoaJuridica> pjuridica = null;
		try 
		{
			Query consulta = sess�o.getNamedQuery("PessoaJuridica.listar");
			pjuridica = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} finally 
		{
			sess�o.close();
		}
		return pjuridica;

	}

	public PessoaJuridica BuscarporCNPJ(String cnpj) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		PessoaJuridica pjuridica = null;

		try 
		{
			Query consulta = sess�o.getNamedQuery("PessoaJuridica.buscar");
			consulta.setString("cnpj", cnpj);
			pjuridica = (PessoaJuridica) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sess�o.close();
		}
		return pjuridica;
	}

	public void Excluir(PessoaJuridica pjuridica) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.delete(pjuridica);
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

	public void ExcluirporCNPJ(String cnpj) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sess�o.beginTransaction();
			PessoaJuridica pjuridica = BuscarporCNPJ(cnpj);
			sess�o.delete(pjuridica);
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

	public void Editar(PessoaJuridica pjuridica) 
	{
		Session sess�o = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sess�o.beginTransaction();
			sess�o.update(pjuridica);
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
