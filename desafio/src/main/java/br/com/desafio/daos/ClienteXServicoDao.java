package br.com.desafio.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.desafio.models.ClienteXServico;
import br.com.desafio.models.PaginatedList;

@Repository
public class ClienteXServicoDao {
	
	@PersistenceContext
	private EntityManager manager;

	public List<ClienteXServico> all() {
		return manager.createQuery("select c from ClienteXServico c", ClienteXServico.class).getResultList();
	}

	public void save(ClienteXServico clienteXServico) {
		manager.persist(clienteXServico);
	}

	public ClienteXServico findById(Integer id) {
		return manager.find(ClienteXServico.class, id);
	}

	public void remove(ClienteXServico clienteXServico) {
		manager.remove(clienteXServico);
	}

	public void update(ClienteXServico clienteXServico) {
		manager.merge(clienteXServico);
	}

	public PaginatedList paginated(int page, int max) {
		return new PaginatorQueryHelper().list(manager, ClienteXServico.class, page, max);
	}
	
}
