package io.github.doflavio;

import io.github.doflavio.annotation.Cachorro;
import io.github.doflavio.domain.entity.Cliente;

import io.github.doflavio.domain.repository.ClienteRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
/*
@ComponentScan(
        basePackages = {
                "io.github.doflavio.repository",
                "io.github.doflavio.service",
                "com.umaoutrabiblioteca.projeto"

        })
*/

@RestController
public class VendasApplication {

    @Value("${application.name}")
    private String applicationName;

    @Autowired
    //@Qualifier("gato")
    @Cachorro
    private Animal animal;

    /*
    @Bean(name = "executarAnimal")
    public CommandLineRunner executar(){
        return args -> {
            this.animal.fazerBarulho();
        };
    }
    */

    /* usando JDBC
    @Bean(name = "executarAnimal")
    public CommandLineRunner init(@Autowired ClienteRepositotyJDBC clienteRepositoty){
        return args -> {
            System.out.println("Salvando Clientes");
            clienteRepositoty.salvar(new Cliente("Flávio"));
            clienteRepositoty.salvar(new Cliente("Marcelo"));
            clienteRepositoty.salvar(new Cliente("Victor"));

            System.out.println("Buscando todos os  Clientes");
            List<Cliente> todosClientes = clienteRepositoty.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Salvando Clientes");
            todosClientes.forEach(c -> {
                    c.setNome(c.getNome() + " atualizado");
                    clienteRepositoty.atualizar(c);
            });

            System.out.println("Buscando Clientes por nome");
            clienteRepositoty.buscarPorNome("a").forEach(System.out::println);

            System.out.println("Deletando Clientes");
            clienteRepositoty.obterTodos().forEach(c -> {
                clienteRepositoty.deletar(c);
            });

            System.out.println("Buscando todos os Clientes novamente");
            todosClientes = clienteRepositoty.obterTodos();

            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado");
            }else {
                todosClientes.forEach(System.out::println);
            }
        };
    }
    */

    /* Usando JPA */
    @Bean()
    public CommandLineRunner init(@Autowired ClienteRepositoryJPA clienteRepositoty){
        return args -> {
            System.out.println("Salvando Clientes");
            clienteRepositoty.salvar(new Cliente("Flávio"));
            clienteRepositoty.salvar(new Cliente("Marcelo"));
            clienteRepositoty.salvar(new Cliente("Victor"));

            System.out.println("Buscando todos os  Clientes");
            List<Cliente> todosClientes = clienteRepositoty.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Salvando Clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                clienteRepositoty.atualizar(c);
            });

            System.out.println("Buscando Clientes por nome");
            clienteRepositoty.buscarPorNome("a").forEach(System.out::println);

            System.out.println("Deletando Clientes");
            clienteRepositoty.obterTodos().forEach(c -> {
                clienteRepositoty.deletar(c);
            });

            System.out.println("Buscando todos os Clientes novamente");
            todosClientes = clienteRepositoty.obterTodos();

            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado");
            }else {
                todosClientes.forEach(System.out::println);
            }

        };
    }

    /* outra forma de fazer
    @Autowired
    @Qualifier("applicationName")
    private String applicationName;
    */


    @GetMapping("/hello")
    public String helloWorld(){
        return applicationName;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class,args);
    }
}
