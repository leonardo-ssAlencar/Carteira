package com.padroes.projetos.carteira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.Participante;

public interface RepositorioParticipante extends JpaRepository<Participante, Long> {

    @Query("FROM Participante where grupo = :grupo")
    public List<Participante> participantes(@Param("grupo") Grupo grupo);

}
