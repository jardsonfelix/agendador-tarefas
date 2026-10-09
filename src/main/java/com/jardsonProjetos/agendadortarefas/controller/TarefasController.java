package com.jardsonProjetos.agendadortarefas.controller;

import com.jardsonProjetos.agendadortarefas.business.TarefasService;
import com.jardsonProjetos.agendadortarefas.business.dto.TarefasDTO;

import com.jardsonProjetos.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.jardsonProjetos.agendadortarefas.infrastructure.enuns.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso =  DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal){

        return ResponseEntity.ok(tarefasService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscartarefasPorEmail(@RequestHeader("Authorization") String token){
        List<TarefasDTO> tarefas = tarefasService.buscarTarefaPorEmail(token);
        return ResponseEntity.ok(tarefas);

    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorid(@RequestParam("id") String id){
        tarefasService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                         @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefas(@RequestBody TarefasDTO dto, @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.updateTarefa(dto, id));
    }


}
