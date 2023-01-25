package br.ufsm.elc1071.startthecount.core.model.complemento;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MunicipioComplemento {

    @SerializedName(value = "numero", alternate = {"codigoTSE"})
    private Integer codigoTSE;

    private String nome;
}
