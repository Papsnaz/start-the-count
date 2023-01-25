package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Candidatura {

    private Candidato candidato;

    private Cargo cargo;

    private Eleicao eleicao;

    private Partido partido;

    private Integer totalVotosApurados;
}
