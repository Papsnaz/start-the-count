package br.ufsm.elc1071.startthecount.core.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum Fase {

    SIMULADO    (1, "Simulado", "S"),
    OFICIAL     (2, "Oficial", "O"),
    TREINAMENTO (3, "Treinamento", "T");

    private final Integer codigoTSE;

    private final String nome;

    private final String abreviacao;

    public static Optional<Fase> fromAbreviacao(@NonNull String abreviacao) {
        for (Fase value : values()) {
            if (value.abreviacao.equalsIgnoreCase(abreviacao)) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
