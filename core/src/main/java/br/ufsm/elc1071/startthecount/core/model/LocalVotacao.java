package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LocalVotacao {

    private Integer numeroTSE;

    private String nome;

    private Zona zona;

    private Municipio municipio;
}
