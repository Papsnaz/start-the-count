package br.ufsm.elc1071.startthecount.rest.dto.retrieval;

import br.ufsm.elc1071.startthecount.rest.model.Fase;
import br.ufsm.elc1071.startthecount.rest.model.OrigemBoletimUrna;
import br.ufsm.elc1071.startthecount.rest.model.UrnaEletronica;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"id", "usuario", "secaoPleito", "origem", "urnaEletronica", "assinatura", "quantidadeTotalQRCodes", "dataEmissao", "horarioEmissao", "coletadoEm", "atualizadoEm"})
public class BoletimUrnaRetrievalDTO {

    private Integer id;

    @JsonProperty(value = "usuario")
    private UsuarioRetrievalDTO usuarioRetrievalDTO;

    @JsonProperty(value = "secaoPleito")
    private SecaoPleitoRetrievalDTO secaoPleitoRetrievalDTO;

    private Fase fase;

    private OrigemBoletimUrna origem;

    private UrnaEletronica urnaEletronica;

    private String assinatura;

    private Integer quantidadeTotalQRCodes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataEmissao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioEmissao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime coletadoEm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime atualizadoEm;
}
