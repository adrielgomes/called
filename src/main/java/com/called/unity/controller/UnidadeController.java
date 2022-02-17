package com.called.unity.controller;

import com.called.error.ResourceNotFoundException;
import com.called.unity.model.UnidadeModel;
import com.called.unity.service.UnidadeService;
import com.called.location.service.LocalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/zafaz/os/unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;
    private final LocalService localService;

    public UnidadeController(UnidadeService unidadeService, LocalService localService) {
        this.unidadeService = unidadeService;
        this.localService = localService;
    }

    @GetMapping("/one/{nome}")
    public ResponseEntity<UnidadeModel> findUnidadeByNome(@PathVariable @Valid String nome) {
        UnidadeModel unidadeModel = unidadeService.findUnidadeByNome(nome)
                .orElseThrow(() -> new ResourceNotFoundException("Local não encontrado com o nome = " + nome));
        return new ResponseEntity<>(unidadeModel, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UnidadeModel>> findAllUnidadesByNome() {
        return new ResponseEntity<>(unidadeService.findUnidadesByOrderByNomeAsc(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UnidadeModel> createUnidade(@RequestBody @Valid UnidadeModel unidadeModel) {
        unidadeModel.setLocal(localService.findLocalByNome(unidadeModel.getLocal().getNome())
                .orElseThrow(() -> new ResourceNotFoundException("Local não encontrado com o nome = " + unidadeModel.getLocal().getNome())));
        return new ResponseEntity<>(unidadeService.saveUnidade(unidadeModel), HttpStatus.CREATED);
    }
}
