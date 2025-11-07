package com.faculdade.crud.controller;

import com.faculdade.crud.dto.AlunoDTO;
import com.faculdade.crud.model.Aluno;
import com.faculdade.crud.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Aluno> criar(@Valid @RequestBody AlunoDTO dto) {
        Aluno criado = service.criar(dto);
        return ResponseEntity.created(URI.create("/alunos/" + criado.getId())).body(criado);
    }

    @GetMapping
    public List<Aluno> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
        Aluno aluno = service.buscarPorId(id);
        if (aluno == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @Valid @RequestBody AlunoDTO dto) {
        Aluno atualizado = service.atualizar(id, dto);
        if (atualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean deletado = service.excluir(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
