package com.faculdade.crud.service;

import com.faculdade.crud.dto.AlunoDTO;
import com.faculdade.crud.model.Aluno;
import com.faculdade.crud.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno criar(AlunoDTO dto) {
        Aluno aluno = new Aluno(dto.nome(), dto.idade(), dto.curso());
        return repository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Aluno atualizar(Long id, AlunoDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setNome(dto.nome());
            existing.setIdade(dto.idade());
            existing.setCurso(dto.curso());
            return repository.save(existing);
        }).orElse(null);
    }

    public boolean excluir(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
