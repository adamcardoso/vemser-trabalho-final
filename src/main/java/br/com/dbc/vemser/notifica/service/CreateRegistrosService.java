package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.entity.CreateRegistros;
import br.com.dbc.vemser.notifica.repository.CreateRegistrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateRegistrosService {

    private final CreateRegistrosRepository createRegistrosRepository;

    @Autowired
    public CreateRegistrosService(CreateRegistrosRepository createRegistrosRepository, MongoTemplate mongoTemplate) {
        this.createRegistrosRepository = createRegistrosRepository;
    }

    public void insertCreateRegistro() {
        CreateRegistros createRegistros = new CreateRegistros();
        createRegistros.setDataHora(LocalDateTime.now());

        createRegistrosRepository.save(createRegistros);
    }
}
