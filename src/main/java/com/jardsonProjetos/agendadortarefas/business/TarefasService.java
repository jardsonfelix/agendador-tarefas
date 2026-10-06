package com.jardsonProjetos.agendadortarefas.business;

import com.jardsonProjetos.agendadortarefas.business.dto.TarefasDTO;
import com.jardsonProjetos.agendadortarefas.business.mapper.TarefasConverter;
import com.jardsonProjetos.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.jardsonProjetos.agendadortarefas.infrastructure.enuns.StatusNotificacaoEnum;
import com.jardsonProjetos.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.jardsonProjetos.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;


    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnun(StatusNotificacaoEnum.PEDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasConverter.paraTarefaEntity(dto);

        return tarefasConverter.paraTarefaDTO(
                tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscarTarefaPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefa = tarefasRepository.findByEmailUsuario(email);
        return tarefasConverter.paraListaTarefasDTO(listaTarefa);
    }
}