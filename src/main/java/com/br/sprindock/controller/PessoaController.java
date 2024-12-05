package com.br.sprindock.controller;


import com.br.sprindock.entity.Pessoa;
import com.br.sprindock.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")  // Prefixando todas as rotas com /users
public class PessoaController {
    @Autowired
    private PessoaService pessoService;

    @PostMapping
    public ResponseEntity<Pessoa> createUser(@RequestBody Pessoa pessoa) {
        return new ResponseEntity<>(pessoService.create(pessoa), HttpStatus.CREATED);  // Usando status 201 para criação
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listUsers() {
        return new ResponseEntity<>(pessoService.list(), HttpStatus.OK);  // Retornando com status 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(pessoService.getUserById(id), HttpStatus.OK);
    }

}
