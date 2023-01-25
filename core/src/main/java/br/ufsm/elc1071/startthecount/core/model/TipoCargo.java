package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TipoCargo {

    private Integer codigoTSE;

    private String nome;

    public TipoCargo(Integer codigoTSE) {
        this.codigoTSE = codigoTSE;
    }
}
