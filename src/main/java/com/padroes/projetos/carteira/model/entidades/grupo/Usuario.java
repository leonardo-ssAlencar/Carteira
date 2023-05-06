package com.padroes.projetos.carteira.model.entidades.grupo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.padroes.projetos.carteira.model.entidades.grupo.comandos.LancamentoCommand;

// @Entity
public final class Usuario implements GrupoComponent {

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

    public void executarLancamento(LancamentoCommand comando, Grupo grupo) {
        comando.setUsuario(this);
        comando.setCaixinha(grupo.getCaixinha());

        comando.executar();

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (telefone == null) {
            if (other.telefone != null)
                return false;
        } else if (!telefone.equals(other.telefone))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (senha == null) {
            if (other.senha != null)
                return false;
        } else if (!senha.equals(other.senha))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", senha="
                + senha + " ]";
    }

}
