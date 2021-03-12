package io.github.doflavio.service;

import io.github.doflavio.domain.entity.Cliente;
import io.github.doflavio.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository repository;

    /*
    @Autowired
    public ClientesService(ClientesRepository repository){
        this.repository = repository;
    }

    ou no método set
    public void setRepository(ClientesRepository repository){
        this.repository = repository;
    }

    */


    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        ClientesRepository clientesRepository = new ClientesRepository();
        clientesRepository.persistir(cliente);
    }
    public void validarCliente(Cliente cliente){
        // aplica validações
    }
}
