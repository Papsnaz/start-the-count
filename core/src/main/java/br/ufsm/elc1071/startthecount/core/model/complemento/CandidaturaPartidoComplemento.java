package br.ufsm.elc1071.startthecount.core.model.complemento;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class CandidaturaPartidoComplemento {

    private PartidoComplemento partido;

    private List<CandidaturaComplemento> candidaturas;

    public Map<Integer, CandidaturaComplemento> getCandidaturas() {
        Map<Integer, CandidaturaComplemento> candidaturas = new HashMap<>();

        for (CandidaturaComplemento candidatura : this.candidaturas) {
            candidaturas.put(
                candidatura.getNumeroCandidatoTSE(),
                candidatura
            );
        }

        return candidaturas;
    }

    public Map<Integer, CandidatoComplemento> getCandidatosTitulares() {
        Map<Integer, CandidatoComplemento> candidatosTitulares = new HashMap<>();

        for (CandidaturaComplemento candidatura : this.candidaturas) {
            candidatosTitulares.put(
                candidatura.getNumeroCandidatoTSE(),
                candidatura.getTitular()
            );
        }

        return candidatosTitulares;
    }
}
