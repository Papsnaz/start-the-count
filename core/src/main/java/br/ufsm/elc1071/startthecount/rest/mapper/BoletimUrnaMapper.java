package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.id.BoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.BoletimUrnaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.BoletimUrna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BoletimUrnaMapper {

    private final UsuarioMapper usuarioMapper;
    private final SecaoPleitoMapper secaoPleitoMapper;

    @Autowired
    public BoletimUrnaMapper(UsuarioMapper usuarioMapper, @Lazy SecaoPleitoMapper secaoPleitoMapper) {
        this.usuarioMapper = usuarioMapper;
        this.secaoPleitoMapper = secaoPleitoMapper;
    }

    public BoletimUrnaRetrievalDTO toBoletimUrnaRetrievalDTO(BoletimUrna boletimUrna) {
        return new BoletimUrnaRetrievalDTO(
            boletimUrna.getId(),
            this.usuarioMapper.toUsuarioRetrievalDTO(boletimUrna.getUsuario()),
            this.secaoPleitoMapper.toSecaoPleitoRetrievalDTO(boletimUrna.getSecaoPleito()),
            boletimUrna.getFase(),
            boletimUrna.getOrigem(),
            boletimUrna.getUrnaEletronica(),
            boletimUrna.getAssinatura(),
            boletimUrna.getQuantidadeTotalQRCodes(),
            boletimUrna.getDataEmissao(),
            boletimUrna.getHorarioEmissao(),
            boletimUrna.getColetadoEm(),
            boletimUrna.getAtualizadoEm()
        );
    }

    public BoletimUrnaIdDTO toBoletimUrnaIdDTO(BoletimUrna boletimUrna) {
        return new BoletimUrnaIdDTO(
            boletimUrna.getUsuario().getUsername(),
            boletimUrna.getSecaoPleito().getPleito().getCodigoTSE(),
            boletimUrna.getSecaoPleito().getSecao().getNumeroTSE(),
            boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla()
        );
    }
}
