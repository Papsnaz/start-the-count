package br.ufsm.elc1071.startthecount.core.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum OrigemBoletimUrna {

    SOFTWARE_DE_VOTACAO  ("Software de Votação", "Vota"),
    RECUPERADOR_DE_DADOS ("Recuperador de Dados", "RED"),
    SISTEMA_DE_APURACAO  ("Sistema de Apuração", "SA");

    private final String nome;

    private final String abreviacao;

    public static Optional<OrigemBoletimUrna> fromAbreviacao(@NonNull String abreviacao) {
        for (OrigemBoletimUrna value : values()) {
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
