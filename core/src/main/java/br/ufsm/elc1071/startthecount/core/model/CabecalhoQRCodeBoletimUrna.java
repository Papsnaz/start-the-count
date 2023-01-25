package br.ufsm.elc1071.startthecount.core.model;

import br.ufsm.elc1071.startthecount.util.StringUtil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CabecalhoQRCodeBoletimUrna {

    private MarcaInicioDados marcaInicioDados;

    private VersaoFormatoRepresentacao versaoFormatoRepresentacao;

    private Integer versaoChaveAssinatura;

    public void setMarcaInicioDados(@NonNull String string) {
        this.marcaInicioDados = new MarcaInicioDados(string);
    }

    public void setVersaoFormatoRepresentacao(@NonNull String string) {
        this.versaoFormatoRepresentacao = new VersaoFormatoRepresentacao(string);
    }

    public void setVersaoChaveAssinatura(String versaoChaveAssinatura) {
        this.versaoChaveAssinatura = StringUtil.toInteger(versaoChaveAssinatura);
    }
}
