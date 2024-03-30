package com.proton.services.funcionario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.proton.models.entities.Funcionario;
import com.proton.models.repositories.FuncionarioRepository;

@Component
@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public Funcionario findById(Integer id) {
        Optional<Funcionario> optionalFuncionario = repository.findById(id);
        return optionalFuncionario.orElse(null); // Retorna null se não encontrar o funcionário
    }
    //TODO: Verificar se o método insert está correto!
    public Funcionario insert(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public Funcionario update(Integer id, Funcionario funcionario) {
        Optional<Funcionario> optionalFuncionario = repository.findById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario entity = optionalFuncionario.get();
            updateData(entity, funcionario);
            return repository.save(entity);
        }
        return null; // Retorna null se não encontrar o funcionário
    }

    private void updateData(Funcionario entity, Funcionario funcionario) {
        entity.setNomeFuncionario(funcionario.getNomeFuncionario());
        entity.setNumCPF(funcionario.getNumCPF());
        entity.setDataNascimento(funcionario.getDataNascimento());
        entity.setCargoFuncionario(funcionario.getCargoFuncionario());
        entity.setEmailDepartamento(funcionario.getEmailDepartamento());
        entity.setSenha(funcionario.getSenha());
        entity.setNumTelefoneMovel(funcionario.getNumTelefoneMovel());
        entity.setNumTelefoneFixo(funcionario.getNumTelefoneFixo());
        
    }

}