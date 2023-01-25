package br.ufsm.elc1071.startthecount.core.service;

import br.ufsm.elc1071.startthecount.core.exception.EstruturaIncorretaPayloadQRCodeBoletimUrnaException;
import br.ufsm.elc1071.startthecount.core.model.BoletimUrnaQRCode;
import br.ufsm.elc1071.startthecount.core.model.CabecalhoQRCodeBoletimUrna;
import br.ufsm.elc1071.startthecount.core.model.ConteudoQRCodeBoletimUrna;
import br.ufsm.elc1071.startthecount.core.model.QRCodeBoletimUrna;

import lombok.NoArgsConstructor;

import java.util.*;
import java.util.regex.Pattern;

@NoArgsConstructor
public class BoletimUrnaQRCodeBuilder {

    private static final Set<String> KEYS = Set.of(
        "^QRBU$",   "^VRQR$", "^VRCH$", "^ORIG$", "^ORLC$",
        "^PROC$",   "^DTPL$", "^PLEI$", "^TURN$", "^FASE$",
        "^UNFE$",   "^MUNI$", "^ZONA$", "^SECA$", "^AGRE$",
        "^IDUE$",   "^IDCA$", "^VERS$", "^LOCA$", "^APTO$",
        "^COMP$",   "^FALT$", "^HBMA$", "^DTAB$", "^HRAB$",
        "^DTFC$",   "^HRFC$", "^JUNT$", "^TURM$", "^DTEM$",
        "^HREM$",   "^IDEL$", "^MAJO$", "^PROP$", "^CARG$",
        "^TIPO$",   "^VERC$", "^PART$", "^LEGP$", "^TOTP$",
        "^[0-9]*$", "^APTA$", "^CSEC$", "^NOMI$", "^LEGC$",
        "^BRAN$",   "^NULO$", "^TOTC$", "^HASH$", "^ASSI$"
    );

    private static Set<String> extractKeysFromRecords(String[] records) {
        Set<String> keys = new HashSet<>();

        for (String record : records) {
            keys.add(record.split(":", 2)[0]);
        }

        return keys;
    }

    private static boolean keyMatchesPattern(String key, String pattern) {
        return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(key).matches();
    }

    private static void checkIfKeyIsValid(String key) {
        boolean keyExists = false;

        Iterator<String> iterator = KEYS.iterator();

        while (iterator.hasNext() && !keyExists) {
            if (keyMatchesPattern(key, iterator.next())) {
                keyExists = true;
            }
        }

        if (!keyExists) {
            String message = String.format("A estrutura do payload de um QR code de boletim de urna não contém nenhuma chave \"%s\".", key);
            throw new EstruturaIncorretaPayloadQRCodeBoletimUrnaException(message);
        }
    }

    private static void checkIfKeysAreValid(String[] records) {
        Set<String> keys = extractKeysFromRecords(records);

        for (String key : keys) {
            checkIfKeyIsValid(key);
        }
    }

    public static BoletimUrnaQRCode build(List<String> qrCodePayloads) {
        BoletimUrnaQRCode boletimUrnaQRCode = new BoletimUrnaQRCode();
        ConteudoQRCodeBoletimUrna conteudoQRCodeBoletimUrna = new ConteudoQRCodeBoletimUrna();
        List<QRCodeBoletimUrna> qrCodesBoletimUrna = new LinkedList<>();

        for (String qrCodePayload : qrCodePayloads) {
            QRCodeBoletimUrna qrCodeBoletimUrna = new QRCodeBoletimUrna();
            CabecalhoQRCodeBoletimUrna cabecalhoQRCodeBoletimUrna = new CabecalhoQRCodeBoletimUrna();

            String[] records = qrCodePayload.split("\\s+");

            checkIfKeysAreValid(records);

            for (String record: records) {
                String[] keyValue = record.split(":", 2);
                String key = keyValue[0];
                String value = keyValue[1];

                if (keyMatchesPattern(key, "^QRBU$")) {
                    cabecalhoQRCodeBoletimUrna.setMarcaInicioDados(value);
                } else if (keyMatchesPattern(key, "^VRQR$")) {
                    cabecalhoQRCodeBoletimUrna.setVersaoFormatoRepresentacao(value);
                } else if (keyMatchesPattern(key, "^VRCH$")) {
                    cabecalhoQRCodeBoletimUrna.setVersaoChaveAssinatura(value);
                } else if (keyMatchesPattern(key, "^ORIG$")) {
                    conteudoQRCodeBoletimUrna.setOrigem(value);
                } else if (keyMatchesPattern(key, "^ORLC$")) {
                    conteudoQRCodeBoletimUrna.setOrigemConfiguracaoProcessoEleitoral(value);
                } else if (keyMatchesPattern(key, "^PROC$")) {
                    conteudoQRCodeBoletimUrna.setCodigoProcessoEleitoralTSE(value);
                } else if (keyMatchesPattern(key, "^DTPL$")) {
                    conteudoQRCodeBoletimUrna.setDataPleito(value);
                } else if (keyMatchesPattern(key, "^PLEI$")) {
                    conteudoQRCodeBoletimUrna.setCodigoPleitoTSE(value);
                } else if (keyMatchesPattern(key, "^TURN$")) {
                    conteudoQRCodeBoletimUrna.setTurnoPleito(value);
                    conteudoQRCodeBoletimUrna.setProcessoEleitoralPleito();
                } else if (keyMatchesPattern(key, "^FASE$")) {
                    conteudoQRCodeBoletimUrna.setFase(value);
                } else if (keyMatchesPattern(key, "^UNFE$")) {
                    conteudoQRCodeBoletimUrna.setUF(value);
                } else if (keyMatchesPattern(key, "^MUNI$")) {
                    conteudoQRCodeBoletimUrna.setCodigoMunicipioTSE(value);
                    conteudoQRCodeBoletimUrna.setUFMunicipio();
                    conteudoQRCodeBoletimUrna.setMunicipioLocalVotacao();
                } else if (keyMatchesPattern(key, "^ZONA$")) {
                    conteudoQRCodeBoletimUrna.setNumeroZonaTSE(value);
                    conteudoQRCodeBoletimUrna.setUFZona();
                    conteudoQRCodeBoletimUrna.setZonaLocalVotacao();
                } else if (keyMatchesPattern(key, "^SECA$")) {
                    conteudoQRCodeBoletimUrna.setNumeroSecaoTSE(value);
                    conteudoQRCodeBoletimUrna.setZonaSecao();
                    conteudoQRCodeBoletimUrna.setMunicipioSecao();
                } else if (keyMatchesPattern(key, "^AGRE$")) {
                    conteudoQRCodeBoletimUrna.setSecoesAgregadasSecao(value);
                } else if (keyMatchesPattern(key, "^IDUE$")) {
                    conteudoQRCodeBoletimUrna.setNumeroSerieUrnaEletronicaTSE(value);
                } else if (keyMatchesPattern(key, "^IDCA$")) {
                    conteudoQRCodeBoletimUrna.setCodigoIdentificacaoCargaUrnaEletronica(value);
                } else if (keyMatchesPattern(key, "^VERS$")) {
                    conteudoQRCodeBoletimUrna.setVersaoSoftwareUrnaEletronica(value);
                } else if (keyMatchesPattern(key, "^LOCA$")) {
                    conteudoQRCodeBoletimUrna.setNumeroLocalVotacaoTSE(value);
                    conteudoQRCodeBoletimUrna.setLocalVotacaoSecao();
                } else if (keyMatchesPattern(key, "^APTO$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeEleitoresAptosSecao(value);
                } else if (keyMatchesPattern(key, "^COMP$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeEleitoresComparecentesSecao(value);
                } else if (keyMatchesPattern(key, "^FALT$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeEleitoresFaltososSecao(value);
                } else if (keyMatchesPattern(key, "^HBMA$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeEleitoresHabilitadosPorAnoNascimentoSecao(value);
                } else if (keyMatchesPattern(key, "^DTAB$")) {
                    conteudoQRCodeBoletimUrna.setDataAberturaUrnaEletronica(value);
                } else if (keyMatchesPattern(key, "^HRAB$")) {
                    conteudoQRCodeBoletimUrna.setHorarioAberturaUrnaEletronica(value);
                } else if (keyMatchesPattern(key, "^DTFC$")) {
                    conteudoQRCodeBoletimUrna.setDataFechamentoUrnaEletronica(value);
                } else if (keyMatchesPattern(key, "^HRFC$")) {
                    conteudoQRCodeBoletimUrna.setHorarioFechamentoUrnaEletronica(value);
                } else if (keyMatchesPattern(key, "^JUNT$")) {
                    conteudoQRCodeBoletimUrna.setNumeroJuntaApuradora(value);
                } else if (keyMatchesPattern(key, "^TURM$")) {
                    conteudoQRCodeBoletimUrna.setNumeroTurmaApuradora(value);
                } else if (keyMatchesPattern(key, "^DTEM$")) {
                    conteudoQRCodeBoletimUrna.setDataEmissaoBoletimUrna(value);
                } else if (keyMatchesPattern(key, "^HREM$")) {
                    conteudoQRCodeBoletimUrna.setHorarioEmissaoBoletimUrna(value);
                } else if (keyMatchesPattern(key, "^IDEL$")) {
                    conteudoQRCodeBoletimUrna.addEleicao(value);
                } else if (keyMatchesPattern(key, "^MAJO$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeVotosCargosMajoritariosEleicao(value);
                } else if (keyMatchesPattern(key, "^PROP$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeVotosCargosProporcionaisEleicao(value);
                } else if (keyMatchesPattern(key, "^CARG$")) {
                    conteudoQRCodeBoletimUrna.addCargo(value);
                } else if (keyMatchesPattern(key, "^TIPO$")) {
                    conteudoQRCodeBoletimUrna.setTipoCargo(value);
                } else if (keyMatchesPattern(key, "^VERC$")) {
                    conteudoQRCodeBoletimUrna.setVersaoPacoteDadosCargo(value);
                } else if (keyMatchesPattern(key, "^PART$")) {
                    conteudoQRCodeBoletimUrna.addPartido(value);
                } else if (keyMatchesPattern(key, "^LEGP$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeVotosDeLegendaPartido(value);
                } else if (keyMatchesPattern(key, "^TOTP$")) {
                    conteudoQRCodeBoletimUrna.setTotalVotosApuradosPartido(value);
                } else if (keyMatchesPattern(key, "^[0-9]*$")) {
                    conteudoQRCodeBoletimUrna.addCandidatura(key, value);
                } else if (keyMatchesPattern(key, "^APTA$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeEleitoresAptosCargo(value);
                } else if (keyMatchesPattern(key, "^CSEC$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeComparecimentoSemCandidatosCargo(value);
                } else if (keyMatchesPattern(key, "^NOMI$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeVotosNominaisCargo(value);
                } else if (keyMatchesPattern(key, "^LEGC$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeVotosDeLegendaCargo(value);
                } else if (keyMatchesPattern(key, "^BRAN$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeVotosEmBrancoCargo(value);
                } else if (keyMatchesPattern(key, "^NULO$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeVotosNulosCargo(value);
                } else if (keyMatchesPattern(key, "^TOTC$")) {
                    conteudoQRCodeBoletimUrna.setQuantidadeVotosCargo(value);
                } else if (keyMatchesPattern(key, "^HASH$")) {
                    qrCodeBoletimUrna.setHash(value);
                } else if (keyMatchesPattern(key, "^ASSI$")) {
                    boletimUrnaQRCode.setAssinatura(value);
                }

                qrCodesBoletimUrna.add(qrCodeBoletimUrna);
            }

            qrCodeBoletimUrna.setPayload(qrCodePayload);
            qrCodeBoletimUrna.setCabecalho(cabecalhoQRCodeBoletimUrna);
            boletimUrnaQRCode.setQrCodes(qrCodesBoletimUrna);
        }

        boletimUrnaQRCode.setConteudo(conteudoQRCodeBoletimUrna);

        return boletimUrnaQRCode;
    }
}
