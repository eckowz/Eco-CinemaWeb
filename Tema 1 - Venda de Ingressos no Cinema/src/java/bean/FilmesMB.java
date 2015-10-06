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

    public FilmesMB() {
        listaFilmes = new ArrayList<Filme>();
        listaFilmes.add(new Filme(1, "Filme 1", "Ação", "Soco soco chute lálálá..."));
        listaFilmes.add(new Filme(2, "Filme 2", "Aventura", "Corre galera lálálá..."));
        listaFilmes.add(new Filme(3, "Filme 3", "Drama", "Então ela foi lálálá..."));
    }

    public boolean addFilmes(Filme filme) {
        return (listaFilmes.add(filme));
    }

    public List<Filme> getListaFilmes() {
        return listaFilmes;
    }

    public boolean filmeExiste(int codigoFilme) {
        for (Filme filme : listaFilmes) {
            if (filme.getCodigoFilme()==(codigoFilme)) {
                return true;
            }
        }
        return false;
    }

    public Filme buscarFilme(int codigoFilme) {
        for (Filme filme : listaFilmes) {
            if (filme.getCodigoFilme()==(codigoFilme)) {
                return filme;
           }
        }
        return null;
    }
} 
