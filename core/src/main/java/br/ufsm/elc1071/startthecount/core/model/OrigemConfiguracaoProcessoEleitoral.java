package br.ufsm.elc1071.startthecount.core.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum OrigemConfiguracaoProcessoEleitoral {

    ELEICAO_LEGAL_OFICIAL ("Eleição legal oficial", "LEG"),
    ELEICAO_COMUNITARIA   ("Eleicao comunitária", "COM");

    private final String nome;

    private final String abreviacao;

    public static Optional<OrigemConfiguracaoProcessoEleitoral> fromAbreviacao(@NonNull String abreviacao) {
        for (OrigemConfiguracaoProcessoEleitoral value : values()) {
            if (value.abreviacao.equalsIgnoreCase(abreviacao)) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }

    public String toStringURLVerificacaoAssinatura() {
        if (this == OrigemConfiguracaoProcessoEleitoral.ELEICAO_LEGAL_OFICIAL) {
            return "LEGAL";
        }

        return "COMUNITARIA";
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
