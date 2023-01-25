package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmissaoBoletimUrna {

    private LocalDate data;

    private LocalTime horario;
}
