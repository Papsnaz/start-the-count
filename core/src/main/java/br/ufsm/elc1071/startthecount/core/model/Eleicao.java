package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Eleicao {

    private Integer codigoTSE;

    private Pleito pleito;

    private String nome;

    private Integer ano;

    private Integer quantidadeVotosCargosMajoritarios;

    private Integer quantidadeVotosCargosProporcionais;

    public Eleicao(Integer codigoTSE, Pleito pleito, Integer ano) {
        this.codigoTSE = codigoTSE;
        this.pleito = pleito;
        this.ano = ano;
    }
}
