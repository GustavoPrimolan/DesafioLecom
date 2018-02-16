package br.com.desafio.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.desafio.models.Cliente;
import br.com.desafio.models.PaginatedList;
import br.com.desafio.models.Servico;

@Repository
public class ServicoDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Servico> all() {
		return manager.createQuery("select s from Servico s", Servico.class).getResultList();
	}

	public void save(Servico servico) {
		manager.persist(servico);
	}

	public Servico findById(Integer id) {
		return manager.find(Servico.class, id);
	}

	public void remove(Servico servico) {
		manager.remove(servico);
	}

	public void update(Servico servico) {
		manager.merge(servico);
	}

	public PaginatedList paginated(int page, int max) {
		return new PaginatorQueryHelper().list(manager, Servico.class, page, max);
	}

}
