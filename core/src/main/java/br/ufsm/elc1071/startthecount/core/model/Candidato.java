package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

@Getter
@Setter
@ToString
public class Candidato {

    private Integer numeroTSE;

    public Candidato(Integer numeroTSE) {
        this.numeroTSE = numeroTSE;
    }
}
