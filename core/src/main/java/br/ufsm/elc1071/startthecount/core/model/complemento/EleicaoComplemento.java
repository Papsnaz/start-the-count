package br.ufsm.elc1071.startthecount.core.model.complemento;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class EleicaoComplemento {

    @SerializedName(value = "codigo", alternate = {"codigoTSE"})
    private Integer codigoTSE;

    private String nome;

    private List<PartidoCargoComplemento> partidosPorCargos;

    public Map<Integer, CargoComplemento> getCargos() {
        Map<Integer, CargoComplemento> cargos = new HashMap<>();

        for (PartidoCargoComplemento partidoCargoComplemento : this.partidosPorCargos) {
            cargos.put(partidoCargoComplemento.getCargo().getCodigoTSE(), partidoCargoComplemento.getCargo());
        }

        return cargos;
    }

    public Map<Integer, PartidoComplemento> getPartidos() {
        Map<Integer, PartidoComplemento> partidos = new HashMap<>();

        for (PartidoCargoComplemento partidoCargoComplemento : this.partidosPorCargos) {
            for (CandidaturaPartidoComplemento candidaturaPartido : partidoCargoComplemento.getCandidaturasPorPartidos()) {
                partidos.put(candidaturaPartido.getPartido().getNumeroTSE(), candidaturaPartido.getPartido());
            }
        }

        return partidos;
    }

    public Map<Integer, CandidatoComplemento> getCandidatosTitulares() {
        Map<Integer, CandidatoComplemento> candidatosTitulares = new HashMap<>();

        for (PartidoCargoComplemento partidoCargoComplemento : this.partidosPorCargos) {
            candidatosTitulares.putAll(partidoCargoComplemento.getCandidatosTitulares());
        }

        return candidatosTitulares;
    }
}
