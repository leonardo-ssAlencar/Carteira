package com.padroes.projetos.carteira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padroes.projetos.carteira.model.entidades.Notificacoes;
import com.padroes.projetos.carteira.model.entidades.caixinha.Caixinha;
import com.padroes.projetos.carteira.model.entidades.caixinha.CaixinhaBuilder;
import com.padroes.projetos.carteira.model.entidades.grupo.Grupo;
import com.padroes.projetos.carteira.model.entidades.grupo.GrupoFachada;
import com.padroes.projetos.carteira.model.entidades.grupo.Participante;
import com.padroes.projetos.carteira.model.entidades.grupo.Usuario;
import com.padroes.projetos.carteira.model.entidades.lancamento.Lancamento;
import com.padroes.projetos.carteira.model.excecoes.EntidadeNaoCadastradaException;
import com.padroes.projetos.carteira.repository.RepositorioCaixinha;
import com.padroes.projetos.carteira.repository.RepositorioGrupo;
import com.padroes.projetos.carteira.repository.RepositorioLancamento;
import com.padroes.projetos.carteira.repository.RepositorioNotificacoes;
import com.padroes.projetos.carteira.repository.RepositorioParticipante;
import com.padroes.projetos.carteira.repository.RepositorioUsuario;

@Service
public class AplicacaoFachada {

    @Autowired
    RepositorioUsuario usuarioRepo;
    @Autowired
    RepositorioGrupo grupoRepo;
    @Autowired
    RepositorioCaixinha caixinhaRepo;
    @Autowired
    RepositorioParticipante participanteRepo;
    @Autowired
    RepositorioLancamento lancamentoRepo;
    @Autowired
    RepositorioNotificacoes notificacaoRepo;

    @Autowired
    GrupoFachada fachada;

    /**
     * Cadastra um Usuario
     * 
     * @param usuario
     * @return
     */

    public Usuario cadastrarUsuario(Usuario usuario) {

        usuarioRepo.save(usuario);

        Grupo grupo = fachada.criarGrupoUsuario(usuario);

        CaixinhaBuilder builder = new CaixinhaBuilder();

        Caixinha caixinha = builder.build();
        grupo.setCaixinha(caixinha);

        caixinhaRepo.save(caixinha);

        grupoRepo.save(grupo);

        return usuarioRepo.save(usuario);
    }

    /**
     * Cadastra um grupo
     * 
     * @param grupo
     * @return
     */
    public Grupo cadastrarGrupo(Grupo grupo) {

        Optional<Usuario> user = usuarioRepo.findById(grupo.getDono().getId());

        if (user.isEmpty()) {

            throw new EntidadeNaoCadastradaException("O usuario em questão nao existe");
        }

        caixinhaRepo.save(grupo.getCaixinha());

        grupo = grupoRepo.save(grupo);
        Participante participante = new Participante(grupo);
        participante.setGrupo((Grupo) user.get().getParente());

        participanteRepo.save(participante);

        return grupo;

    }

    /**
     * Testa se o usuario tem conta. Use pra fazer login.
     * 
     * @param email
     * @param senha
     * @return a conta do usuario
     */

    public Optional<Usuario> validarUsuario(String email, String senha) {

        Optional<Usuario> userOpt = usuarioRepo.findOneByEmail(email);

        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            if (user.getSenha().equals(senha)) {
                return userOpt;
            }
        }

        return Optional.empty();

    }

    public Participante buscarUsuario(String email) throws EntidadeNaoCadastradaException {

        Optional<Usuario> user = usuarioRepo.findOneByEmail(email);

        if (user.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Não existe usuario com esse email");
        }

        return new Participante(user.get());

    }

    public Grupo buscarGrupo(Long id) throws EntidadeNaoCadastradaException {

        Optional<Grupo> grupo = grupoRepo.findById(id);
        if (grupo.isEmpty()) {
            throw new EntidadeNaoCadastradaException("O grupo nao existe");

        }

        return grupo.get();

    }

    /**
     * Cadastra ou atualiza um participante no grupo
     * 
     * @param participante
     * @param grupo
     * @return
     */
    public Participante cadastrarParticipante(Participante participante, Grupo grupo) {

        grupo.setParticipantes(participante.getParticipante());
        participante.setGrupo(grupo);

        return participanteRepo.save(participante);

    }

    /**
     * Salva ou atualiza um lancamento no banco
     * 
     * @param lancamento
     * @return Lancamento com o id
     */
    public Lancamento salvarLancamento(Lancamento lancamento) {

        return lancamentoRepo.save(lancamento);

    }

    /**
     * Salva as notificacoes no banco
     * 
     * @param notificacoes lista das notificacoes
     */
    public void salvarNotificacoes(List<Notificacoes> notificacoes) {

        notificacaoRepo.saveAll(notificacoes);

    }

}
