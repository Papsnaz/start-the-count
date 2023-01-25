package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

import br.ufsm.elc1071.startthecount.util.StringUtil;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VersaoFormatoRepresentacao {

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final String SPLIT_PATTERN = "\\.";

    private Integer numeroCiclosEleitoraisDesdeImplementacao;

    private Integer numeroRevisoesFormatoCiclo;

    public VersaoFormatoRepresentacao(@NonNull String string) {
        String[] strings = string.split(SPLIT_PATTERN);

        this.numeroCiclosEleitoraisDesdeImplementacao = StringUtil.toInteger(strings[0]);
        this.numeroRevisoesFormatoCiclo = StringUtil.toInteger(strings[1]);
    }

    @Override
    public String toString() {
        return String.format("%d.%d", this.numeroCiclosEleitoraisDesdeImplementacao, this.numeroRevisoesFormatoCiclo);
    }
}
