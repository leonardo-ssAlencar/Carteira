package com.padroes.projetos.carteira.model.entidades.grupo;

// @Entity
// @Inheritance(strategy = InheritanceType.JOINED)
public abstract class GrupoComponent {

    protected Long id;
    protected String nome;
    protected GrupoComponent parente;

    public GrupoComponent(String nome, GrupoComponent parente) {
        this.nome = nome;
        this.parente = parente;
    }

    public GrupoComponent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * O metodo deve retornar o objeto que contem o objeto que chamou o metodo. No
     * caso de um usuario deve retornar o grupo raiz dele, no caso de um subGrupo
     * deve retornar o grupo pai dele
     * 
     * @return Optional<GrupoComponent> parente
     */
    public GrupoComponent getParente() {
        return this.parente;
    }

    /**
     * O metodo deve retornar o nome em que o componente foi cadastrado
     * 
     * @return String com o nome do componente
     */
    public String getNome() {
        return this.nome;
    }

}
