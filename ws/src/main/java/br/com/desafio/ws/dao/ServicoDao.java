package br.com.desafio.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.desafio.ws.factory.MySQLConnection;
import br.com.desafio.ws.models.Servico;

public class ServicoDao {

	private Connection connection;

	public ServicoDao() {
		this.connection = MySQLConnection.conector();
	}

	public List<Servico> buscaServicos() {
		List<Servico> servicos = new ArrayList<Servico>();
		
		String sql = "select * from servico";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Servico servico = new Servico();
				servico.setId(rs.getInt("id"));
				servico.setNome(rs.getString("nome"));
				servico.setPreco(rs.getBigDecimal("preco"));
				servicos.add(servico);
			}
		} catch (SQLException erro) {
			Logger.getLogger(ServicoDao.class.getName()).log(Level.SEVERE, null, erro);
		}

		return servicos;
	}

}
