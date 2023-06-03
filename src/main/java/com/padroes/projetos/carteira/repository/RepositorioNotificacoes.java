package com.padroes.projetos.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.padroes.projetos.carteira.model.entidades.Notificacoes;

public interface RepositorioNotificacoes extends JpaRepository<Notificacoes, Long> {

}
