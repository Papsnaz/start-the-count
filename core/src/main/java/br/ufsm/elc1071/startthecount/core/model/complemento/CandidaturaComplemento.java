package br.ufsm.elc1071.startthecount.core.model.complemento;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CandidaturaComplemento {

    @SerializedName(value = "numero", alternate = {"numeroCandidatoTSE"})
    private Integer numeroCandidatoTSE;

    private CandidatoComplemento titular;

    private List<CandidatoComplemento> suplentes;
}
