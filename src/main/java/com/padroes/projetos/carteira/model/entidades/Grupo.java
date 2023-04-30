package com.padroes.projetos.carteira.model.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.padroes.projetos.carteira.model.entidades.excecoes.ComponenteNulo;

public class Grupo implements GrupoComponent {

    private String nome;
    private GrupoComponent parente; // É o objeto que contém
    private GrupoComponent dono;
    private List<Participante> filhos = new ArrayList<>();
    private Caixinha caixinha;

    // Cria um novo Grupo
    public Grupo(String nome, GrupoComponent parente) {

    }

    // Cria um novo grupo de usuario
    public Grupo(Usuario user) {

    }

    /**
     * @return a lista de entidades que são participantes desse grupo
     */
    @Override
    public List<GrupoComponent> getFilhos() {
        return filhos.stream().map(Participante::getParticipante).collect(Collectors.toList());
    }

    /**
     * @return o grupo que contém esse grupo, ou o grupo raiz.
     * 
     */
    @Override
    public Optional<GrupoComponent> getParente() {
        return Optional.of(parente);
    }

    @Override
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @return Lista de usuarios que tem privilegio de administrador
     */
    public List<Usuario> getAdministradores() {

        Function<Participante, Usuario> map = (x) -> {
            return (Usuario) x.getParticipante();
        };

        return filhos.stream().filter(Participante::eAdmin).filter(Participante::eUsuario).map(map)
                .collect(Collectors.toList());
    }

    /**
     * Adiciona um Grupo ou usuario nesse grupo
     * 
     * @param GrupoComponent participante
     * @return uma referencia ao objeto para concatenar chamadas
     */
    public Grupo setParticipante(GrupoComponent participante) {

        if (this.parente == GrupoComponent.root && participante instanceof Usuario) {
            // TODO criar uma exception personalizada
            throw new RuntimeException("Não é possivel adicionar outro usuario a um grupo raiz");

        }
        Participante p = new Participante(participante);

        this.filhos.add(p);
        return this;

    }

    /**
     * Adiciona uma coleção ao grupo
     * 
     * @param List participante
     */
    public void setParticipante(List<GrupoComponent> participantes) {
        for (GrupoComponent gc : participantes) {

            Participante p = new Participante(gc);

            this.filhos.add(p);

        }
    }

    public Caixinha getCaixinha() {
        return caixinha;
    }

    public void setCaixinha(Caixinha caixinha) {
        this.caixinha = caixinha;
    }

    public void setAdmin(Usuario user) {

        Predicate<Participante> eUsuario = Participante::eUsuario;
        Predicate<Participante> eAdmin = Participante::eAdmin;
        this.filhos.stream().filter(eUsuario.and(eAdmin.negate())).toList().forEach(
                x -> {
                    x.getParticipante().equals(user);
                });
        ;

    }

    public GrupoComponent getDono() {
        return dono;
    }

    public void setDono(GrupoComponent dono) {
        this.dono = dono;
    }

    /**
     * Classe interna para controle do grupo.
     * Tem alguns metodos uteis, e o controle do administrador
     */
    private class Participante {

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

        public boolean eAdmin() {
            return this.eAdmin;
        }

        public void setAdmin(boolean tornarAdmin) {
            this.eAdmin = tornarAdmin;
        }

        public boolean eUsuario() {
            return this.participante instanceof Usuario;
        }

        public boolean eGrupo() {
            return this.participante instanceof Grupo;
        }
    }

}
