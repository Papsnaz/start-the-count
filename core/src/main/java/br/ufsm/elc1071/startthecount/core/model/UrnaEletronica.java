package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UrnaEletronica {

    private Integer numeroSerie;

    private String codigoIdentificacaoCarga;

    private String versaoSoftware;

    private LocalDate dataAbertura;

    private LocalTime horarioAbertura;

    private LocalDate dataFechamento;

    private LocalTime horarioFechamento;
}
