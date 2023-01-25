package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Municipio {

    private Integer codigoTSE;

    private String nome;

    private UF uf;
}
