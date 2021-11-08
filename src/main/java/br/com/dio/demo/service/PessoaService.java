package br.com.dio.demo.service;

import br.com.dio.demo.domain.model.Pessoa;
import br.com.dio.demo.exception.EntidadeEmUsoException;
import br.com.dio.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }
    public void excluir(String pessoaId){
        try{
            pessoaRepository.deleteById(pessoaId);
        }catch(EmptyResultDataAccessException e){
            String.format("Não existe um cadastro de pessoa com código %d", pessoaId);
        }catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Pessoa de código %d não pode ser removida, pois está em uso", pessoaId));
        }

    }

}
