package br.com.dbc.vemser.notifica.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Estatistica {
    private String classificacao;
    private int total;
    private double percentual;
}
