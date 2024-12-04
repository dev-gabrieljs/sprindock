package com.br.sprindock.controller;


import com.br.sprindock.entity.Pessoa;
import com.br.sprindock.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")  // Prefixando todas as rotas com /users
public class PessoaController {
    @Autowired
    private PessoaService pessoService;

    @PostMapping
    public ResponseEntity<Pessoa> createUser(@RequestBody Pessoa pessoa) {
        Pessoa savedPessoa = pessoService.create(pessoa);
        return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);  // Usando status 201 para criação
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listUsers() {
        List<Pessoa> users = pessoService.list();
        return new ResponseEntity<>(users, HttpStatus.OK);  // Retornando com status 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getUserById(@PathVariable Long id) {
        return pessoService.getUserById(id)
                .map(pessoa -> new ResponseEntity<>(pessoa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
