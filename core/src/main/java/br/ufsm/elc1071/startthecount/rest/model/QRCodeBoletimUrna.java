package br.ufsm.elc1071.startthecount.rest.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.Objects;

@Entity(name = "QRCodeBoletimUrna")
@Table(name = "qr_code_boletim_urna", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"indice", "id_boletim_urna"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class QRCodeBoletimUrna {

    @Id
    @Column(name = "id_qr_code_boletim_urna", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_boletim_urna", nullable = false)
    private BoletimUrna boletimUrna;

    @Column(name = "cabecalho", nullable = false)
    private String cabecalho;

    @Column(name = "conteudo", nullable = false)
    private String conteudo;

    @Column(name = "hash", nullable = false, length = 128, columnDefinition = "bpchar")
    private String hash;

    @Column(name = "indice", nullable = false)
    private Integer indice;

    @Column(name = "num_ciclos_eleitorais_desde_implementacao", nullable = false)
    private Integer numeroCiclosEleitoraisDesdeImplementacao;

    @Column(name = "num_revisoes_formato_ciclo", nullable = false)
    private Integer numeroRevisoesFormatoCiclo;

    @Column(name = "num_versao_chave_assinatura", nullable = false)
    private Integer numeroVersaoChaveAssinatura;

    public QRCodeBoletimUrna(BoletimUrna boletimUrna, String cabecalho, String conteudo, String hash, Integer indice, Integer numeroCiclosEleitoraisDesdeImplementacao, Integer numeroRevisoesFormatoCiclo, Integer numeroVersaoChaveAssinatura) {
        this.boletimUrna = boletimUrna;
        this.cabecalho = cabecalho;
        this.conteudo = conteudo;
        this.hash = hash;
        this.indice = indice;
        this.numeroCiclosEleitoraisDesdeImplementacao = numeroCiclosEleitoraisDesdeImplementacao;
        this.numeroRevisoesFormatoCiclo = numeroRevisoesFormatoCiclo;
        this.numeroVersaoChaveAssinatura = numeroVersaoChaveAssinatura;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.indice,
            this.boletimUrna.getUsuario().getUsername(),
            this.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE(),
            this.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            this.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla(),
            this.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        QRCodeBoletimUrna qrCodeBoletimUrna = (QRCodeBoletimUrna) object;

        return
            Objects.equals(this.indice, qrCodeBoletimUrna.indice) &&
            Objects.equals(this.boletimUrna.getUsuario().getUsername(), qrCodeBoletimUrna.boletimUrna.getUsuario().getUsername()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE(), qrCodeBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(), qrCodeBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla(), qrCodeBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE(), qrCodeBoletimUrna.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE());
    }
}
