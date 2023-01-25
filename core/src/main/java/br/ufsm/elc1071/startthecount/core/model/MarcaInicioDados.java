package br.ufsm.elc1071.startthecount.core.model;

import lombok.*;

import br.ufsm.elc1071.startthecount.util.StringUtil;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarcaInicioDados {

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final String SPLIT_PATTERN = ":";

    private Integer indiceQRCode;

    private Integer quantidadeTotalQRCodes;

    public MarcaInicioDados(@NonNull String string) {
        String[] strings = string.split(SPLIT_PATTERN);

        this.indiceQRCode = StringUtil.toInteger(strings[0]);
        this.quantidadeTotalQRCodes = StringUtil.toInteger(strings[1]);
    }

    @Override
    public String toString() {
        return String.format("%d:%d", this.indiceQRCode, this.quantidadeTotalQRCodes);
    }
}
