package com.called.collaborator.technician.controller;


import com.called.collaborator.technician.model.TecnicoModel;
import com.called.collaborator.technician.service.TecnicoService;
import com.called.error.ResourceNotFoundException;
import com.called.collaborator.technician.dto.TecnicoConverter;
import com.called.collaborator.technician.dto.TecnicoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/zafaz/os/tecnicos")
public class TecnicoController {

    private final TecnicoConverter tecnicoConverter;
    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoConverter tecnicoConverter, TecnicoService tecnicoService) {
        this.tecnicoConverter = tecnicoConverter;
        this.tecnicoService = tecnicoService;
    }

    @GetMapping("/one/{email}")
    public ResponseEntity<TecnicoModel> findTecnicoByEmail(@PathVariable @Valid String email) {
        TecnicoModel tecnicoModel = tecnicoService.findByTecnicoByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Técnico não encontrado com o código = " + email));
        return new ResponseEntity<>(tecnicoModel, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TecnicoDto>> findAllTecnicosByOrderByNomeAsc() {
        return new ResponseEntity<>(tecnicoConverter.modelToDto(tecnicoService.findAllTecnicosByOrderByNomeAsc()), HttpStatus.OK);
    }

    @GetMapping("/tecnicos")
    public ResponseEntity<List<TecnicoDto>> findAllTecnicosByLocal(String local) {
        List<TecnicoModel> allTecnicos = new ArrayList<>();

        tecnicoService.findAllTecnicosByOrderByNomeAsc().forEach((tecnico) -> {
            if (tecnico.getUsuario().getLocal().getNome().equals(local)) {
                allTecnicos.add(tecnico);
            }
        });

        return new ResponseEntity<>(tecnicoConverter.modelToDto(allTecnicos), HttpStatus.OK);
    }

}
