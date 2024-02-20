package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.entity.CreateRegistros;
import br.com.dbc.vemser.notifica.repository.CreateRegistrosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateRegistrosService {

    private final CreateRegistrosRepository createRegistrosRepository;


    public void insertCreateRegistro() {
        CreateRegistros createRegistros = new CreateRegistros();
        createRegistros.setDataHora(LocalDate.now());

        createRegistrosRepository.save(createRegistros);
    }

    public List<CreateRegistros> findAll(){
        return createRegistrosRepository.findAll();
    }

    public List<CreateRegistros> findAllAfterDate(LocalDate dateTime){
        return createRegistrosRepository.findAllAfterDate(dateTime);
    }

    public List<CreateRegistros> findAllBeforeDate(LocalDate dateTime){
        return createRegistrosRepository.findAllBeforeDate(dateTime);
    }

}
