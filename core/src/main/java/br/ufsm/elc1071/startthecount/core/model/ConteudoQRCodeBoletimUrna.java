package br.ufsm.elc1071.startthecount.core.model;

import br.ufsm.elc1071.startthecount.util.StringUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.map.MultiKeyMap;

import java.util.*;

@Getter
@Setter
@ToString
public class ConteudoQRCodeBoletimUrna {

    private OrigemBoletimUrna origem;

    private ProcessoEleitoral processoEleitoral;

    private Pleito pleito;

    private Fase fase;

    private UF uf;

    private Municipio municipio;

    private Zona zona;

    private Secao secao;

    private LocalVotacao localVotacao;

    private UrnaEletronica urnaEletronica;

    private ResponsavelApuracaoVotos responsavelApuracaoVotos;

    private EmissaoBoletimUrna emissaoBoletimUrna;

    private Map<Integer, Eleicao> eleicoes;

    private Map<Integer, Cargo> cargos;

    private Map<Integer, Partido> partidos;

    private List<Partido> historicoPartidos;

    private Map<Integer, Candidato> candidatos;

    private MultiKeyMap<Integer, Candidatura> candidaturas;

    @Getter(value = AccessLevel.NONE)
    private static final Integer CODIGO_TSE_CARGO_PRESIDENTE = 1;

    public void setOrigem(String string) {
        this.origem = OrigemBoletimUrna.fromAbreviacao(string).orElse(null);
    }

    public void setOrigemConfiguracaoProcessoEleitoral(String abreviacaoOrigemConfiguracaoProcessoEleitoral) {
        if (Objects.isNull(this.processoEleitoral)) {
            this.processoEleitoral = new ProcessoEleitoral();
        }

        this.processoEleitoral.setOrigemConfiguracao(OrigemConfiguracaoProcessoEleitoral.fromAbreviacao(abreviacaoOrigemConfiguracaoProcessoEleitoral).orElse(null));
    }

    public void setCodigoProcessoEleitoralTSE(String codigoProcessoEleitoralTSEString) {
        if (Objects.isNull(this.processoEleitoral)) {
            this.processoEleitoral = new ProcessoEleitoral();
        }

        this.processoEleitoral.setCodigoTSE(StringUtil.toInteger(codigoProcessoEleitoralTSEString));
    }

    public void setDataPleito(String dataString) {
        if (Objects.isNull(this.pleito)) {
            this.pleito = new Pleito();
        }

        this.pleito.setData(StringUtil.toLocalDate(dataString, "yyyyMMdd"));
    }

    public void setCodigoPleitoTSE(String codigoPleitoTSEString) {
        if (Objects.isNull(this.pleito)) {
            this.pleito = new Pleito();
        }

        this.pleito.setCodigoTSE(StringUtil.toInteger(codigoPleitoTSEString));
    }

    public void setTurnoPleito(String turnoString) {
        if (Objects.isNull(this.pleito)) {
            this.pleito = new Pleito();
        }

        this.pleito.setTurno(Turno.fromNumero(StringUtil.toInteger(turnoString)).orElse(null));
    }

    public void setProcessoEleitoralPleito() {
        if (Objects.isNull(this.pleito)) {
            this.pleito = new Pleito();
        }

        this.pleito.setProcessoEleitoral(this.processoEleitoral);
    }

    public void setFase(String abreviacaoFase) {
        this.fase = Fase.fromAbreviacao(abreviacaoFase).orElse(null);
    }

    public void setUF(String siglaUF) {
        this.uf = UF.fromSigla(siglaUF).orElse(null);
    }

    public void setCodigoMunicipioTSE(String codigoMunicipioTSEString) {
        if (Objects.isNull(this.municipio)) {
            this.municipio = new Municipio();
        }

        this.municipio.setCodigoTSE(StringUtil.toInteger(codigoMunicipioTSEString));
    }

    public void setUFMunicipio() {
        if (Objects.isNull(this.municipio)) {
            this.municipio = new Municipio();
        }

        this.municipio.setUf(this.uf);
    }

    public void setNumeroZonaTSE(String numeroZonaTSEString) {
        if (Objects.isNull(this.zona)) {
            this.zona = new Zona();
        }

        this.zona.setNumeroTSE(StringUtil.toInteger(numeroZonaTSEString));
    }

    public void setUFZona() {
        if (Objects.isNull(this.zona)) {
            this.zona = new Zona();
        }

        this.zona.setUf(this.uf);
    }

    public void setNumeroSecaoTSE(String numeroSecaoTSEString) {
        if (Objects.isNull(this.secao)) {
            this.secao = new Secao();
        }

        this.secao.setNumeroTSE(StringUtil.toInteger(numeroSecaoTSEString));
    }

    public void setZonaSecao() {
        if (Objects.isNull(this.secao)) {
            this.secao = new Secao();
        }

        this.secao.setZona(this.zona);
    }

    public void setMunicipioSecao() {
        if (Objects.isNull(this.secao)) {
            this.secao = new Secao();
        }

        this.secao.setMunicipio(this.municipio);
    }

    public void setSecoesAgregadasSecao(String secoesAgregadas) {
        if (Objects.isNull(this.secao)) {
            this.secao = new Secao();
        }

        for (String secaoAgregada : secoesAgregadas.split("\\.")) {
            this.secao.addSecaoAgregada(new Secao(StringUtil.toInteger(secaoAgregada), this.zona, this.municipio));
        }
    }

    public void setLocalVotacaoSecao() {
        if (Objects.isNull(this.secao)) {
            this.secao = new Secao();
        }

        this.secao.setLocalVotacao(this.localVotacao);
    }

    public void setQuantidadeEleitoresAptosSecao(String quantidadeEleitoresAptosString) {
        if (Objects.isNull(this.secao)) {
            this.secao = new Secao();
        }

        this.secao.setQuantidadeEleitoresAptos(StringUtil.toInteger(quantidadeEleitoresAptosString));
    }

    public void setQuantidadeEleitoresComparecentesSecao(String quantidadeEleitoresComparecentesString) {
        if (Objects.isNull(this.secao)) {
            this.secao = new Secao();
        }

        this.secao.setQuantidadeEleitoresComparecentes(StringUtil.toInteger(quantidadeEleitoresComparecentesString));
    }

    public void setQuantidadeEleitoresFaltososSecao(String quantidadeEleitoresFaltososString) {
        if (Objects.isNull(this.secao)) {
            this.secao = new Secao();
        }

        this.secao.setQuantidadeEleitoresFaltosos(StringUtil.toInteger(quantidadeEleitoresFaltososString));
    }

    public void setQuantidadeEleitoresHabilitadosPorAnoNascimentoSecao(String quantidadeEleitoresHabilitadosPorAnoNascimentoString) {
        if (Objects.isNull(this.secao)) {
            this.secao = new Secao();
        }

        this.secao.setQuantidadeEleitoresHabilitadosPorAnoNascimento(StringUtil.toInteger(quantidadeEleitoresHabilitadosPorAnoNascimentoString));
    }

    public void setNumeroSerieUrnaEletronicaTSE(String numeroSerieUrnaEletronicaTSEString) {
        if (Objects.isNull(this.urnaEletronica)) {
            this.urnaEletronica = new UrnaEletronica();
        }

        this.urnaEletronica.setNumeroSerie(StringUtil.toInteger(numeroSerieUrnaEletronicaTSEString));
    }

    public void setCodigoIdentificacaoCargaUrnaEletronica(String codigoIdentificacaoCarga) {
        if (Objects.isNull(this.urnaEletronica)) {
            this.urnaEletronica = new UrnaEletronica();
        }

        this.urnaEletronica.setCodigoIdentificacaoCarga(codigoIdentificacaoCarga);
    }

    public void setVersaoSoftwareUrnaEletronica(String versaoSoftware) {
        if (Objects.isNull(this.urnaEletronica)) {
            this.urnaEletronica = new UrnaEletronica();
        }

        this.urnaEletronica.setVersaoSoftware(versaoSoftware);
    }

    public void setDataAberturaUrnaEletronica(String dataAberturaString) {
        if (Objects.isNull(this.urnaEletronica)) {
            this.urnaEletronica = new UrnaEletronica();
        }

        this.urnaEletronica.setDataAbertura(StringUtil.toLocalDate(dataAberturaString, "yyyyMMdd"));
    }

    public void setHorarioAberturaUrnaEletronica(String horarioAberturaString) {
        if (Objects.isNull(this.urnaEletronica)) {
            this.urnaEletronica = new UrnaEletronica();
        }

        this.urnaEletronica.setHorarioAbertura(StringUtil.toLocalTime(horarioAberturaString, "HHmmss"));
    }

    public void setDataFechamentoUrnaEletronica(String dataFechamentoString) {
        if (Objects.isNull(this.urnaEletronica)) {
            this.urnaEletronica = new UrnaEletronica();
        }

        this.urnaEletronica.setDataFechamento(StringUtil.toLocalDate(dataFechamentoString, "yyyyMMdd"));
    }

    public void setHorarioFechamentoUrnaEletronica(String horarioFechamentoString) {
        if (Objects.isNull(this.urnaEletronica)) {
            this.urnaEletronica = new UrnaEletronica();
        }

        this.urnaEletronica.setHorarioFechamento(StringUtil.toLocalTime(horarioFechamentoString, "HHmmss"));
    }

    public void setNumeroLocalVotacaoTSE(String numeroLocalVotacaoTSEString) {
        if (Objects.isNull(this.localVotacao)) {
            this.localVotacao = new LocalVotacao();
        }

        this.localVotacao.setNumeroTSE(StringUtil.toInteger(numeroLocalVotacaoTSEString));
    }

    public void setZonaLocalVotacao() {
        if (Objects.isNull(this.localVotacao)) {
            this.localVotacao = new LocalVotacao();
        }

        this.localVotacao.setZona(this.zona);
    }

    public void setMunicipioLocalVotacao() {
        if (Objects.isNull(this.localVotacao)) {
            this.localVotacao = new LocalVotacao();
        }

        this.localVotacao.setMunicipio(this.municipio);
    }

    public void setNumeroJuntaApuradora(String numeroJuntaApuradoraString) {
        if (Objects.isNull(this.responsavelApuracaoVotos)) {
            this.responsavelApuracaoVotos = new ResponsavelApuracaoVotos();
        }

        this.responsavelApuracaoVotos.setNumeroJunta(StringUtil.toInteger(numeroJuntaApuradoraString));
    }

    public void setNumeroTurmaApuradora(String numeroTurmaApuradoraString) {
        if (Objects.isNull(this.responsavelApuracaoVotos)) {
            this.responsavelApuracaoVotos = new ResponsavelApuracaoVotos();
        }

        this.responsavelApuracaoVotos.setNumeroTurma(StringUtil.toInteger(numeroTurmaApuradoraString));
    }

    public void setDataEmissaoBoletimUrna(String dataEmissaoString) {
        if (Objects.isNull(this.emissaoBoletimUrna)) {
            this.emissaoBoletimUrna = new EmissaoBoletimUrna();
        }

        this.emissaoBoletimUrna.setData(StringUtil.toLocalDate(dataEmissaoString, "yyyyMMdd"));
    }

    public void setHorarioEmissaoBoletimUrna(String horarioEmissaoString) {
        if (Objects.isNull(this.emissaoBoletimUrna)) {
            this.emissaoBoletimUrna = new EmissaoBoletimUrna();
        }

        this.emissaoBoletimUrna.setHorario(StringUtil.toLocalTime(horarioEmissaoString, "HHmmss"));
    }

    public void addEleicao(String codigoEleicaoTSEString) {
        if (Objects.isNull(this.eleicoes)) {
            this.eleicoes = new LinkedHashMap<>();
        }

        Integer codigoEleicaoTSE = StringUtil.toInteger(codigoEleicaoTSEString);

        this.eleicoes.put(codigoEleicaoTSE, new Eleicao(codigoEleicaoTSE, this.pleito, this.pleito.getAno()));
    }

    private Optional<Eleicao> getUltimaEleicao() {
        if (Objects.isNull(this.eleicoes) || this.eleicoes.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(this.eleicoes.get((Integer) this.eleicoes.keySet().toArray()[this.eleicoes.size() - 1]));
    }

    public void setQuantidadeVotosCargosMajoritariosEleicao(String quantidadeVotosCargosMajoritariosString) {
        this.getUltimaEleicao().ifPresent(eleicao -> eleicao.setQuantidadeVotosCargosMajoritarios(StringUtil.toInteger(quantidadeVotosCargosMajoritariosString)));
    }

    public void setQuantidadeVotosCargosProporcionaisEleicao(String quantidadeVotosCargosProporcionaisString) {
        this.getUltimaEleicao().ifPresent(eleicao -> eleicao.setQuantidadeVotosCargosProporcionais(StringUtil.toInteger(quantidadeVotosCargosProporcionaisString)));
    }

    public void addCargo(String codigoCargoTSEString) {
        if (Objects.isNull(this.cargos)) {
            this.cargos = new LinkedHashMap<>();
        }

        Integer codigoCargoTSE = StringUtil.toInteger(codigoCargoTSEString);

        this.cargos.put(codigoCargoTSE, new Cargo(codigoCargoTSE));
    }

    private Optional<Cargo> getUltimoCargo() {
        if (Objects.isNull(this.cargos) || this.cargos.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(this.cargos.get((Integer) this.cargos.keySet().toArray()[this.cargos.size() - 1]));
    }

    public void setTipoCargo(String codigoTipoCargoTSEString) {
        this.getUltimoCargo().ifPresent(cargo -> cargo.setTipo(new TipoCargo(StringUtil.toInteger(codigoTipoCargoTSEString))));
    }

    public void setVersaoPacoteDadosCargo(String versaoPacoteDadosCargoString) {
        this.getUltimoCargo().ifPresent(cargo -> cargo.setVersaoPacoteDados(versaoPacoteDadosCargoString));
    }

    public void setQuantidadeEleitoresAptosCargo(String quantidadeEleitoresAptosString) {
        this.getUltimoCargo().ifPresent(cargo -> cargo.setQuantidadeEleitoresAptos(StringUtil.toInteger(quantidadeEleitoresAptosString)));
    }

    public void setQuantidadeComparecimentoSemCandidatosCargo(String quantidadeComparecimentoSemCandidatosString) {
        this.getUltimoCargo().ifPresent(cargo -> cargo.setQuantidadeComparecimentoCargoSemCandidatos(StringUtil.toInteger(quantidadeComparecimentoSemCandidatosString)));
    }

    public void setQuantidadeVotosNominaisCargo(String quantidadeVotosNominaisString) {
        this.getUltimoCargo().ifPresent(cargo -> cargo.setQuantidadeVotosNominais(StringUtil.toInteger(quantidadeVotosNominaisString)));
    }

    public void setQuantidadeVotosDeLegendaCargo(String quantidadeVotosDeLegendaString) {
        this.getUltimoCargo().ifPresent(cargo -> cargo.setQuantidadeVotosDeLegenda(StringUtil.toInteger(quantidadeVotosDeLegendaString)));
    }

    public void setQuantidadeVotosEmBrancoCargo(String quantidadeVotosEmBrancoString) {
        this.getUltimoCargo().ifPresent(cargo -> cargo.setQuantidadeVotosEmBranco(StringUtil.toInteger(quantidadeVotosEmBrancoString)));
    }

    public void setQuantidadeVotosNulosCargo(String quantidadeVotosNulosString) {
        this.getUltimoCargo().ifPresent(cargo -> cargo.setQuantidadeVotosNulos(StringUtil.toInteger(quantidadeVotosNulosString)));
    }

    public void setQuantidadeVotosCargo(String quantidadeVotosString) {
        this.getUltimoCargo().ifPresent(cargo -> cargo.setTotalVotosApurados(StringUtil.toInteger(quantidadeVotosString)));
    }

    public void addPartido(Partido partido) {
        if (Objects.isNull(this.partidos)) {
            this.partidos = new LinkedHashMap<>();
            this.historicoPartidos = new LinkedList<>();
        }

        if (!this.partidos.containsKey(partido.getNumeroTSE())) {
            this.partidos.put(partido.getNumeroTSE(), partido);
        }

        this.historicoPartidos.add(this.partidos.get(partido.getNumeroTSE()));
    }

    public void addPartido(String numeroPartidoTSEString) {
        this.addPartido(new Partido(StringUtil.toInteger(numeroPartidoTSEString)));
    }

    private Optional<Partido> getUltimoPartido() {
        if (Objects.isNull(this.historicoPartidos) || this.historicoPartidos.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(this.historicoPartidos.get(this.historicoPartidos.size() - 1));
    }

    public void setQuantidadeVotosDeLegendaPartido(String quantidadeVotosDeLegendaString) {
        this.getUltimoPartido().ifPresent(partido -> partido.setQuantidadeVotosDeLegenda(partido.getQuantidadeVotosDeLegenda() + StringUtil.toInteger(quantidadeVotosDeLegendaString)));
    }

    public void setTotalVotosApuradosPartido(String totalVotosApuradosPartidoString) {
        this.getUltimoPartido().ifPresent(partido -> partido.setTotalVotosApurados(partido.getTotalVotosApurados() + StringUtil.toInteger(totalVotosApuradosPartidoString)));
    }

    public void addCandidato(Candidato candidato) {
        if (Objects.isNull(this.candidatos)) {
            this.candidatos = new LinkedHashMap<>();
        }

        this.candidatos.put(candidato.getNumeroTSE(), candidato);
    }

    public void addCandidatura(String numeroCandidatoTSEString, String totalVotosApuradosString) {
        Integer numeroCandidatoTSE = StringUtil.toInteger(numeroCandidatoTSEString);

        Candidato candidato = new Candidato(numeroCandidatoTSE);

        this.addCandidato(candidato);

        if (Objects.isNull(this.candidaturas)) {
            this.candidaturas = new MultiKeyMap<>();
        }

        if (this.getUltimoCargo().isEmpty()) return;

        Cargo cargo = this.getUltimoCargo().get();

        Integer totalVotosApurados = StringUtil.toInteger(totalVotosApuradosString);

        Partido partido;

        if (Objects.equals(cargo.getCodigoTSE(), CODIGO_TSE_CARGO_PRESIDENTE)) {
            partido = new Partido(numeroCandidatoTSE, totalVotosApurados);
        } else {
            partido = this.getUltimoPartido().orElse(null);
        }

        this.candidaturas.put(
            numeroCandidatoTSE,
            cargo.getCodigoTSE(),
            new Candidatura(
                candidato,
                cargo,
                this.getUltimaEleicao().orElse(null),
                partido,
                totalVotosApurados
            )
        );
    }
}
