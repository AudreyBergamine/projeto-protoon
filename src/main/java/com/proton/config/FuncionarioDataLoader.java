// package com.proton.config;

// import java.time.LocalDate;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import com.proton.models.entities.endereco.Endereco;
// import com.proton.models.entities.funcionario.Funcionario;
// import com.proton.models.entities.secretaria.Secretaria;
// import com.proton.models.entities.user.User;
// import com.proton.models.enums.Role;
// import com.proton.models.repositories.FuncionarioRepository;

// @Configuration
// public class FuncionarioDataLoader {
//     @Autowired
//         private PasswordEncoder passwordEncoder;

//     @Bean
//     CommandLineRunner initDatabase(FuncionarioRepository funcionarioRepository) {
//         String senha = passwordEncoder.encode("123456");

//         return args -> {
//             // Verificar se já existe um funcionário para evitar duplicação
//             Optional<Funcionario> existingFuncionario = funcionarioRepository.findByEmail("jose.silva@example.com");

//             if (existingFuncionario.isEmpty()) {
//                 // Criando endereço fictício
//                 Endereco endereco = new Endereco();

//                 // Criando secretaria fictícia
//                 Secretaria secretaria = new Secretaria();

//                 // Criando funcionário default
//                 Funcionario funcionario = new Funcionario();
//                 funcionario.setNome("José Silva");
//                 funcionario.setEmail("jose.silva@example.com");
//                 funcionario.setSenha(senha);
//                 funcionario.setNum_CPF("123.456.789-00");
//                 funcionario.setCelular("(11) 98765-4321");
//                 funcionario.setData_nascimento(LocalDate.of(1990, 8, 20));
//                 funcionario.setEndereco(endereco);
//                 funcionario.setSecretaria(secretaria);
//                 funcionario.setRole(Role.FUNCIONARIO);
//                 // Salvando no banco
//                 funcionarioRepository.save(funcionario);
//                 System.out.println("Funcionário padrão criado: " + funcionario.getNome());
//             } else {
//                 System.out.println("Funcionário já existe, não será criado novamente.");
//             }
//         };
//     }
// }
