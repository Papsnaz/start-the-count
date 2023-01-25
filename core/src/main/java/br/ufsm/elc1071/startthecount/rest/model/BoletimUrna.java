package br.ufsm.elc1071.startthecount.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "BoletimUrna")
@Table(name = "boletim_urna", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_usuario", "id_secao_pleito"})})
@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class BoletimUrna {

    @Id
    @Column(name = "id_boletim_urna", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_secao_pleito", nullable = false)
    private SecaoPleito secaoPleito;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_fase", nullable = false)
    private Fase fase;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_origem_boletim_urna", nullable = false)
    private OrigemBoletimUrna origem;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_urna_eletronica", nullable = false)
    private UrnaEletronica urnaEletronica;

    @Column(name = "assinatura", nullable = false, length = 128, columnDefinition = "bpchar")
    private String assinatura;

    @Column(name = "qtde_total_qr_codes")
    private Integer quantidadeTotalQRCodes;

    @Column(name = "data_emissao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataEmissao;

    @Column(name = "horario_emissao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioEmissao;

    @Column(name = "coletado_em", insertable = false, updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime coletadoEm;

    @Column(name = "atualizado_em", insertable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "boletimUrna", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<QRCodeBoletimUrna> qrCodes;

    @OneToMany(mappedBy = "boletimUrna", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<ApuracaoVotosCandidaturaBoletimUrna> apuracoesVotosCandidatura;

    @OneToMany(mappedBy = "boletimUrna", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<ApuracaoVotosCargoBoletimUrna> apuracoesVotosCargo;

    @OneToMany(mappedBy = "boletimUrna", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<ApuracaoVotosPartidoBoletimUrna> apuracoesVotosPartido;

    public BoletimUrna() {
        this.qrCodes = new LinkedHashSet<>();
        this.apuracoesVotosCandidatura = new LinkedHashSet<>();
        this.apuracoesVotosCargo = new LinkedHashSet<>();
        this.apuracoesVotosPartido = new LinkedHashSet<>();
    }

    public BoletimUrna(Usuario usuario, SecaoPleito secaoPleito, Fase fase, OrigemBoletimUrna origem, UrnaEletronica urnaEletronica, String assinatura, Integer quantidadeTotalQRCodes, LocalDate dataEmissao, LocalTime horarioEmissao) {
        this();
        this.usuario = usuario;
        this.secaoPleito = secaoPleito;
        this.fase = fase;
        this.origem = origem;
        this.urnaEletronica = urnaEletronica;
        this.assinatura = assinatura;
        this.quantidadeTotalQRCodes = quantidadeTotalQRCodes;
        this.dataEmissao = dataEmissao;
        this.horarioEmissao = horarioEmissao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.usuario.getUsername(),
            this.secaoPleito.getSecao().getNumeroTSE(),
            this.secaoPleito.getSecao().getZona().getNumeroTSE(),
            this.secaoPleito.getSecao().getZona().getUF().getSigla(),
            this.secaoPleito.getPleito().getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        BoletimUrna boletimUrna = (BoletimUrna) object;

        return
            Objects.equals(this.usuario.getUsername(), boletimUrna.usuario.getUsername()) &&
            Objects.equals(this.secaoPleito.getSecao().getNumeroTSE(), boletimUrna.secaoPleito.getSecao().getNumeroTSE()) &&
            Objects.equals(this.secaoPleito.getSecao().getZona().getNumeroTSE(), boletimUrna.secaoPleito.getSecao().getZona().getNumeroTSE()) &&
            Objects.equals(this.secaoPleito.getSecao().getZona().getUF().getSigla(), boletimUrna.secaoPleito.getSecao().getZona().getUF().getSigla()) &&
            Objects.equals(this.secaoPleito.getPleito().getCodigoTSE(), boletimUrna.secaoPleito.getPleito().getCodigoTSE());
    }
}
