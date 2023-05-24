package com.padroes.projetos.carteira.model.entidades;

import java.time.LocalDateTime;

public class Mensagens {

    private Long id;
    private String mensagem;
    private LocalDateTime dataHoraEnvio;
    private boolean lida;

    public Mensagens(String msg) {
        this.mensagem = msg;
        this.dataHoraEnvio = LocalDateTime.now();
        this.lida = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        this.marcarLida();
        return mensagem;
    }

    public LocalDateTime getEnviada() {
        return dataHoraEnvio;
    }

    public boolean isLida() {
        return this.lida;
    }

    public void marcarLida() {
        this.lida = true;
    }

}
