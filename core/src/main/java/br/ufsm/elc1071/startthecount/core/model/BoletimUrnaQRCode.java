package br.ufsm.elc1071.startthecount.core.model;

import br.ufsm.elc1071.startthecount.core.exception.*;
import br.ufsm.elc1071.startthecount.core.model.complemento.BoletimUrnaComplemento;
import br.ufsm.elc1071.startthecount.util.CryptoUtil;
import br.ufsm.elc1071.startthecount.util.WebRequestUtil;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import lombok.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoletimUrnaQRCode {

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final String URL_BASE_VERIFICACAO_ASSINATURA = "https://qrcodenobu.tse.jus.br/tse.qrcodebu";

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final String URL_BASE_DADOS_COMPLEMENTARES = "https://qrcodenobu.tse.jus.br/json-bu";

    private List<QRCodeBoletimUrna> qrCodes;

    private ConteudoQRCodeBoletimUrna conteudo;

    private String assinatura;

    public Integer getQuantidadeTotalQRCodes() {
        return this.qrCodes.get(0).getCabecalho().getMarcaInicioDados().getQuantidadeTotalQRCodes();
    }

    private void validarDadosURLChavePublicaVerificacaoAssinatura() throws DadosFaltantesException {
        Map<String, String> dadosFaltantes = new LinkedHashMap<>();

        if (Objects.isNull(this.qrCodes) || this.qrCodes.isEmpty()) {
            throw new QRCodeFaltanteException("O boletim de urna deve ter pelo menos um QR code.");
        }

        QRCodeBoletimUrna primeiroQRCode = this.qrCodes.get(0);

        if (
            Objects.isNull(primeiroQRCode) ||
            Objects.isNull(primeiroQRCode.getCabecalho()) ||
            Objects.isNull(primeiroQRCode.getCabecalho().getVersaoChaveAssinatura())
        ) {
            dadosFaltantes.put("VERSAO_CHAVE", "Vers??o das chaves de assinatura do(s) QR Code(s).");
        }

        Set<Integer> versoesChaveAssinaturaQRCode = new HashSet<>();

        for (QRCodeBoletimUrna qrCode : this.qrCodes) {
            if (
                versoesChaveAssinaturaQRCode.add(qrCode.getCabecalho().getVersaoChaveAssinatura()) &&
                qrCode.getCabecalho().getMarcaInicioDados().getIndiceQRCode() != 1
            ) {
                throw new VersoesChaveAssinaturaDivergentesException("As vers??es da chave utilizada para assinar os QR codes do boletim de urna s??o divergentes.");
            }
        }

        if (
            Objects.isNull(this.conteudo.getProcessoEleitoral()) ||
            Objects.isNull((this.conteudo.getProcessoEleitoral().getOrigemConfiguracao()))
        ) {
            dadosFaltantes.put("ORLC", "Origem da configura????o do processo eleitoral.");
        }

        if (Objects.isNull(this.conteudo.getFase()) || Objects.isNull(this.conteudo.getFase().getAbreviacao())) {
            dadosFaltantes.put("F", "Primeira letra da fase dos dados em min??sculo.");
        }

        if (Objects.isNull(this.conteudo.getUf())) {
            dadosFaltantes.put("UF", "Sigla da UF em min??sculo.");
        }

        if (!dadosFaltantes.isEmpty()) {
            String mensagem = "N??o foi poss??vel compor a URL de verifica????o da assinatura do boletim de urna porque um ou mais dados que a comp??e n??o foram informados ou s??o inv??lidos.";
            throw new DadosFaltantesException(mensagem, dadosFaltantes);
        }
    }

    private String comporURLChavePublicaVerificacaoAssinatura() throws DadosFaltantesException {
        this.validarDadosURLChavePublicaVerificacaoAssinatura();

        Integer versaoChaveAssinaturaQRCode = this.getQrCodes().get(0).getCabecalho().getVersaoChaveAssinatura();
        String origem = this.conteudo.getProcessoEleitoral().getOrigemConfiguracao().toStringURLVerificacaoAssinatura();
        String fase = this.conteudo.getFase().getAbreviacao().toLowerCase();
        String UF = this.conteudo.getUf().toString().toLowerCase();

        return String.format("%s/%s/%s/%s%sqrcode.pub", URL_BASE_VERIFICACAO_ASSINATURA, versaoChaveAssinaturaQRCode, origem, fase, UF);
    }

    private void validarDadosURLDadosComplementares() throws DadosFaltantesException {
        Map<String, String> dadosFaltantes = new LinkedHashMap<>();

        if (Objects.isNull(this.conteudo.getFase())) {
            dadosFaltantes.put("fase", "Fase dos dados por extenso e em min??sculo.");
        }

        if (Objects.isNull(this.conteudo.getProcessoEleitoral())) {
            dadosFaltantes.put("idProcesso", "N??mero do processo eleitoral.");
        }

        if (Objects.isNull(this.conteudo.getFase()) || Objects.isNull(this.conteudo.getFase().getAbreviacao())) {
            dadosFaltantes.put("F", "Primeira letra da fase dos dados em min??sculo.");
        }

        if (Objects.isNull(this.conteudo.getPleito())) {
            dadosFaltantes.put("ppppp", "N??mero do pleito com zeros ?? esquerda, totalizando 5 d??gitos.");
        }

        if (Objects.isNull(this.conteudo.getUf())) {
            dadosFaltantes.put("UF", "Sigla da UF em min??sculo.");
        }

        if (Objects.isNull(this.conteudo.getMunicipio())) {
            dadosFaltantes.put("MMMMM", "N??mero do munic??pio com zeros ?? esquerda, totalizando 5 d??gitos.");
        }

        if (!dadosFaltantes.isEmpty()) {
            String mensagem = "N??o foi poss??vel compor a URL de busca dos dados complementares do boletim de urna porque um ou mais dados que a comp??e n??o foram informados ou s??o inv??lidos.";
            throw new DadosFaltantesException(mensagem, dadosFaltantes);
        }
    }

    private String comporURLDadosComplementares() throws DadosFaltantesException {
        this.validarDadosURLDadosComplementares();

        String fase = this.conteudo.getFase().getNome().toLowerCase();
        String idProcesso = Integer.toString(this.conteudo.getProcessoEleitoral().getCodigoTSE());
        String F = this.conteudo.getFase().getAbreviacao().toLowerCase();
        String ppppp = String.format("%05d", this.conteudo.getPleito().getCodigoTSE());
        String UF = this.conteudo.getUf().toString().toLowerCase();
        String MMMMMM = String.format("%05d", this.conteudo.getMunicipio().getCodigoTSE());

        return String.format("%s/%s/%s/%s%s%s%s-qbu.js", URL_BASE_DADOS_COMPLEMENTARES, fase, idProcesso, F, ppppp, UF, MMMMMM);
    }

    public BoletimUrnaComplemento getDadosComplementares() throws
            DadosFaltantesException,
            FalhaRequisicaoWebException
    {
        String url = this.comporURLDadosComplementares();

        try {
            JsonObject jsonObject = WebRequestUtil.getJSON(url);
            return new GsonBuilder().create().fromJson(jsonObject, BoletimUrnaComplemento.class);
        } catch (IOException exception) {
            String message = String.format("N??o foi poss??vel obter os dados complementares do boletim de urna atrav??s da URL \"%s\".", url);
            throw new FalhaRequisicaoWebException(message);
        }
    }

    private String getHashUltimoQRCode() {
        if (Objects.isNull(this.qrCodes) || this.qrCodes.isEmpty()) {
            throw new QRCodeFaltanteException("O boletim de urna deve ter pelo menos um QR code.");
        }

        return this.qrCodes.get(this.qrCodes.size() - 1).getHash();
    }

    public void verificarAssinatura() {
        try {
            String url = this.comporURLChavePublicaVerificacaoAssinatura();
            byte[] chavePublica = WebRequestUtil.getBytes(url);
            String hashUltimoQRCode = this.getHashUltimoQRCode();

            if (!CryptoUtil.verifySignature(hashUltimoQRCode, this.assinatura, chavePublica)) {
                throw new AssinaturasDivergentesException("A assinatura digital do QR code difere da original, ou seja, o conte??do do boletim de urna foi adulterado.");
            };
        } catch (IOException exception) {
            throw new FalhaRequisicaoWebException("N??o foi poss??vel obter a chave p??blica para verificar a assinatura digital do boletim de urna.");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | SignatureException | InvalidKeyException exception) {
            System.out.println(exception.getMessage());
            throw new FalhaVerificacaoAssinaturaException("N??o foi poss??vel verificar a assinatura digital do boletim de urna.");
        }
    }
}
