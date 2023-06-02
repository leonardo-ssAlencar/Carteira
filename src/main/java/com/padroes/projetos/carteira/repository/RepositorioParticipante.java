package com.padroes.projetos.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padroes.projetos.carteira.model.entidades.grupo.Participante;

public interface RepositorioParticipante extends JpaRepository<Participante, Long> {

}
