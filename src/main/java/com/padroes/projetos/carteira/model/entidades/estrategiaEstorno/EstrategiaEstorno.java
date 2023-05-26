package com.padroes.projetos.carteira.model.entidades.estrategiaEstorno;

import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class EstrategiaEstorno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public abstract void calcularExtorno(Caixinha caixinha);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
