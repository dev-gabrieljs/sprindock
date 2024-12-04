package com.br.sprindock.service;

import com.br.sprindock.entity.Pessoa;
import com.br.sprindock.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    // Método para criar um novo usuário
    public Pessoa create(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    // Método para listar todos os usuários
    public List<Pessoa> list() {
        return pessoaRepository.findAll();
    }

    // Método para encontrar um usuário por ID
    public Optional<Pessoa> getUserById(Long id) {
        return pessoaRepository.findById(id);
    }
}
