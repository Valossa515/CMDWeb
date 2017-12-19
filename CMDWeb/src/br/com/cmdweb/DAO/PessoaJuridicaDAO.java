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
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.save(pjuridica);
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
	public List<PessoaJuridica> Listar() 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		List<PessoaJuridica> pjuridica = null;
		try 
		{
			Query consulta = sessão.getNamedQuery("PessoaJuridica.listar");
			pjuridica = consulta.list();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} finally 
		{
			sessão.close();
		}
		return pjuridica;

	}

	public PessoaJuridica BuscarporCNPJ(String cnpj) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		PessoaJuridica pjuridica = null;

		try 
		{
			Query consulta = sessão.getNamedQuery("PessoaJuridica.buscar");
			consulta.setString("cnpj", cnpj);
			pjuridica = (PessoaJuridica) consulta.uniqueResult();
		} 
		catch (RuntimeException e) 
		{
			throw e;
		} 
		finally 
		{
			sessão.close();
		}
		return pjuridica;
	}

	public void Excluir(PessoaJuridica pjuridica) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			sessão.delete(pjuridica);
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

	public void ExcluirporCNPJ(String cnpj) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;

		try 
		{
			trans = sessão.beginTransaction();
			PessoaJuridica pjuridica = BuscarporCNPJ(cnpj);
			sessão.delete(pjuridica);
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

	public void Editar(PessoaJuridica pjuridica) 
	{
		Session sessão = HibernateUtil.getSessionFactory().openSession();

		Transaction trans = null;
		
		try 
		{
			trans = sessão.beginTransaction();
			sessão.update(pjuridica);
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
