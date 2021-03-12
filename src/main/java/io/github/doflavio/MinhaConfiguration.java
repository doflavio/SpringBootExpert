package io.github.doflavio;

import io.github.doflavio.annotation.Development;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

/*
@Configuration
@Profile("development")
*/
 @Development
public class MinhaConfiguration {

/*
    @Bean(name="applicationName")
    public String applicationName(){
        return "Sistema de Vendas";
    }
    */

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("RODANDO A CONFIGURAÇÃO DE DESENVOLVIMENTO");
        };
    }
}
