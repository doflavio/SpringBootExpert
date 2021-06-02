package io.github.doflavio;

import io.github.doflavio.annotation.Cachorro;
import io.github.doflavio.domain.entity.Cliente;

import io.github.doflavio.domain.entity.Pedido;
import io.github.doflavio.domain.repository.Clientes;
import io.github.doflavio.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    /* Aplicando conceitos do mapeamento */
    @Bean()
    public CommandLineRunner init(@Autowired Clientes clientes,
                                  @Autowired Pedidos pedidos ){
        return args -> {
            System.out.println("Salvando clientes");
            Cliente fulano = new Cliente("Fulano");
            clientes.save(fulano);

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

            /*
            Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());
            */
            pedidos.findByCliente(fulano).forEach(System.out::println);



        };
    }



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

    /* Usando JPA
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
    */


    /* Usando JPA Repository
    @Bean()
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando Clientes");
            clientes.save(new Cliente("Flávio"));
            clientes.save(new Cliente("Marcelo"));
            clientes.save(new Cliente("Victor"));

            System.out.println("Buscando todos os  Clientes");
            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);


            List<Cliente> resusltHql = clientes.encontrarPorNome("Flávio");
            System.out.println("------- @Query");
            resusltHql.forEach(System.out::println);

            List<Cliente> resusltNativeQuery = clientes.encontrarPorNomeNativamente("Flávio");
            System.out.println("------- @Query - nativeQuery");
            resusltNativeQuery.forEach(System.out::println);
            System.out.println("--------------" + resusltNativeQuery.size());

            boolean existeCliente = clientes.existsByNome("Flávio");
            System.out.println("Existe um cliente com o nome Flávio? " + existeCliente);

            System.out.println("Salvando Clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                clientes.save(c);
            });

            System.out.println("Buscando Clientes por nome");
            clientes.findByNomeLike("a").forEach(System.out::println);


            System.out.println("Deletando Clientes");
            clientes.findAll().forEach(c -> {
                clientes.delete(c);
            });

            System.out.println("Buscando todos os Clientes novamente");
            todosClientes = clientes.findAll();

            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado");
            }else {
                todosClientes.forEach(System.out::println);
            }
        };
    }
    */


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
