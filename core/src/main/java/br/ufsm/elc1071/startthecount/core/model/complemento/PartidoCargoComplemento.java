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
public class PartidoCargoComplemento {

    private List<CandidaturaPartidoComplemento> candidaturasPorPartidos;

    private CargoComplemento cargo;

    public Map<Integer, CandidatoComplemento> getCandidatosTitulares() {
        Map<Integer, CandidatoComplemento> candidatosTitulares = new HashMap<>();

        for (CandidaturaPartidoComplemento candidaturaPartido : this.candidaturasPorPartidos) {
            candidatosTitulares.putAll(candidaturaPartido.getCandidatosTitulares());
        }

        return candidatosTitulares;
    }
}
