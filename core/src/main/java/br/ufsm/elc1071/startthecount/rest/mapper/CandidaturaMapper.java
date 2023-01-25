package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.id.CandidaturaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.CandidaturaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.Candidatura;
import br.ufsm.elc1071.startthecount.rest.service.CandidatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CandidaturaMapper {

    private final CargoEleicaoMapper cargoEleicaoMapper;
    private final CandidatoService candidatoService;

    public CandidaturaRetrievalDTO toCandidaturaRetrievalDTO(Candidatura candidatura) {
        return new CandidaturaRetrievalDTO(
            candidatura.getId(),
            this.candidatoService.findByCodigoTSE(candidatura.getCandidato().getCodigoTSE()),
            this.cargoEleicaoMapper.toCargoEleicaoRetrievalDTO(candidatura.getCargoEleicao()),
            candidatura.getPartido()
        );
    }

    public CandidaturaIdDTO toCandidaturaIdDTO(Candidatura candidatura) {
        return new CandidaturaIdDTO(
            candidatura.getCandidato().getNumeroTSE(),
            candidatura.getCargoEleicao().getCargo().getCodigoTSE(),
            candidatura.getCargoEleicao().getEleicao().getCodigoTSE()
        );
    }
}
