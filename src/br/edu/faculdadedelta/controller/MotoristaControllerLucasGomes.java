package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.MotoristaLucasDAO;
import br.edu.faculdadedelta.modelo.CnhLucasGomes;
import br.edu.faculdadedelta.modelo.MotoristaLucas;

@ManagedBean
@SessionScoped
public class MotoristaControllerLucasGomes {

	MotoristaLucas motoristaLucasGomes = new MotoristaLucas();
	MotoristaLucasDAO dao = new MotoristaLucasDAO();
	CnhLucasGomes cnhSelecionado = new CnhLucasGomes();

	private static final String PAGINA_CADASTRO_MOTORISTA = "cadastroMotorista.xhtml";
	private static final String PAGINA_LISTA_MOTORISTA = "listaMotorista.xhtml";

	public MotoristaLucas getMotoristaLucas() {
		return motoristaLucasGomes;
	}

	public void setMotoristaLucasGomes(MotoristaLucas motoristaLucas) {
		this.motoristaLucasGomes = motoristaLucas;
	}

	public CnhLucasGomes getCnhSelecionado() {
		return cnhSelecionado;
	}

	public void setCnhSelecionado(CnhLucasGomes cnhSelecionado) {
		this.cnhSelecionado = cnhSelecionado;
	}

	public void limparCampos() {
		motoristaLucasGomes = new MotoristaLucas();
		cnhSelecionado = new CnhLucasGomes();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (motoristaLucasGomes.getId() == 0) {
				motoristaLucasGomes.setCnhLucasGomes(cnhSelecionado);
				dao.incluir(motoristaLucasGomes);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				motoristaLucasGomes.setCnhLucasGomes(cnhSelecionado);
				dao.editar(motoristaLucasGomes);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}

		return PAGINA_CADASTRO_MOTORISTA;
	}

	public String excluir() {
		try {
			dao.excluir(motoristaLucasGomes);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}
		return PAGINA_LISTA_MOTORISTA;
	}

	public String editar() {
		cnhSelecionado = motoristaLucasGomes.getCnhLucasGomes();
		return PAGINA_CADASTRO_MOTORISTA;
	}

	public List<MotoristaLucas> getLista() {
		List<MotoristaLucas> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}
		return listaRetorno;
	}
}