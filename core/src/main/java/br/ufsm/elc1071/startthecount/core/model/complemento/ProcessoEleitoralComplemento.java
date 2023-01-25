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
public class ProcessoEleitoralComplemento {

    @SerializedName(value = "codigo", alternate = {"codigoTSE"})
    private Integer codigoTSE;

    private List<EleicaoComplemento> eleicoes;

    private List<String> consultasPopulares;

    private MunicipioComplemento municipio;

    private String nome;

    private PleitoComplemento pleito;

    public Map<Integer, EleicaoComplemento> getEleicoes() {
        Map<Integer, EleicaoComplemento> eleicoes = new HashMap<>();

        for (EleicaoComplemento eleicaoComplemento : this.eleicoes) {
            eleicoes.put(eleicaoComplemento.getCodigoTSE(), eleicaoComplemento);
        }

        return eleicoes;
    }

    public Map<Integer, CargoComplemento> getCargos() {
        Map<Integer, CargoComplemento> cargos = new HashMap<>();

        for (EleicaoComplemento eleicaoComplemento : this.eleicoes) {
            cargos.putAll(eleicaoComplemento.getCargos());
        }

        return cargos;
    }

    public Map<Integer, PartidoComplemento> getPartidos() {
        Map<Integer, PartidoComplemento> partidos = new HashMap<>();

        for (EleicaoComplemento eleicaoComplemento : this.eleicoes) {
            partidos.putAll(eleicaoComplemento.getPartidos());
        }

        return partidos;
    }

    public Map<Integer, CandidatoComplemento> getCandidatosTitulares() {
        Map<Integer, CandidatoComplemento> candidatosTitulares = new HashMap<>();

        for (EleicaoComplemento eleicaoComplemento : this.eleicoes) {
            candidatosTitulares.putAll(eleicaoComplemento.getCandidatosTitulares());
        }

        return candidatosTitulares;
    }
}
