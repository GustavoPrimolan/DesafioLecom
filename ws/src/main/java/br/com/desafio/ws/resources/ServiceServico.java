package br.com.desafio.ws.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.desafio.ws.dao.ServicoDao;
import br.com.desafio.ws.models.Servico;

@Path("/servicos")
public class ServiceServico {

	private ServicoDao servicoDao = new ServicoDao();

	@Path("/")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<Servico> servicos() {
		List<Servico> servicos = servicoDao.buscaServicos();

		return servicos;
	}

}
