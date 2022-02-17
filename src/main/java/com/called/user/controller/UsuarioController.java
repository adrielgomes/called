package com.called.user.controller;

import com.called.error.ResourceNotFoundException;
import com.called.user.model.UsuarioModel;
import com.called.user.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/zafaz/os/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }

    @GetMapping("/one/{email}")
    public ResponseEntity<UsuarioModel> findUsuarioByEmail(@PathVariable @Valid String email) {
        UsuarioModel usuarioModel = usuarioService.findUsuarioByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o email = " + email));
        return new ResponseEntity<>(usuarioModel, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioModel>> findAllUsuarioByNome() {
        return new ResponseEntity<>(usuarioService.findByOrderByNomeAsc(), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<UsuarioModel> createUsuario(@RequestBody @Valid UsuarioModel usuarioModel) {

        return new ResponseEntity<>(usuarioService.saveUsuario(usuarioModel), HttpStatus.CREATED);

    }

    @PutMapping("/update/{email}")
    public ResponseEntity<UsuarioModel> updateUsuario(@PathVariable("email") String email, @RequestBody @Valid UsuarioModel usuarioModel) {
        UsuarioModel usuarioOld = usuarioService.findUsuarioByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o email = " + email));
        usuarioOld.setNome(usuarioModel.getNome());
        usuarioOld.setEmail(usuarioModel.getEmail());
        usuarioOld.setSenha(usuarioModel.getSenha());

        return new ResponseEntity<>(usuarioService.saveUsuario(usuarioOld), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("email") String email) {
        usuarioService.findUsuarioByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o email = " + email));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
