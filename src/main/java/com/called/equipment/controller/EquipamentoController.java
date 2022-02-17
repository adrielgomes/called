package com.called.equipment.controller;

import com.called.equipment.model.EquipamentoModel;
import com.called.error.ResourceNotFoundException;
import com.called.partner.manager.service.GestorUnidadeService;
import com.called.equipment.service.EquipamentoService;
import com.called.unity.model.UnidadeModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/zafaz/os/equipamentos")
public class EquipamentoController {
    private boolean resposta;
    private final EquipamentoService equipamentoService;
    private final GestorUnidadeService gestorUnidadeService;

    public EquipamentoController(EquipamentoService equipamentoService, GestorUnidadeService gestorUnidadeService) {
        this.equipamentoService = equipamentoService;
        this.gestorUnidadeService = gestorUnidadeService;
        this.resposta = false;

    }

    @GetMapping("/one/{uuid}")
    public ResponseEntity<EquipamentoModel> findEquipamentoByCodigo(@PathVariable @Valid String uuid, @RequestParam String gestorEmail) {
        resposta = false;
        EquipamentoModel equipamentoModel = equipamentoService.findByCodigo(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com o código = " + uuid));
        gestorUnidadeService.findGestorByEmail(gestorEmail).ifPresent((gestor) -> gestor.getUnidades().stream().map(UnidadeModel::getEquipamentos).forEach((e) -> {
            if (e.contains(equipamentoModel)) {
                resposta = true;

            }
        }));

        if(resposta){
            System.out.println(equipamentoModel.getDescricao());
            return new ResponseEntity<>(equipamentoModel, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<EquipamentoModel>> findAllEquipamentosByData() {
        return new ResponseEntity<>(equipamentoService.findEquipamentosByOrderByNome(), HttpStatus.OK);
    }

}
