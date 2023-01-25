package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cargo {

    private Integer codigoTSE;

    private String nome;

    private String nomeAbreviado;

    private TipoCargo tipo;

    private String versaoPacoteDados;

    private Integer quantidadeEleitoresAptos;

    private Integer quantidadeComparecimentoCargoSemCandidatos;

    private Integer quantidadeVotosNominais;

    private Integer quantidadeVotosDeLegenda;

    private Integer quantidadeVotosEmBranco;

    private Integer quantidadeVotosNulos;

    private Integer totalVotosApurados;

    public Cargo (Integer codigoTSE) {
        this.codigoTSE = codigoTSE;
    }
}
