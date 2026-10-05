package com.jardsonProjetos.agendadortarefas.business.mapper;

import com.jardsonProjetos.agendadortarefas.business.dto.TarefasDTO;
import com.jardsonProjetos.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}

