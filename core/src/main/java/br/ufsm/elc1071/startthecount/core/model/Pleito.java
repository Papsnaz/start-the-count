package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Pleito {

    private Integer codigoTSE;

    private String nome;

    private Turno turno;

    private LocalDate data;

    private ProcessoEleitoral processoEleitoral;

    public Integer getAno() {
        return this.data.getYear();
    }
}
