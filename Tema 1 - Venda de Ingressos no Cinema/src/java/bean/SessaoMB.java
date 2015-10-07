package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import model.Sessao;

@ManagedBean(eager = true)
@ApplicationScoped //Application, pois os usuários cadastrados deverão permanecer mesmo se fizer logout.
public class SessaoMB {

    private List<Sessao> listaSessoes;
    private Sessao sessaoSelecionada;
    FilmesMB filmeMB = new FilmesMB();
    SalasMB salaMB = new SalasMB();

    public SessaoMB() {
        //FilmesMB filmeMB = new FilmesMB();
        //SalasMB salaMB = new SalasMB();
        listaSessoes = new ArrayList<Sessao>();
        listaSessoes.add(new Sessao("28-05-2015 15:30", filmeMB.buscarFilme(1), salaMB.buscarSala(3)));
        listaSessoes.add(new Sessao("28-05-2015 15:30", filmeMB.buscarFilme(3), salaMB.buscarSala(1)));
        listaSessoes.add(new Sessao("28-05-2015 15:30", filmeMB.buscarFilme(2), salaMB.buscarSala(2)));
    }
    
    public Sessao getSessaoSelecionada() {
        return sessaoSelecionada;
    }

    public void setSessaoSelecionada(Sessao sessaoSelecionada) {
        this.sessaoSelecionada = sessaoSelecionada;
    }

    public List<Sessao> getListaSessoes() {
        return listaSessoes;
    }

    public String novaSessao() {
        sessaoSelecionada = new Sessao();
        return ("/admin/formCadastroSessao?faces-redirect=true");
    }

    public String addSessoes() {
        listaSessoes.add(sessaoSelecionada);
        return ("/admin/formSessao?faces-redirect=true");
    }

    public void removerSessao(Sessao sessao) {
        listaSessoes.remove(sessao);
    }

    public int proxSessao() {
        int k = 0;
        for (Sessao sessao : listaSessoes) {
            k = k + 1;
            if (sessao.getCodSessao() != k) {
                if (listaSessoes.size() == k) {
                    return (k + 1);
                }
                return (k);
            }
        }
        return 0;
    }

    
    public boolean sessaoExiste(String horario) {
        for (Sessao sessao : listaSessoes) {
            if (sessao.getHorario().equals(horario)) {
                return true;
            }
        }
        return false;
    }

    public boolean sessaoExistePorCodigo(int cod) {
        for (Sessao hora : listaSessoes) {
            if (hora.getCodSessao() == cod) {
                return true;
            }
        }
        return false;
    }

    public Sessao buscarHorarioSessao(Date horario) {
        for (Sessao hora : listaSessoes) {
            if (hora.getHorario().equals(horario)) {
                return hora;
            }
        }
        return null;
    }

    public boolean temAssento(int cod) {
        if (sessaoExistePorCodigo(cod)) {
            for (Sessao hora : listaSessoes) {
                if (hora.getCodSessao() == cod && hora.getSala().getQuantidadeAssentos() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void ocupaAssento(int cod) {
        if (sessaoExistePorCodigo(cod)) {
            for (Sessao horarioSessao : listaSessoes) {
                if (horarioSessao.getCodSessao() == cod && temAssento(cod)) {
                    int a = horarioSessao.getSala().getQuantidadeAssentos() - 1;
                    horarioSessao.getSala().setQuantidadeAssentos(a);
                }
            }
        }
    }

    public int listarAssentosDisponiveisPorSessao(int cod) {
        for (Sessao horarioSessao : listaSessoes) {
            if (horarioSessao.getCodSessao() == cod) {
                return horarioSessao.getSala().getQuantidadeAssentos();
            }
        }
        return 0;
    }

}
