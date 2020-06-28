package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.CnhLucasGomesDAO;
import br.edu.faculdadedelta.modelo.CnhLucasGomes;

@ManagedBean
@SessionScoped
public class CnhControllerLucasGomes {

	CnhLucasGomes cnhLucasGomes = new CnhLucasGomes();
	CnhLucasGomesDAO dao = new CnhLucasGomesDAO();

	private static final String PAGINA_CADASTRO_CNH = "cadastroCnh.xhtml";
	private static final String PAGINA_LISTA_CNH = "listaCnh.xhtml";

	public CnhLucasGomes getCnhLucasGomes() {
		return cnhLucasGomes;
	}

	public void setCnhLucasGomes(CnhLucasGomes cnhLucasGomes) {
		this.cnhLucasGomes = cnhLucasGomes;
	}

	public void limparCampos() {
		cnhLucasGomes = new CnhLucasGomes();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (cnhLucasGomes.getId() == 0) {
				dao.incluir(cnhLucasGomes);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(cnhLucasGomes);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return PAGINA_CADASTRO_CNH;
	}

	public String excluir() {
		try {
			dao.excluir(cnhLucasGomes);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return PAGINA_LISTA_CNH;
	}

	public String editar() {
		return PAGINA_CADASTRO_CNH;
	}

	public List<CnhLucasGomes> getLista() {
		List<CnhLucasGomes> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return listaRetorno;
	}
}