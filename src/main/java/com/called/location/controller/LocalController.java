package com.called.location.controller;

import com.called.error.ResourceNotFoundException;
import com.called.location.model.LocalModel;
import com.called.location.service.LocalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/zafaz/os/locais")
public class LocalController {
    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping("/one/{nome}")
    public ResponseEntity<LocalModel> findUnidadeByNome(@PathVariable @Valid String nome) {
        LocalModel localModel = localService.findLocalByNome(nome)
                .orElseThrow(() -> new ResourceNotFoundException("Local não encontrado com o nome = " + nome));
        return new ResponseEntity<>(localModel, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocalModel>> findAllLocalByNome() {
        return new ResponseEntity<>(localService.findByOrderByNomeAsc(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LocalModel> createLocal(@RequestBody @Valid LocalModel localModel) {
        return new ResponseEntity<>(localService.saveLocal(localModel), HttpStatus.CREATED);
    }
}
