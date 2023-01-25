package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoEleicaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.ZonaIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.mapper.SecaoEleicaoMapper;
import br.ufsm.elc1071.startthecount.rest.model.SecaoEleicao;
import br.ufsm.elc1071.startthecount.rest.repository.SecaoEleicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecaoEleicaoService {

    private final SecaoEleicaoRepository secaoEleicaoRepository;
    private final SecaoEleicaoMapper secaoEleicaoMapper;
    private final BoletimUrnaService boletimUrnaService;

    @Autowired
    public SecaoEleicaoService(SecaoEleicaoRepository secaoEleicaoRepository, @Lazy SecaoEleicaoMapper secaoEleicaoMapper, @Lazy BoletimUrnaService boletimUrnaService) {
        this.secaoEleicaoRepository = secaoEleicaoRepository;
        this.secaoEleicaoMapper = secaoEleicaoMapper;
        this.boletimUrnaService = boletimUrnaService;
    }

    public SecaoEleicao findById(SecaoEleicaoIdDTO id) {
        return this.secaoEleicaoRepository
            .findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndEleicaoCodigoTSE(id.getNumeroSecaoTSE(), id.getNumeroZonaTSE(), id.getSiglaUF(), id.getCodigoEleicaoTSE())
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma <seção, eleição> identificada por %s.", id));
            });
    }

    public List<SecaoEleicao> findAll() {
        return this.secaoEleicaoRepository.findAll();
    }

    public void saveIfDoesNotExist(SecaoEleicao secaoEleicao) {
        if (!this.secaoEleicaoRepository.existsBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndEleicaoCodigoTSE(
                secaoEleicao.getSecao().getNumeroTSE(),
                secaoEleicao.getSecao().getZona().getNumeroTSE(),
                secaoEleicao.getSecao().getZona().getUF().getSigla(),
                secaoEleicao.getEleicao().getCodigoTSE())
        ) {
            this.secaoEleicaoRepository.save(secaoEleicao);
        }
    }

    public void deleteById(SecaoEleicaoIdDTO id) {
        id.validate();

        if (!this.secaoEleicaoRepository.existsBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndEleicaoCodigoTSE(id.getNumeroSecaoTSE(), id.getNumeroZonaTSE(), id.getSiglaUF(), id.getCodigoEleicaoTSE())) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma instância de SecaoEleicao identificada por %s.", id));
        }

        this.secaoEleicaoRepository.deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndEleicaoCodigoTSE(id.getNumeroSecaoTSE(), id.getNumeroZonaTSE(), id.getSiglaUF(), id.getCodigoEleicaoTSE());
    }

    public void deleteByZonaId(ZonaIdDTO zonaId) {
        zonaId.validate();

        this.secaoEleicaoRepository.deleteBySecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(zonaId.getNumeroZonaTSE(), zonaId.getSiglaUF());
    }

    public void deleteBySecaoId(SecaoIdDTO secaoId) {
        secaoId.validate();

        this.secaoEleicaoRepository.deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(secaoId.getNumeroSecaoTSE(), secaoId.getNumeroZonaTSE(), secaoId.getSiglaUF());
    }

    public void deleteByCodigoEleicaoTSE(Integer codigoEleicaoTSE) {
        this.secaoEleicaoRepository.deleteByEleicaoCodigoTSE(codigoEleicaoTSE);
    }
}
