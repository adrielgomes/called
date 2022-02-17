package com.called.rules.controller;

import com.called.error.ResourceNotFoundException;
import com.called.rules.model.RegraModel;
import com.called.rules.service.RegraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/zafaz/os/regras")
public class RegraController {

    private final RegraService regraService;

    public RegraController(RegraService regraService) {
        this.regraService = regraService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegraModel>> findAllUnidadesByNome() {
        return new ResponseEntity<>(regraService.findRegrasByOrderByNomeAsc(), HttpStatus.OK);
    }

    @GetMapping("/one/{regra}")
    public ResponseEntity<RegraModel> findUsuarioByEmail(@PathVariable @Valid String regra) {
        RegraModel regraModel = regraService.findRegraByNome(regra)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o email = " + regra));
        return new ResponseEntity<>(regraModel, HttpStatus.OK);
    }
}
