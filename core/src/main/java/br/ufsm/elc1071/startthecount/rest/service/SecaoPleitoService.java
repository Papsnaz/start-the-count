package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoPleitoIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.mapper.SecaoPleitoMapper;
import br.ufsm.elc1071.startthecount.rest.model.SecaoPleito;
import br.ufsm.elc1071.startthecount.rest.repository.SecaoPleitoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecaoPleitoService {

    private final SecaoPleitoRepository secaoPleitoRepository;
    private final SecaoPleitoMapper secaoPleitoMapper;
    private final BoletimUrnaService boletimUrnaService;

    public SecaoPleito findById(SecaoPleitoIdDTO id) {
        return this.secaoPleitoRepository
            .findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndPleitoCodigoTSE(
                id.getNumeroSecaoTSE(),
                id.getNumeroZonaTSE(),
                id.getSiglaUF(),
                id.getCodigoPleitoTSE()
            )
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma instância de SecaoPleito identificada por %s.", id));
            });
    }

    public List<SecaoPleito> findAll() {
        return this.secaoPleitoRepository.findAll();
    }

    public void saveIfDoesNotExist(SecaoPleito secaoPleito) {
        if (!this.secaoPleitoRepository.existsBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndPleitoCodigoTSE(
            secaoPleito.getSecao().getNumeroTSE(),
            secaoPleito.getSecao().getZona().getNumeroTSE(),
            secaoPleito.getSecao().getZona().getUF().getSigla(),
            secaoPleito.getPleito().getCodigoTSE()
        )) {
            this.secaoPleitoRepository.save(secaoPleito);
        }
    }

    public void deleteById(SecaoPleitoIdDTO id) {
        id.validate();

        if (!this.secaoPleitoRepository.existsBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndPleitoCodigoTSE(
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF(),
            id.getCodigoPleitoTSE()
        )) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma instância de SecaoPleito identificada por %s.", id));
        }

        this.boletimUrnaService.deleteBySecaoPleitoId(id);

        this.secaoPleitoRepository.deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndPleitoCodigoTSE(
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF(),
            id.getCodigoPleitoTSE()
        );
    }

    public void deleteBySecaoId(SecaoIdDTO secaoId) {
        secaoId.validate();

        this.secaoPleitoRepository.findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(
            secaoId.getNumeroSecaoTSE(),
            secaoId.getNumeroZonaTSE(),
            secaoId.getSiglaUF()
        ).forEach(secaoPleito -> this.boletimUrnaService.deleteBySecaoPleitoId(this.secaoPleitoMapper.toSecaoPleitoIdDTO(secaoPleito)));

        this.secaoPleitoRepository.deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(
            secaoId.getNumeroSecaoTSE(),
            secaoId.getNumeroZonaTSE(),
            secaoId.getSiglaUF()
        );
    }

    public void deleteByCodigoPleitoTSE(int codigoPleitoTSE) {
        this.secaoPleitoRepository
            .findByPleitoCodigoTSE(codigoPleitoTSE)
            .forEach(secaoPleito -> this.boletimUrnaService.deleteBySecaoPleitoId(this.secaoPleitoMapper.toSecaoPleitoIdDTO(secaoPleito)));

        this.secaoPleitoRepository.deleteByPleitoCodigoTSE(codigoPleitoTSE);
    }
}
