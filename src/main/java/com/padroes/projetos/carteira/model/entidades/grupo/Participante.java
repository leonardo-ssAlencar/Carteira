package com.padroes.projetos.carteira.model.entidades.grupo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

/**
 * Classe interna para controle do grupo.
 * Tem alguns metodos uteis, e o controle do administrador
 */

@Entity
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private GrupoComponent participante;
    @ManyToOne
    private Grupo grupo;
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

    public void tornarAdmin(boolean eAdmin) {
        this.eAdmin = eAdmin;

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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
