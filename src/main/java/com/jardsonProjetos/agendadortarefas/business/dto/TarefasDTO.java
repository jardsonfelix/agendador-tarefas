package com.jardsonProjetos.agendadortarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jardsonProjetos.agendadortarefas.infrastructure.enuns.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTO {

    private String  id;
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnun;

}
