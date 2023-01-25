package br.ufsm.elc1071.startthecount.rest.dto.build;

import br.ufsm.elc1071.startthecount.rest.exception.CamposFaltantesException;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoletimUrnaBuildDTO {

    @NotNull(message = "O(s) payload(s) do(s) QR code(s) do boletim de urna não pode(m) ser nulo(s).")
    @NotEmpty(message = "O(s) payload(s) do(s) QR code(s) do boletim de urna não pode(m) estar vazio(s).")
    private List<String> payloads;

    public void validate() {
        if (Objects.isNull(this.payloads)) {
            throw new CamposFaltantesException("O(s) payload(s) do(s) QR code(s) do boletim de urna devem ser informados para construir um boletim de urna.");
        }
    }
}
