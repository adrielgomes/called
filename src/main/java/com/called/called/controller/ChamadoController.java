package com.called.called.controller;

import com.called.called.dto.ChamadoConverter;
import com.called.called.dto.ChamadoDto;
import com.called.called.model.ChamadoModel;
import com.called.called.service.ChamadoService;
import com.called.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/zafaz/os/chamados")
public class ChamadoController {
    private final ChamadoService chamadoService;
    private final ChamadoConverter chamadoConverter;


    public ChamadoController(ChamadoService chamadoService, ChamadoConverter chamadoConverter) {
        this.chamadoService = chamadoService;
        this.chamadoConverter = chamadoConverter;

    }

    @GetMapping("/one/{uuid}")
    public ResponseEntity<ChamadoModel> findChamadoByCodigo(@PathVariable @Valid String uuid) {
        ChamadoModel chamadoModel = chamadoService.findByCodigo(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Chamado não encontrado com o código = " + uuid));
        return new ResponseEntity<>(chamadoModel, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChamadoDto>> findAllChamadosByData() {
        return new ResponseEntity<>(
                chamadoConverter.modelToDto(
                        chamadoService.findChamadosByOrderByDataAsc()),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ChamadoDto> createChamado(@RequestBody ChamadoDto chamadoDto) {
        return new ResponseEntity<>(
                chamadoConverter.modelToDto(
                        chamadoService.saveChamado(
                                chamadoConverter.dtoToModel(chamadoDto))),
                HttpStatus.CREATED);
    }

}