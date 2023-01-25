package br.ufsm.elc1071.startthecount.core.model.complemento;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PartidoComplemento {

    @SerializedName(value = "numero", alternate = {"numeroTSE"})
    private Integer numeroTSE;

    private String nome;

    private String sigla;
}
