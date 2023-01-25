package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.core.model.*;
import br.ufsm.elc1071.startthecount.core.model.complemento.BoletimUrnaComplemento;
import br.ufsm.elc1071.startthecount.core.model.complemento.CargoComplemento;
import br.ufsm.elc1071.startthecount.core.model.complemento.EleicaoComplemento;
import br.ufsm.elc1071.startthecount.core.model.complemento.PartidoComplemento;
import br.ufsm.elc1071.startthecount.core.service.BoletimUrnaQRCodeBuilder;
import br.ufsm.elc1071.startthecount.rest.dto.build.BoletimUrnaBuildDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.*;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeJaExisteException;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.mapper.BoletimUrnaMapper;
import br.ufsm.elc1071.startthecount.rest.model.Candidato;
import br.ufsm.elc1071.startthecount.rest.model.Candidatura;
import br.ufsm.elc1071.startthecount.rest.model.Cargo;
import br.ufsm.elc1071.startthecount.rest.model.Eleicao;
import br.ufsm.elc1071.startthecount.rest.model.Fase;
import br.ufsm.elc1071.startthecount.rest.model.LocalVotacao;
import br.ufsm.elc1071.startthecount.rest.model.Municipio;
import br.ufsm.elc1071.startthecount.rest.model.OrigemBoletimUrna;
import br.ufsm.elc1071.startthecount.rest.model.Partido;
import br.ufsm.elc1071.startthecount.rest.model.Pleito;
import br.ufsm.elc1071.startthecount.rest.model.ProcessoEleitoral;
import br.ufsm.elc1071.startthecount.rest.model.QRCodeBoletimUrna;
import br.ufsm.elc1071.startthecount.rest.model.Secao;
import br.ufsm.elc1071.startthecount.rest.model.TipoCargo;
import br.ufsm.elc1071.startthecount.rest.model.UF;
import br.ufsm.elc1071.startthecount.rest.model.UrnaEletronica;
import br.ufsm.elc1071.startthecount.rest.model.Zona;
import br.ufsm.elc1071.startthecount.rest.model.*;
import br.ufsm.elc1071.startthecount.rest.repository.BoletimUrnaRepository;
import br.ufsm.elc1071.startthecount.util.StringUtil;

import org.apache.commons.collections4.map.MultiKeyMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BoletimUrnaService {

    private final UsuarioService usuarioService;
    private final UFService ufService;
    private final UrnaEletronicaService urnaEletronicaService;
    private final MunicipioService municipioService;
    private final ZonaService zonaService;
    private final SecaoService secaoService;
    private final LocalVotacaoService localVotacaoService;
    private final OrigemConfiguracaoProcessoEleitoralService origemConfiguracaoProcessoEleitoralService;
    private final ProcessoEleitoralService processoEleitoralService;
    private final PleitoService pleitoService;
    private final EleicaoService eleicaoService;
    private final SecaoProcessoEleitoralService secaoProcessoEleitoralService;
    private final SecaoPleitoService secaoPleitoService;
    private final SecaoEleicaoService secaoEleicaoService;
    private final TipoCargoService tipoCargoService;
    private final CargoService cargoService;
    private final CargoEleicaoService cargoEleicaoService;
    private final PartidoService partidoService;
    private final CandidatoService candidatoService;
    private final CandidaturaService candidaturaService;
    private final FaseService faseService;
    private final OrigemBoletimUrnaService origemBoletimUrnaService;
    private final QRCodeBoletimUrnaService qrCodeBoletimUrnaService;
    private final ApuracaoVotosCandidaturaBoletimUrnaService apuracaoVotosCandidaturaBoletimUrnaService;
    private final ApuracaoVotosCargoBoletimUrnaService apuracaoVotosCargoBoletimUrnaService;
    private final ApuracaoVotosPartidoBoletimUrnaService apuracaoVotosPartidoBoletimUrnaService;
    private final BoletimUrnaRepository boletimUrnaRepository;
    private final BoletimUrnaMapper boletimUrnaMapper;

    @Autowired
    public BoletimUrnaService(
        UsuarioService usuarioService,
        UFService ufService,
        UrnaEletronicaService urnaEletronicaService,
        MunicipioService municipioService,
        ZonaService zonaService,
        SecaoService secaoService,
        LocalVotacaoService localVotacaoService,
        OrigemConfiguracaoProcessoEleitoralService origemConfiguracaoProcessoEleitoralService,
        ProcessoEleitoralService processoEleitoralService,
        PleitoService pleitoService,
        EleicaoService eleicaoService,
        SecaoProcessoEleitoralService secaoProcessoEleitoralService,
        @Lazy SecaoPleitoService secaoPleitoService,
        SecaoEleicaoService secaoEleicaoService,
        TipoCargoService tipoCargoService,
        CargoService cargoService,
        CargoEleicaoService cargoEleicaoService,
        PartidoService partidoService,
        CandidatoService candidatoService,
        CandidaturaService candidaturaService,
        FaseService faseService,
        OrigemBoletimUrnaService origemBoletimUrnaService,
        QRCodeBoletimUrnaService qrCodeBoletimUrnaService,
        ApuracaoVotosCandidaturaBoletimUrnaService apuracaoVotosCandidaturaBoletimUrnaService,
        ApuracaoVotosCargoBoletimUrnaService apuracaoVotosCargoBoletimUrnaService,
        ApuracaoVotosPartidoBoletimUrnaService apuracaoVotosPartidoBoletimUrnaService,
        BoletimUrnaRepository boletimUrnaRepository,
        @Lazy BoletimUrnaMapper boletimUrnaMapper
    ) {
        this.usuarioService = usuarioService;
        this.ufService = ufService;
        this.urnaEletronicaService = urnaEletronicaService;
        this.municipioService = municipioService;
        this.zonaService = zonaService;
        this.secaoService = secaoService;
        this.localVotacaoService = localVotacaoService;
        this.origemConfiguracaoProcessoEleitoralService = origemConfiguracaoProcessoEleitoralService;
        this.processoEleitoralService = processoEleitoralService;
        this.pleitoService = pleitoService;
        this.eleicaoService = eleicaoService;
        this.secaoProcessoEleitoralService = secaoProcessoEleitoralService;
        this.secaoPleitoService = secaoPleitoService;
        this.secaoEleicaoService = secaoEleicaoService;
        this.tipoCargoService = tipoCargoService;
        this.cargoService = cargoService;
        this.cargoEleicaoService = cargoEleicaoService;
        this.partidoService = partidoService;
        this.candidatoService = candidatoService;
        this.candidaturaService = candidaturaService;
        this.faseService = faseService;
        this.origemBoletimUrnaService = origemBoletimUrnaService;
        this.qrCodeBoletimUrnaService = qrCodeBoletimUrnaService;
        this.apuracaoVotosCandidaturaBoletimUrnaService = apuracaoVotosCandidaturaBoletimUrnaService;
        this.apuracaoVotosCargoBoletimUrnaService = apuracaoVotosCargoBoletimUrnaService;
        this.apuracaoVotosPartidoBoletimUrnaService = apuracaoVotosPartidoBoletimUrnaService;
        this.boletimUrnaRepository = boletimUrnaRepository;
        this.boletimUrnaMapper = boletimUrnaMapper;
    }

    public BoletimUrna findById(BoletimUrnaIdDTO id) {
        return this.boletimUrnaRepository
            .findByUsuarioUsernameEqualsIgnoreCaseAndSecaoPleitoPleitoCodigoTSEAndSecaoPleitoSecaoNumeroTSEAndSecaoPleitoSecaoZonaNumeroTSEAndSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
                id.getUsername(),
                id.getCodigoPleitoTSE(),
                id.getNumeroSecaoTSE(),
                id.getNumeroZonaTSE(),
                id.getSiglaUF()
            )
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum boletim de urna identificado por %s.", id));
            });
    }

    public List<BoletimUrna> findAll() {
        return this.boletimUrnaRepository.findAll();
    }

    public void saveIfDoesNotExist(BoletimUrna boletimUrna) {
        if (this.boletimUrnaRepository.existsByUsuarioUsernameEqualsIgnoreCaseAndSecaoPleitoPleitoCodigoTSEAndSecaoPleitoSecaoNumeroTSEAndSecaoPleitoSecaoZonaNumeroTSEAndSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            boletimUrna.getUsuario().getUsername(),
            boletimUrna.getSecaoPleito().getPleito().getCodigoTSE(),
            boletimUrna.getSecaoPleito().getSecao().getNumeroTSE(),
            boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla()
        )) {
            throw new EntidadeJaExisteException(
                String.format(
                    "O usuário \"%s\" já coletou um boletim de urna no %s na UF \"%s\", zona %d e seção %d.",
                    boletimUrna.getUsuario().getUsername(),
                    boletimUrna.getSecaoPleito().getPleito().getNome(),
                    boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla(),
                    boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(),
                    boletimUrna.getSecaoPleito().getSecao().getNumeroTSE()
                )
            );
        }

        this.boletimUrnaRepository.save(boletimUrna);
    }

    public void deleteById(BoletimUrnaIdDTO id) {
        id.validate();

        if (!this.boletimUrnaRepository.existsByUsuarioUsernameEqualsIgnoreCaseAndSecaoPleitoPleitoCodigoTSEAndSecaoPleitoSecaoNumeroTSEAndSecaoPleitoSecaoZonaNumeroTSEAndSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            id.getUsername(),
            id.getCodigoPleitoTSE(),
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF()
        )) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum boletim de urna identificado por %s.", id));
        }

        this.qrCodeBoletimUrnaService.deleteByBoletimUrnaId(id);
        this.apuracaoVotosCandidaturaBoletimUrnaService.deleteByBoletimUrnaId(id);
        this.apuracaoVotosCargoBoletimUrnaService.deleteByBoletimUrnaId(id);
        this.apuracaoVotosPartidoBoletimUrnaService.deleteByBoletimUrnaId(id);

        this.boletimUrnaRepository.deleteByUsuarioUsernameEqualsIgnoreCaseAndSecaoPleitoPleitoCodigoTSEAndSecaoPleitoSecaoNumeroTSEAndSecaoPleitoSecaoZonaNumeroTSEAndSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            id.getUsername(),
            id.getCodigoPleitoTSE(),
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF()
        );
    }

    public void deleteBySecaoPleitoId(SecaoPleitoIdDTO id) {
        id.validate();

        this.boletimUrnaRepository
            .findBySecaoPleitoPleitoCodigoTSEAndSecaoPleitoSecaoNumeroTSEAndSecaoPleitoSecaoZonaNumeroTSEAndSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
                id.getCodigoPleitoTSE(),
                id.getNumeroSecaoTSE(),
                id.getNumeroZonaTSE(),
                id.getSiglaUF()
            ).forEach(boletimUrna -> {
                BoletimUrnaIdDTO boletimUrnaIdDTO = this.boletimUrnaMapper.toBoletimUrnaIdDTO(boletimUrna);

                this.qrCodeBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
                this.apuracaoVotosCandidaturaBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
                this.apuracaoVotosCargoBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
                this.apuracaoVotosPartidoBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
            });

        this.boletimUrnaRepository.deleteBySecaoPleitoPleitoCodigoTSEAndSecaoPleitoSecaoNumeroTSEAndSecaoPleitoSecaoZonaNumeroTSEAndSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            id.getCodigoPleitoTSE(),
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF()
        );
    }

    public void deleteByUsername(String username) {
        this.boletimUrnaRepository
            .findByUsuarioUsernameEqualsIgnoreCase(username)
            .forEach(boletimUrna -> {
                BoletimUrnaIdDTO boletimUrnaIdDTO = this.boletimUrnaMapper.toBoletimUrnaIdDTO(boletimUrna);

                this.qrCodeBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
                this.apuracaoVotosCandidaturaBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
                this.apuracaoVotosCargoBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
                this.apuracaoVotosPartidoBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
            });

        this.boletimUrnaRepository.deleteByUsuarioUsernameEqualsIgnoreCase(username);
    }

    public void deleteByNomeFase(String nomefase) {
        this.boletimUrnaRepository
            .findByFaseNomeEqualsIgnoreCase(nomefase)
            .forEach(boletimUrna -> {
                BoletimUrnaIdDTO boletimUrnaIdDTO = this.boletimUrnaMapper.toBoletimUrnaIdDTO(boletimUrna);

                this.qrCodeBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
                this.apuracaoVotosCandidaturaBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
                this.apuracaoVotosCargoBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
                this.apuracaoVotosPartidoBoletimUrnaService.deleteByBoletimUrnaId(boletimUrnaIdDTO);
            });

        this.boletimUrnaRepository.deleteByFaseNomeEqualsIgnoreCase(nomefase);
    }

    public BoletimUrna build(BoletimUrnaBuildDTO boletimUrnaBuildDTO, String username) {
        boletimUrnaBuildDTO.validate();

        BoletimUrnaQRCode boletimUrnaQRCode = BoletimUrnaQRCodeBuilder.build(boletimUrnaBuildDTO.getPayloads());

        boletimUrnaQRCode.verificarAssinatura();

        BoletimUrnaComplemento boletimUrnaComplemento = boletimUrnaQRCode.getDadosComplementares();

        UF uf = this.ufService.findBySigla(boletimUrnaQRCode.getConteudo().getUf().getSigla());

        UrnaEletronica urnaEletronica = new UrnaEletronica(
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getNumeroSerie(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getCodigoIdentificacaoCarga(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getVersaoSoftware(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getDataAbertura(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getHorarioAbertura(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getDataFechamento(),
            boletimUrnaQRCode.getConteudo().getUrnaEletronica().getHorarioFechamento()
        );

        this.urnaEletronicaService.saveIfDoesNotExist(urnaEletronica);

        Municipio municipio = new Municipio(
            boletimUrnaComplemento.getProcessoEleitoral().getMunicipio().getCodigoTSE(),
            boletimUrnaComplemento.getProcessoEleitoral().getMunicipio().getNome(),
            uf
        );

        this.municipioService.saveIfDoesNotExist(municipio);

        Zona zona = new Zona(
            boletimUrnaQRCode.getConteudo().getZona().getNumeroTSE(),
            uf
        );

        this.zonaService.saveIfDoesNotExist(zona);

        Secao secao = new Secao(
            boletimUrnaQRCode.getConteudo().getSecao().getNumeroTSE(),
            this.zonaService.findById(new ZonaIdDTO(
                zona.getNumeroTSE(),
                uf.getSigla()
            ))
        );

        this.secaoService.saveIfDoesNotExist(secao);

        LocalVotacao localVotacao = new LocalVotacao(
            boletimUrnaQRCode.getConteudo().getLocalVotacao().getNumeroTSE(),
            this.zonaService.findById(new ZonaIdDTO(
                zona.getNumeroTSE(),
                uf.getSigla()
            )),
            this.municipioService.findByCodigoTSE(municipio.getCodigoTSE())
        );

        this.localVotacaoService.saveIfDoesNotExist(localVotacao);

        ProcessoEleitoral processoEleitoral = new ProcessoEleitoral(
            boletimUrnaComplemento.getProcessoEleitoral().getCodigoTSE(),
            boletimUrnaComplemento.getProcessoEleitoral().getNome(),
            this.origemConfiguracaoProcessoEleitoralService.findByAbreviacao(boletimUrnaQRCode.getConteudo().getProcessoEleitoral().getOrigemConfiguracao().getAbreviacao())
        );

        this.processoEleitoralService.saveIfDoesNotExist(processoEleitoral);

        Pleito pleito = new Pleito(
            boletimUrnaComplemento.getProcessoEleitoral().getPleito().getCodigoTSE(),
            boletimUrnaComplemento.getProcessoEleitoral().getPleito().getNome(),
            boletimUrnaQRCode.getConteudo().getPleito().getTurno().getNumero(),
            StringUtil.toLocalDate(boletimUrnaComplemento.getProcessoEleitoral().getPleito().getData(), "dd/MM/yyyy"),
            this.processoEleitoralService.findByCodigoTSE(processoEleitoral.getCodigoTSE())
        );

        this.pleitoService.saveIfDoesNotExist(pleito);

        SecaoPleito secaoPleito = new SecaoPleito(
            this.secaoService.findById(new SecaoIdDTO(
                secao.getNumeroTSE(),
                zona.getNumeroTSE(),
                uf.getSigla()
            )),
            this.pleitoService.findByCodigoTSE(pleito.getCodigoTSE())
        );

        this.secaoPleitoService.saveIfDoesNotExist(secaoPleito);

        Map<Integer, Eleicao> eleicoes = new LinkedHashMap<>();

        boletimUrnaComplemento.getProcessoEleitoral().getEleicoes().values().forEach(eleicaoComplemento -> {
            br.ufsm.elc1071.startthecount.core.model.Eleicao eleicaoCore = boletimUrnaQRCode.getConteudo().getEleicoes().get(eleicaoComplemento.getCodigoTSE());

            Eleicao eleicao = new Eleicao(
                eleicaoComplemento.getCodigoTSE(),
                eleicaoComplemento.getNome(),
                pleito.getAno(),
                Objects.isNull(eleicaoCore) ? null : eleicaoCore.getQuantidadeVotosCargosMajoritarios(),
                Objects.isNull(eleicaoCore) ? null : eleicaoCore.getQuantidadeVotosCargosProporcionais(),
                this.pleitoService.findByCodigoTSE(pleito.getCodigoTSE())
            );

            eleicoes.put(eleicao.getCodigoTSE(), eleicao);

            this.eleicaoService.saveIfDoesNotExist(eleicao);
        });

        SecaoProcessoEleitoral secaoProcessoEleitoral = new SecaoProcessoEleitoral(
            this.secaoService.findById(new SecaoIdDTO(
                secao.getNumeroTSE(),
                zona.getNumeroTSE(),
                uf.getSigla()
            )),
            this.processoEleitoralService.findByCodigoTSE(processoEleitoral.getCodigoTSE()),
            this.localVotacaoService.findById(new LocalVotacaoIdDTO(
                localVotacao.getNumeroTSE(),
                zona.getNumeroTSE(),
                uf.getSigla()
            ))
        );

        this.secaoProcessoEleitoralService.saveIfDoesNotExist(secaoProcessoEleitoral);

        for (Eleicao eleicao : eleicoes.values()) {
            SecaoEleicao secaoEleicao = new SecaoEleicao(
                this.secaoService.findById(new SecaoIdDTO(
                    secao.getNumeroTSE(),
                    zona.getNumeroTSE(),
                    uf.getSigla()
                )),
                this.eleicaoService.findByCodigoTSE(eleicao.getCodigoTSE()),
                boletimUrnaQRCode.getConteudo().getSecao().getQuantidadeEleitoresAptos(),
                boletimUrnaQRCode.getConteudo().getSecao().getQuantidadeEleitoresComparecentes(),
                boletimUrnaQRCode.getConteudo().getSecao().getQuantidadeEleitoresFaltosos(),
                boletimUrnaQRCode.getConteudo().getSecao().getQuantidadeEleitoresHabilitadosPorAnoNascimento()
            );

            this.secaoEleicaoService.saveIfDoesNotExist(secaoEleicao);
        }

        for (br.ufsm.elc1071.startthecount.core.model.Cargo cargo : boletimUrnaQRCode.getConteudo().getCargos().values()) {
            TipoCargo tipoCargo = new TipoCargo(
                cargo.getTipo().getCodigoTSE(),
                cargo.getTipo().getNome()
            );

            this.tipoCargoService.saveIfDoesNotExist(tipoCargo);
        }

        for (br.ufsm.elc1071.startthecount.core.model.Cargo cargoCore : boletimUrnaQRCode.getConteudo().getCargos().values()) {
            CargoComplemento cargoComplemento = boletimUrnaComplemento.getProcessoEleitoral().getCargos().get(cargoCore.getCodigoTSE());

            Cargo cargo = new Cargo(
                cargoCore.getCodigoTSE(),
                cargoComplemento.getNomeNeutro(),
                cargoComplemento.getNomeAbreviado(),
                this.tipoCargoService.findByCodigoTSE(cargoCore.getTipo().getCodigoTSE())
            );

            this.cargoService.saveIfDoesNotExists(cargo);
        }

        MultiKeyMap<Integer, CargoEleicao> cargosEleicao = new MultiKeyMap<>();

        for (EleicaoComplemento eleicaoComplemento : boletimUrnaComplemento.getProcessoEleitoral().getEleicoes().values()) {
            for (CargoComplemento cargoComplemento : eleicaoComplemento.getCargos().values()) {
                CargoEleicao cargoEleicao = new CargoEleicao(
                    this.cargoService.findByCodigoTSE(cargoComplemento.getCodigoTSE()),
                    this.eleicaoService.findByCodigoTSE(eleicaoComplemento.getCodigoTSE())
                );

                cargosEleicao.put(
                    cargoEleicao.getCargo().getCodigoTSE(),
                    cargoEleicao.getEleicao().getCodigoTSE(),
                    cargoEleicao
                );

                this.cargoEleicaoService.saveIfDoesNotExist(cargoEleicao);
            }
        }

        for (br.ufsm.elc1071.startthecount.core.model.Partido partidoCore : boletimUrnaQRCode.getConteudo().getPartidos().values()) {
            PartidoComplemento partidoComplemento = boletimUrnaComplemento.getProcessoEleitoral().getPartidos().get(partidoCore.getNumeroTSE());

            Partido partido = new Partido(
                partidoComplemento.getNumeroTSE(),
                partidoComplemento.getNome(),
                partidoComplemento.getSigla()
            );

            this.partidoService.saveIfDoesNotExist(partido);
        }

        Map<Integer, Candidato> candidatos = new LinkedHashMap<>();

        for (br.ufsm.elc1071.startthecount.core.model.Candidatura candidatura : boletimUrnaQRCode.getConteudo().getCandidaturas().values()) {
            Candidato candidato = new Candidato(
                candidatura.getCandidato().getNumeroTSE(),
                boletimUrnaComplemento.getProcessoEleitoral().getCandidatosTitulares().get(candidatura.getCandidato().getNumeroTSE()).getCodigoTSE(),
                boletimUrnaComplemento.getProcessoEleitoral().getCandidatosTitulares().get(candidatura.getCandidato().getNumeroTSE()).getNome()
            );

            candidatos.put(candidato.getNumeroTSE(), candidato);

            this.candidatoService.saveIfDoesNotExist(candidato);
        }

        MultiKeyMap<Integer, Candidatura> candidaturas = new MultiKeyMap<>();

        for (br.ufsm.elc1071.startthecount.core.model.Candidatura candidaturaCore : boletimUrnaQRCode.getConteudo().getCandidaturas().values()) {
            Candidatura candidatura = new Candidatura(
                this.candidatoService.findByCodigoTSE(candidatos.get(candidaturaCore.getCandidato().getNumeroTSE()).getCodigoTSE()),
                this.cargoEleicaoService.findById(new CargoEleicaoIdDTO(
                    candidaturaCore.getCargo().getCodigoTSE(),
                    candidaturaCore.getEleicao().getCodigoTSE()
                )),
                this.partidoService.findByNumeroTSE(candidaturaCore.getPartido().getNumeroTSE())
            );

            candidaturas.put(
                candidatura.getCandidato().getNumeroTSE(),
                candidatura.getCargoEleicao().getCargo().getCodigoTSE(),
                candidatura.getCargoEleicao().getEleicao().getCodigoTSE(),
                candidatura
            );

            this.candidaturaService.saveIfDoesNotExist(candidatura);
        }

        Fase fase = new Fase(
            boletimUrnaQRCode.getConteudo().getFase().getCodigoTSE(),
            boletimUrnaQRCode.getConteudo().getFase().getNome()
        );

        this.faseService.saveIfDoesNotExist(fase);

        OrigemBoletimUrna origemBoletimUrna = new OrigemBoletimUrna(
            boletimUrnaQRCode.getConteudo().getOrigem().getNome(),
            boletimUrnaQRCode.getConteudo().getOrigem().getAbreviacao()
        );

        this.origemBoletimUrnaService.saveIfDoesNotExist(origemBoletimUrna);

        EmissaoBoletimUrna emissaoBoletimUrna = boletimUrnaQRCode.getConteudo().getEmissaoBoletimUrna();

        Usuario usuario = this.usuarioService.findByUsername(username);

        BoletimUrna boletimUrna = new BoletimUrna(
            usuario,
            this.secaoPleitoService.findById(new SecaoPleitoIdDTO(
                secao.getNumeroTSE(),
                zona.getNumeroTSE(),
                uf.getSigla(),
                pleito.getCodigoTSE()
            )),
            this.faseService.findByNome(fase.getNome()),
            this.origemBoletimUrnaService.findByAbreviacao(origemBoletimUrna.getAbreviacao()),
            this.urnaEletronicaService.findByNumeroSerieTSE(urnaEletronica.getNumeroSerieTSE()),
            boletimUrnaQRCode.getAssinatura(),
            boletimUrnaQRCode.getQuantidadeTotalQRCodes(),
            Objects.isNull(emissaoBoletimUrna) ? null : emissaoBoletimUrna.getData(),
            Objects.isNull(emissaoBoletimUrna) ? null : emissaoBoletimUrna.getHorario()
        );

        this.saveIfDoesNotExist(boletimUrna);

        for (br.ufsm.elc1071.startthecount.core.model.QRCodeBoletimUrna qrCodeBoletimUrna : boletimUrnaQRCode.getQrCodes()) {
            QRCodeBoletimUrna qrCode = new QRCodeBoletimUrna(
                this.findById(new BoletimUrnaIdDTO(
                    usuario.getUsername(),
                    pleito.getCodigoTSE(),
                    secao.getNumeroTSE(),
                    zona.getNumeroTSE(),
                    uf.getSigla()
                )),
                qrCodeBoletimUrna.getPayloadCabecalho(),
                qrCodeBoletimUrna.getPayloadConteudo(),
                qrCodeBoletimUrna.getHash(),
                qrCodeBoletimUrna.getCabecalho().getMarcaInicioDados().getIndiceQRCode(),
                qrCodeBoletimUrna.getCabecalho().getVersaoFormatoRepresentacao().getNumeroCiclosEleitoraisDesdeImplementacao(),
                qrCodeBoletimUrna.getCabecalho().getVersaoFormatoRepresentacao().getNumeroRevisoesFormatoCiclo(),
                qrCodeBoletimUrna.getCabecalho().getVersaoChaveAssinatura()
            );

            this.qrCodeBoletimUrnaService.saveIfDoesNotExist(qrCode);
        }

        for (Candidatura candidatura : candidaturas.values()) {
            ApuracaoVotosCandidaturaBoletimUrna apuracaoVotosCandidaturaBoletimUrna = new ApuracaoVotosCandidaturaBoletimUrna(
                this.candidaturaService.findById(new CandidaturaIdDTO(
                    candidatura.getCandidato().getNumeroTSE(),
                    candidatura.getCargoEleicao().getCargo().getCodigoTSE(),
                    candidatura.getCargoEleicao().getEleicao().getCodigoTSE()
                )),
                this.findById(new BoletimUrnaIdDTO(
                    usuario.getUsername(),
                    pleito.getCodigoTSE(),
                    secao.getNumeroTSE(),
                    zona.getNumeroTSE(),
                    uf.getSigla()
                )),
                boletimUrnaQRCode.getConteudo().getCandidaturas().get(candidatura.getCandidato().getNumeroTSE(), candidatura.getCargoEleicao().getCargo().getCodigoTSE()).getTotalVotosApurados()
            );

            this.apuracaoVotosCandidaturaBoletimUrnaService.saveIfDoesNotExist(apuracaoVotosCandidaturaBoletimUrna);
        }

        for (CargoEleicao cargoEleicao : cargosEleicao.values()) {
            br.ufsm.elc1071.startthecount.core.model.Cargo cargo = boletimUrnaQRCode.getConteudo().getCargos().get(cargoEleicao.getCargo().getCodigoTSE());

            ApuracaoVotosCargoBoletimUrna apuracaoVotosCargoBoletimUrna = new ApuracaoVotosCargoBoletimUrna(
                this.cargoEleicaoService.findById(new CargoEleicaoIdDTO(
                    cargoEleicao.getCargo().getCodigoTSE(),
                    cargoEleicao.getEleicao().getCodigoTSE()
                )),
                this.findById(new BoletimUrnaIdDTO(
                    usuario.getUsername(),
                    pleito.getCodigoTSE(),
                    secao.getNumeroTSE(),
                    zona.getNumeroTSE(),
                    uf.getSigla()
                )),
                cargo.getQuantidadeEleitoresAptos(),
                cargo.getQuantidadeComparecimentoCargoSemCandidatos(),
                cargo.getQuantidadeVotosNominais(),
                cargo.getQuantidadeVotosDeLegenda(),
                cargo.getQuantidadeVotosEmBranco(),
                cargo.getQuantidadeVotosNulos(),
                cargo.getTotalVotosApurados()
            );

            this.apuracaoVotosCargoBoletimUrnaService.saveIfDoesNotExist(apuracaoVotosCargoBoletimUrna);
        }

        for (br.ufsm.elc1071.startthecount.core.model.Candidatura candidatura : boletimUrnaQRCode.getConteudo().getCandidaturas().values()) {
            ApuracaoVotosPartidoBoletimUrna apuracaoVotosPartidoBoletimUrna = new ApuracaoVotosPartidoBoletimUrna(
                this.partidoService.findByNumeroTSE(candidatura.getPartido().getNumeroTSE()),
                this.findById(new BoletimUrnaIdDTO(
                    usuario.getUsername(),
                    pleito.getCodigoTSE(),
                    secao.getNumeroTSE(),
                    zona.getNumeroTSE(),
                    uf.getSigla()
                )),
                this.eleicaoService.findByCodigoTSE(candidatura.getEleicao().getCodigoTSE()),
                candidatura.getPartido().getQuantidadeVotosDeLegenda(),
                candidatura.getPartido().getTotalVotosApurados()
            );

            this.apuracaoVotosPartidoBoletimUrnaService.saveIfDoesNotExist(apuracaoVotosPartidoBoletimUrna);
        }

        return boletimUrna;
    }
}
