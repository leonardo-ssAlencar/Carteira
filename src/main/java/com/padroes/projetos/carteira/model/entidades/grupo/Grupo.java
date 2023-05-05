package com.padroes.projetos.carteira.model.entidades.grupo;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.excecoes.OperacaoNaoPermitidaException;

// @Entity
public final class Grupo implements GrupoComponent {

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
    private Set<Participante> participantes = new HashSet<>();
    // A Caixinha do grupo
    private Caixinha caixinha;

    public Grupo() {

    }

    public Grupo(String nome, GrupoComponent parente, GrupoComponent dono, Caixinha caixinha) {
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
        return participantes.stream().map(Participante::getParticipante).toList();
    }

    public boolean setParticipantes(GrupoComponent participante) {

        if (participante instanceof Usuario)
            verificarRaiz();

        return this.participantes.add(new Participante(participante));

    }

    public boolean setParticipantes(List<? extends GrupoComponent> filhos) {
        if (parente instanceof Usuario)
            verificarRaiz();

        List<Participante> novoParticipantes = filhos.stream().map(x -> new Participante(x)).toList();

        if (this.parente == GrupoFachada.grupoRaiz) {
            return this.participantes.addAll(novoParticipantes.stream().filter(Participante::eGrupo).toList());

        }
        return this.participantes.addAll(novoParticipantes);

    }

    public Caixinha getCaixinha() {
        return caixinha;
    }

    public void setCaixinha(Caixinha caixinha) {
        this.caixinha = caixinha;
    }

    public void tornarAdmin(Usuario usuario) {
        verificarRaiz();
        Optional<Participante> componente = this.participantes.stream().filter(Participante::eUsuario)
                .filter(x -> x.getParticipante().equals(usuario)).findAny();

        if (componente.isEmpty()) {

            throw new OperacaoNaoPermitidaException("O usuario " + usuario.getNome() + " não está no grupo");
        }

        componente.get().eAdmin = true;

    }

    public List<Usuario> getAdministradores() {

        Set<Usuario> user = new HashSet<>();
        if (this.dono instanceof Grupo) {
            Grupo grupo = (Grupo) this.dono;

            user.addAll(grupo.getAdministradores());

        }

        return participantes.stream().filter(Participante::eUsuario).filter(Participante::eAdmin)
                .map(x -> (Usuario) x.getParticipante()).toList();

    }

    private void verificarRaiz() {
        if (parente == GrupoFachada.grupoRaiz) {
            throw new OperacaoNaoPermitidaException("A operação não pode ser concluida pois já está na raiz ");
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

    @Override
    public String toString() {
        return "Grupo [id=" + id + ", nome=" + nome + ", parente=" + parente.getNome() + ", dono=" + dono.getNome()
                + ", caixinha="
                + caixinha + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((parente == null) ? 0 : parente.hashCode());
        result = prime * result + ((dono == null) ? 0 : dono.hashCode());
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
        Grupo other = (Grupo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (parente == null) {
            if (other.parente != null)
                return false;
        } else if (!parente.equals(other.parente))
            return false;
        if (dono == null) {
            if (other.dono != null)
                return false;
        } else if (!dono.equals(other.dono))
            return false;
        return true;
    }

    /**
     * Classe interna para controle do grupo.
     * Tem alguns metodos uteis, e o controle do administrador
     */
    private class Participante {

        private GrupoComponent participante;
        private boolean eAdmin;

        public Participante(GrupoComponent participante) {
            if (participante == null) {
                // Lançar uma excessão
            }
            this.participante = participante;
            this.eAdmin = false;

        }

        public GrupoComponent getParticipante() {
            return participante;
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

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((participante == null) ? 0 : participante.hashCode());
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
            Participante other = (Participante) obj;
            if (participante == null) {
                if (other.participante != null)
                    return false;
            } else if (!participante.equals(other.participante))
                return false;
            return true;
        }

    }

}
