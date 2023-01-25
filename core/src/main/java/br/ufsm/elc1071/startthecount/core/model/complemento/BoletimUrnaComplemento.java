package br.ufsm.elc1071.startthecount.core.model.complemento;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoletimUrnaComplemento {

    private String assinatura;

    private ProcessoEleitoralComplemento processoEleitoral;
}
