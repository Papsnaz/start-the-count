package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Zona {

    private Integer numeroTSE;

    private UF uf;

    public UF getUF() {
        return this.uf;
    }
}
