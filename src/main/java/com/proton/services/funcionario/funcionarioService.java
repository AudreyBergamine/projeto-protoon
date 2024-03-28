package com.proton.services.funcionario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proton.models.entities.Funcionario;
import com.proton.models.repositories.FuncionarioRepository;

@Component
public class funcionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public Funcionario findById(Integer id) {
        Optional<Funcionario> obj = repository.findById(id);
        return obj.get();
    }

    public Funcionario insert(Funcionario obj) {
        return repository.save(obj);
    }

    public Funcionario update(Integer id, Funcionario obj) {
        Funcionario entity = repository.getOne(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Funcionario entity, Funcionario obj) {
        entity.setNomeFuncionario(obj.getNomeFuncionario());
        entity.setNumCPF(obj.getNumCPF());
        entity.setDataNascimento(obj.getDataNascimento());
        entity.setCargoFuncionario(obj.getCargoFuncionario());
        entity.setEmailDepartamento(obj.getEmailDepartamento());
        entity.setSenha(obj.getSenha());
        entity.setNumTelefoneMovel(obj.getNumTelefoneMovel());
        entity.setNumTelefoneFixo(obj.getNumTelefoneFixo());
    }

}