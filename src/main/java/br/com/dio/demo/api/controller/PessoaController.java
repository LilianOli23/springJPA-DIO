package br.com.dio.demo.api.controller;

import br.com.dio.demo.domain.model.Pessoa;
import br.com.dio.demo.exception.EntidadeEmUsoException;
import br.com.dio.demo.exception.EntidadeNaoEncontradaException;
import br.com.dio.demo.repository.PessoaRepository;
import br.com.dio.demo.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> buscar(@PathVariable String pessoaId) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);

        if(pessoa.isPresent()){
            return ResponseEntity.ok(pessoa.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa adicionar(@RequestBody Pessoa pessoa){
        return pessoaService.salvar(pessoa);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable String pessoaId,
                                            @RequestBody Pessoa pessoa){
        Pessoa pessoaAtual = pessoaRepository.findById(pessoaId).orElse(null);

        if(pessoaAtual != null){
            BeanUtils.copyProperties(pessoa, pessoaAtual, "id");

            pessoaAtual = pessoaService.salvar(pessoaAtual);
            return ResponseEntity.ok(pessoaAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{pessoaId}")

    public ResponseEntity<String> remover(@PathVariable String pessoaId){

        try {
            pessoaService.excluir(pessoaId);
            return ResponseEntity.noContent().build();
        }catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        }
    }

}
