/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import model.Filme;

@ManagedBean(eager = true)
@ApplicationScoped //Application, pois os usuários cadastrados deverão permanecer mesmo se fizer logout.
public class FilmesMB {

    private List<Filme> listaFilmes;
    private Filme filmeSelecionado;

    public FilmesMB() {
        filmeSelecionado = new Filme();
        listaFilmes = new ArrayList<Filme>();
        listaFilmes.add(new Filme(1, "Filme 1", "Ação", "Soco soco chute lálálá..."));
        listaFilmes.add(new Filme(2, "Filme 2", "Aventura", "Corre galera lálálá..."));
        listaFilmes.add(new Filme(3, "Filme 3", "Drama", "Então ela foi lálálá..."));
    }

    public Filme getFilmeSelecionado() {
        return filmeSelecionado;
    }

    public void setFilmeSelecionado(Filme filmeSelecionado) {
        this.filmeSelecionado = filmeSelecionado;
    }

    public List<Filme> getListaFilmes() {
        return listaFilmes;
    }

    public String novoFilme() {
        filmeSelecionado = new Filme();
        return ("/admin/formCadastroFilme?faces-redirect=true");
    }

    public String addFilmes() {
        listaFilmes.add(filmeSelecionado);
        return ("/admin/formFilme?faces-redirect=true");
    }

    public void removerSala(Filme filme) {
        listaFilmes.remove(filme);
    }

    public boolean filmeExiste(int codigoFilme) {
        for (Filme filme : listaFilmes) {
            if (filme.getCodigoFilme() == (codigoFilme)) {
                return true;
            }
        }
        return false;
    }

    public Filme buscarFilme(int codigoFilme) {
        for (Filme filme : listaFilmes) {
            if (filme.getCodigoFilme() == (codigoFilme)) {
                return filme;
            }
        }
        return null;
    }
}
