package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.CnhLucasGomes;
import br.edu.faculdadedelta.modelo.MotoristaLucas;
import br.edu.faculdadedelta.util.Conexao;

public class MotoristaLucasDAO {

	public void incluir(MotoristaLucas motorista) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO motoristas_viagens (id_cat, nome_motorista, destino, distancia, valor_km, data_corrida) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, motorista.getCnhLucasGomes().getId());
		ps.setString(2, motorista.getNomeMotorista().trim());
		ps.setString(3, motorista.getDestino().trim());
		ps.setDouble(4, motorista.getDistancia());
		ps.setDouble(5, motorista.getValorKm());
		ps.setDate(6, new java.sql.Date(motorista.getDataCorrida().getTime()));

		ps.executeUpdate();

		Conexao.fecharConexao(conn, ps, null);
	}

	public void editar (MotoristaLucas motorista) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE motoristas_viagens SET id_cat = ?, nome_motorista = ?, destino = ?, distancia = ?, valor_km = ?, data_corrida = ? WHERE id_mot = ?";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, motorista.getCnhLucasGomes().getId());
		ps.setString(2, motorista.getNomeMotorista().trim());
		ps.setString(3, motorista.getDestino().trim());
		ps.setDouble(4, motorista.getDistancia());
		ps.setDouble(5, motorista.getValorKm());
		ps.setDate(6, new java.sql.Date(motorista.getDataCorrida().getTime()));
		ps.setLong(7, motorista.getId());

		ps.executeUpdate();

		Conexao.fecharConexao(conn, ps, null);
	}

	public void excluir(MotoristaLucas motorista) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM motoristas_viagens WHERE id_mot = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, motorista.getId());

		ps.executeUpdate();

		Conexao.fecharConexao(conn, ps, null);

	}

	public List<MotoristaLucas> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT " + " m.id_mot AS idMot, " + " m.nome_motorista AS nomeMotorista, "
				+ " m.destino AS destinoM, " + " m.distancia AS distanciaM, " + " m.valor_km AS valorKm, "
				+ " m.data_corrida AS dataCorrida, " + " c.id_cat AS idCat, " + " c.descricao_cat AS descricaoCat "
				+ " FROM motoristas_viagens m INNER JOIN categorias_cnh c ON m.id_cat = c.id_cat";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<MotoristaLucas> listaRetorno = new ArrayList<>();
		while (rs.next()) {

			MotoristaLucas motorista = new MotoristaLucas();

			motorista.setId(rs.getLong("idMot"));
			motorista.setNomeMotorista(rs.getString("nomeMotorista").trim());
			motorista.setDestino(rs.getString("destinoM").trim());
			motorista.setDistancia(rs.getDouble("distanciaM"));
			motorista.setValorKm(rs.getDouble("valorKm"));
			motorista.setDataCorrida(rs.getDate("dataCorrida"));

			CnhLucasGomes cnh = new CnhLucasGomes();

			cnh.setId(rs.getLong("idCat"));
			cnh.setDescCat(rs.getString("descricaoCat").trim());

			motorista.setCnhLucasGomes(cnh);

			listaRetorno.add(motorista);
		}

		Conexao.fecharConexao(conn, ps, null);

		return listaRetorno;
	}

}