package com.called.partner.manager.controller;

import com.called.error.ResourceNotFoundException;
import com.called.partner.manager.model.GestorUnidadeModel;
import com.called.partner.manager.service.GestorUnidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/zafaz/os/gestores")
public class GestorUnidadeController {

    private final GestorUnidadeService gestorUnidadeService;


    public GestorUnidadeController(GestorUnidadeService gestorUnidadeService) {
        this.gestorUnidadeService = gestorUnidadeService;

    }

    @GetMapping("/one/{email}")
    public ResponseEntity<GestorUnidadeModel> findGestorByEmail(@PathVariable @Valid String email) {
        GestorUnidadeModel gestorUnidadeModel = gestorUnidadeService.findGestorByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Gestor não encontrado com o email = " + email));
        return new ResponseEntity<>(gestorUnidadeModel, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GestorUnidadeModel>> findByOrderByGestoresAsc() {
        return new ResponseEntity<>(gestorUnidadeService.findByOrderByGestoresAsc(), HttpStatus.OK);
    }

}
