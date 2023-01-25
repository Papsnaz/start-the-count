package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

@Getter
@Setter
@ToString
public class Partido {

    private Integer numeroTSE;

    private String nome;

    private String sigla;

    private Integer quantidadeVotosDeLegenda;

    private Integer totalVotosApurados;

    public Partido() {
        this.quantidadeVotosDeLegenda = 0;
        this.totalVotosApurados = 0;
    }

    public Partido(Integer numeroTSE) {
        this();
        this.numeroTSE = numeroTSE;
    }

    public Partido(Integer numeroTSE, Integer totalVotosApurados) {
        this(numeroTSE);
        this.totalVotosApurados = totalVotosApurados;
    }
}
