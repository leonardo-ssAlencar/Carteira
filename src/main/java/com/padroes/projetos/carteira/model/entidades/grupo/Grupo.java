package com.padroes.projetos.carteira.model.entidades.grupo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.padroes.projetos.carteira.model.entidades.Caixinha;

// @Entity
public class Grupo implements GrupoComponent {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Nome do grupo
    private String nome;
    // O grupo que contém este grupo
    private GrupoComponent parente;
    // O dono do grupo, normalmente quem o criou
    private GrupoComponent dono;
    // Lista dos participantes do grupo
    private List<Participante> participantes;
    // A Caixinha do grupo
    private Caixinha caixinha;

    public Grupo() {

    }

    public Grupo(String nome, GrupoComponent parente, GrupoComponent dono, Caixinha caixinha) {
        this.participantes = new ArrayList<>();
        this.nome = nome;
        this.parente = parente;
        this.dono = dono;

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setParente(GrupoComponent parente) {
        this.parente = parente;
    }

    public GrupoComponent getDono() {
        return dono;
    }

    public void setDono(GrupoComponent dono) {
        this.dono = dono;
    }

    public List<GrupoComponent> getParticipantes() {
        verificarRaiz();
        return participantes.stream().map(Participante::getParticipante).toList();
    }

    public void setParticipantes(GrupoComponent participante) {
        verificarRaiz();
        if (this.participantes == null) {
            this.participantes = new ArrayList<>();

        }
        this.participantes.add(Participante.of(participante));

    }

    public void setParticipantes(List<? extends GrupoComponent> filhos) {
        verificarRaiz();
        this.participantes.addAll(filhos.stream().map(Participante::of).toList());
    }

    public Caixinha getCaixinha() {
        return caixinha;
    }

    public void setCaixinha(Caixinha caixinha) {
        this.caixinha = caixinha;
    }

    public void tornarAdmin(Usuario usuario) {
        verificarRaiz();
        this.participantes.stream().filter(Participante::eUsuario).filter(x -> x.getParticipante().equals(usuario))
                .forEach(x -> {
                    x.eAdmin = true;
                });
    }

    public List<Usuario> getAdministradores() {
        verificarRaiz();
        return participantes.stream().filter(Participante::eAdmin).filter(Participante::eUsuario)
                .map(x -> (Usuario) x.getParticipante()).toList();

    }

    private void verificarRaiz() {
        if (parente == GrupoInterface.grupoRaiz) {
            // TODO criar uma excessão para isso
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Optional<GrupoComponent> getParente() {
        return Optional.of(this.parente);

    }

    @Override
    public String getNome() {
        return this.nome;
    }

    /**
     * Classe interna para controle do grupo.
     * Tem alguns metodos uteis, e o controle do administrador
     */
    private static class Participante {

        private GrupoComponent participante;
        private boolean eAdmin = false;

        public Participante(GrupoComponent participante) {
            if (participante == null) {
                // Lançar uma excessão
            }
            this.participante = participante;

        }

        public GrupoComponent getParticipante() {
            return participante;
        }

        public static Participante of(GrupoComponent participante) {
            return new Participante(participante);
        }

        public boolean eAdmin() {
            return this.eAdmin;
        }

        public boolean eUsuario() {
            return this.participante instanceof Usuario;
        }

        public boolean eGrupo() {
            return this.participante instanceof Grupo;
        }
    }

}
