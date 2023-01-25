package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.id.CargoEleicaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.CargoEleicaoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.CargoEleicao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CargoEleicaoMapper {

    public CargoEleicaoRetrievalDTO toCargoEleicaoRetrievalDTO(CargoEleicao cargoEleicao) {
        return new CargoEleicaoRetrievalDTO(
            cargoEleicao.getId(),
            cargoEleicao.getCargo(),
            cargoEleicao.getEleicao()
        );
    }

    public CargoEleicaoIdDTO toCargoEleicaoIdDTO(CargoEleicao cargoEleicao) {
        return new CargoEleicaoIdDTO(
            cargoEleicao.getCargo().getCodigoTSE(),
            cargoEleicao.getEleicao().getCodigoTSE()
        );
    }
}
