package com.padroes.projetos.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;

@Repository
public interface RepositorioGrupo extends JpaRepository<Grupo, Long> {

}
