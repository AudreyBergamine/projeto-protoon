package com.proton.controller.resources.protocolo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proton.models.entities.Endereco;
import com.proton.models.entities.Protocolo;
import com.proton.models.entities.Secretaria;
import com.proton.models.entities.municipe.Municipe;
import com.proton.models.repositories.MunicipeRepository;
import com.proton.models.repositories.SecretariaRepository;
import com.proton.models.repositories.EnderecoRepository;
import com.proton.models.repositories.ProtocoloRepository;
import com.proton.services.protocolo.ProtocoloService;
import com.proton.services.user.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;

@RestController
@RequestMapping(value = "/protoon/protocolo")
public class ProtocoloController {

    @Autowired
    private ProtocoloService protocoloService;

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @Autowired
    private MunicipeRepository municipeRepository;

    @Autowired
    private SecretariaRepository secretariaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping(value = "/todos-protocolos")// Adicionando a anotação GetMapping para o método findAll
    public ResponseEntity<List<Protocolo>> findAll() {
        List<Protocolo> list = protocoloService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{numero_protocolo}") //Pesquisa por numero de protocolo
    public ResponseEntity<Protocolo> findByNumeroProtocolo(@PathVariable String numero_protocolo) {
        Optional<Protocolo> protocoloOptional = protocoloRepository.findByNumeroProtocolo(numero_protocolo);
        if (protocoloOptional.isPresent()) {
            Protocolo obj = protocoloService.findByNumero_protocolo(numero_protocolo);
            return ResponseEntity.ok().body(obj);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/pesquisar-municipe/{nomeMunicipe}") //Pesquisar pelo nome do municipe
    public ResponseEntity<List<Protocolo>> findByNomeMunicipe(@PathVariable String nomeMunicipe) {
        List<Protocolo> protocolos = protocoloService.findByNomeMunicipe(nomeMunicipe);
        if (!protocolos.isEmpty()) {
            return ResponseEntity.ok().body(protocolos); // Retorna os protocolos encontrados
        } else {
            return ResponseEntity.notFound().build(); // Retorna status 404 se nenhum protocolo for encontrado
        }
    }

    @GetMapping(value = "/pesquisar-id/{id}") //pesquisar pelo ID
    public ResponseEntity<Protocolo> findById(@PathVariable Integer id) {
        Protocolo obj = protocoloService.findById(id);
        return ResponseEntity.ok().body(obj);// retorna UM protocolo
    }

    @PostMapping(value = "/abrir-protocolos/{id_s}")
    public ResponseEntity<Protocolo> insert(@RequestBody Protocolo protocolo, @PathVariable Long id_s,
    HttpServletRequest request) {
        Integer id_m = authenticationService.getUserIdFromToken(request);
        Municipe mun = municipeRepository.getReferenceById(id_m);
        Secretaria sec = secretariaRepository.getReferenceById(id_s);
        Endereco end = enderecoRepository.getReferenceById(mun.getEndereco().getId_endereco());
        String numeroProtocolo = protocoloService.gerarNumeroProtocolo();
        protocolo.setNumero_protocolo(numeroProtocolo);
        protocolo.setMunicipe(mun);
        protocolo.setEndereco(end);
        protocolo.setSecretaria(sec);
        protocoloRepository.save(protocolo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(protocolo.getId_protocolo()).toUri();
        return ResponseEntity.created(uri).body(protocolo);
    }

    @PutMapping("/alterar-protocolos/{numero_protocolo}")
    public ResponseEntity<Protocolo> update(@PathVariable String numero_protocolo, @RequestBody Protocolo protocolo) {
        Protocolo obj = protocoloService.update(numero_protocolo, protocolo);
        return ResponseEntity.ok(obj);
    }

    @GetMapping(value = "/meus-protocolos/{municipeId}")// Pesquisa os protocolos do munipe logado
    public ResponseEntity<List<Protocolo>> findByMunicipe(@PathVariable Integer municipeId) {
        // Recupera o Municipe com base no ID
        Optional<Municipe> municipeOptional = municipeRepository.findById(municipeId); // Optional pode voltar com o
                                                                                       // valor do municipe ou não, e
                                                                                       // evita o erro de estar null
        if (municipeOptional.isPresent()) {
            Municipe municipe = municipeOptional.get();
            // Usa o municipe recuperado para buscar os protocolos
            List<Protocolo> protocolos = protocoloService.findByMunicipe(municipe);
            return ResponseEntity.ok().body(protocolos);// retorna VARIOS protocolos do MUNICIPE LOGADO
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
