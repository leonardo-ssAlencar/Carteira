package com.padroes.projetos.carteira.model.entidades.grupo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

// @Entity
public class Usuario implements GrupoComponent {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private GrupoComponent parente;
    private List<Usuario> dependentes;

    public Usuario() {
    }

    public Usuario(String nome, String telefone, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Optional<GrupoComponent> getParente() {
        return Optional.of(parente);
    }

    public void setParente(Grupo parente) {
        this.parente = parente;
    }

    public void setDependente(Usuario dependentes) {
        if (this.dependentes == null) {
            this.dependentes = new LinkedList<>();
        }
        this.dependentes.add(dependentes);
    }

    public List<Usuario> getDependentes() {
        return dependentes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
