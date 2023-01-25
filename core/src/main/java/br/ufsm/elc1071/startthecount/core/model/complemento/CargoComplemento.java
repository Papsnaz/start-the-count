package br.ufsm.elc1071.startthecount.core.model.complemento;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CargoComplemento {

    @SerializedName(value = "codigo", alternate = {"codigoTSE"})
    private Integer codigoTSE;

    private String nomeMasculino;

    private String nomeFeminino;

    private String nomeNeutro;

    private String nomeAbreviado;

    private String versao;
}
