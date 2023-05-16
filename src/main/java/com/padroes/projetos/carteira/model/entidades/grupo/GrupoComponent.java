package com.padroes.projetos.carteira.model.entidades.grupo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GrupoComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nome;
    @OneToOne
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
     * @return GrupoComponent parente
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
