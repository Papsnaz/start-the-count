package br.ufsm.elc1071.startthecount.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
public class QRCodeBoletimUrna {

    private CabecalhoQRCodeBoletimUrna cabecalho;

    private ConteudoQRCodeBoletimUrna conteudo;

    private String hash;

    private String payload;

    public String getPayloadCabecalho() {
        List<String> payloadCabecalho = new LinkedList<>();

        for (String record : this.payload.split(" ")) {
            String[] keyValue = record.split(":", 2);
            String key = keyValue[0].toUpperCase();

            if (
                key.equals("QRBU") ||
                key.equals("VRQR") ||
                key.equals("VRCH")
            ) {
                payloadCabecalho.add(record);
            }
        }

        return String.join(" ", payloadCabecalho);
    }

    public String getPayloadConteudo() {
        List<String> payloadConteudo = new LinkedList<>();

        for (String record : this.payload.split(" ")) {
            String[] keyValue = record.split(":", 2);
            String key = keyValue[0].toUpperCase();

            if (
                !key.equals("QRBU") &&
                !key.equals("VRQR") &&
                !key.equals("VRCH") &&
                !key.equals("HASH") &&
                !key.equals("ASSI")
            ) {
                payloadConteudo.add(record);
            }

        }

        return String.join(" ", payloadConteudo);
    }
}
