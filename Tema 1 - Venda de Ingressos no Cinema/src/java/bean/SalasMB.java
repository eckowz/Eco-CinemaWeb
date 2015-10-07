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
import model.Sala;

@ManagedBean(eager = true)
@ApplicationScoped //Application, pois os usuários cadastrados deverão permanecer mesmo se fizer logout.
public class SalasMB {

    private List<Sala> listaSalas;
    private Sala salaSelecionada;

    public SalasMB() {
        salaSelecionada = new Sala();
        listaSalas = new ArrayList<Sala>();
        listaSalas.add(new Sala(1, 13));
        listaSalas.add(new Sala(2, 28));
        listaSalas.add(new Sala(3, 9));
    }

    public Sala getSalaSelecionada() {
        return salaSelecionada;
    }

    public void setSalaSelecionada(Sala salaSelecionada) {
        this.salaSelecionada = salaSelecionada;
    }

    public List<Sala> getListaSalas() {
        return listaSalas;
    }

    public String novaSala() {
        salaSelecionada = new Sala();
        return ("/admin/formCadastroSala?faces-redirect=true");
    }

    public String addSalas() {
        listaSalas.add(salaSelecionada);
        return ("/admin/formSala?faces-redirect=true");
    }

    public void removerSala(Sala sala) {
        listaSalas.remove(sala);
    }

    public boolean salaExiste(int codigoSala) {
        for (Sala sala : listaSalas) {
            if (sala.getCodigoSala() == (codigoSala)) {
                return true;
            }
        }
        return false;
    }

    public Sala buscarSala(int codigoSala) {
        for (Sala sala : listaSalas) {
            if (sala.getCodigoSala() == (codigoSala)) {
                return sala;
            }
        }
        return null;
    }
}
