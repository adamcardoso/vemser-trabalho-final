package br.com.dbc.vemser.notifica.service.interfaces;

import br.com.dbc.vemser.notifica.dto.curtida.CurtidaDto;

public interface ICurtidaService {
    void apoiar(CurtidaDto curtidaDto) throws Exception;
}