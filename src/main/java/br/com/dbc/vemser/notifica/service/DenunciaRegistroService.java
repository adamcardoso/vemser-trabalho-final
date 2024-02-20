package br.com.dbc.vemser.notifica.service;

import br.com.dbc.vemser.notifica.entity.CreateRegistros;
import br.com.dbc.vemser.notifica.entity.DenunciaRegistros;
import br.com.dbc.vemser.notifica.entity.enums.TipoRegistro;
import br.com.dbc.vemser.notifica.repository.DenunciaRegistroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DenunciaRegistroService {
    private final DenunciaRegistroRepository denunciaRegistroRepository;

    public void inicioDenunciaRegistro(){
        DenunciaRegistros createRegistros = new DenunciaRegistros();
        createRegistros.setDataHora(LocalDate.now());
        createRegistros.setTipoRegistro(TipoRegistro.CRIACAO);

        denunciaRegistroRepository.save(createRegistros);
    }

    public void fechaDenunciaRegistro(){
        DenunciaRegistros createRegistros = new DenunciaRegistros();
        createRegistros.setDataHora(LocalDate.now());
        createRegistros.setTipoRegistro(TipoRegistro.FECHAMENTo);

        denunciaRegistroRepository.save(createRegistros);
    }

    public List<DenunciaRegistros> findAllAfterDateTimeAndTipoRegistroInicio(LocalDate dataHora){
        return denunciaRegistroRepository.findAllAfterDateTimeAndTipoRegistroInicio(dataHora, TipoRegistro.CRIACAO);
    }

    public List<DenunciaRegistros> findAllBeforeDateAndTipoRegistroInicio(LocalDate dataHora){
        return denunciaRegistroRepository.findAllBeforeDateAndTipoRegistroInicio(dataHora, TipoRegistro.CRIACAO);
    }

    public List<DenunciaRegistros> findAllAfterDateTimeAndTipoRegistroFechamento(LocalDate dataHora){
        return denunciaRegistroRepository.findAllAfterDateTimeAndTipoRegistroFechamento(dataHora, TipoRegistro.FECHAMENTo);
    }

    public List<DenunciaRegistros> findAllBeforeDateAndTipoRegistroFechamento(LocalDate dataHora){
        return denunciaRegistroRepository.findAllBeforeDateAndTipoRegistroFechamento(dataHora, TipoRegistro.FECHAMENTo);
    }

    public List<DenunciaRegistros> findAll(){
        return denunciaRegistroRepository.findAll();
    }

}
