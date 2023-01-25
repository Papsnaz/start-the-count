package br.ufsm.elc1071.startthecount.core.model.complemento;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CandidatoComplemento {

    @SerializedName(value = "codigo", alternate = {"codigoTSE"})
    private String codigoTSE;

    private String nome;
}
