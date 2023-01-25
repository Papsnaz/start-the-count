package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Secao {

    private Integer numeroTSE;

    private Zona zona;

    private Municipio municipio;

    private LocalVotacao localVotacao;

    private List<Secao> secoesAgregadas;

    private Integer quantidadeEleitoresAptos;

    private Integer quantidadeEleitoresComparecentes;

    private Integer quantidadeEleitoresFaltosos;

    private Integer quantidadeEleitoresHabilitadosPorAnoNascimento;

    public Secao(Integer numeroTSE, Zona zona, Municipio municipio) {
        this.numeroTSE = numeroTSE;
        this.zona = zona;
        this.municipio = municipio;
    }

    public void addSecaoAgregada(Secao secaoAgregada) {
        if (Objects.isNull(this.secoesAgregadas)) {
            this.secoesAgregadas = new LinkedList<>();
        }

        this.secoesAgregadas.add(secaoAgregada);
    }
}
