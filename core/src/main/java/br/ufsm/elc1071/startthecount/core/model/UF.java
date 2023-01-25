package br.ufsm.elc1071.startthecount.core.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum UF {

    AC ("Acre"),
    AL ("Alagoas"),
    AM ("Amazonas"),
    AP ("Amapá"),
    BA ("Bahia"),
    CE ("Ceará"),
    DF ("Distrito Federal"),
    ES ("Espírito Santo"),
    GO ("Goiás"),
    MA ("Maranhão"),
    MG ("Minas Gerais"),
    MS ("Mato Grosso do Sul"),
    MT ("Mato Grosso"),
    PA ("Pará"),
    PB ("Paraíba"),
    PE ("Pernambuco"),
    PI ("Piauí"),
    PR ("Paraná"),
    RJ ("Rio de Janeiro"),
    RN ("Rio Grande do Norte"),
    RO ("Rondônia"),
    RR ("Roraima"),
    RS ("Rio Grande do Sul"),
    SC ("Santa Cataria"),
    SE ("Sergipe"),
    SP ("São Paulo"),
    TO ("Tocantins"),
    ZZ ("Exterior");

    private final String nome;

    public static Optional<UF> fromSigla(@NonNull String sigla) {
        for (UF value : values()) {
            if (value.name().equalsIgnoreCase(sigla)) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }

    public String getSigla() {
        return this.name();
    }
}
