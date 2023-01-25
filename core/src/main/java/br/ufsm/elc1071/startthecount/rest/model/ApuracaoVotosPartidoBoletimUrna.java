package br.ufsm.elc1071.startthecount.rest.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.Objects;

@Entity(name = "ApuracaoVotosPartido")
@Table(name = "apuracao_votos_partido_boletim_urna", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_partido", "id_boletim_urna", "id_eleicao"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class ApuracaoVotosPartidoBoletimUrna {

    @Id
    @Column(name = "id_apuracao_votos_partido_boletim_urna", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_partido", nullable = false)
    private Partido partido;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_boletim_urna", nullable = false)
    private BoletimUrna boletimUrna;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_eleicao", nullable = false)
    private Eleicao eleicao;

    @Column(name = "qtde_votos_de_legenda", nullable = false)
    private Integer quantidadeVotosDeLegenda;

    @Column(name = "total_votos_apurados", nullable = false)
    private Integer totalVotosApurados;

    public ApuracaoVotosPartidoBoletimUrna(Partido partido, BoletimUrna boletimUrna, Eleicao eleicao, Integer quantidadeVotosDeLegenda, Integer totalVotosApurados) {
        this.partido = partido;
        this.boletimUrna = boletimUrna;
        this.eleicao = eleicao;
        this.quantidadeVotosDeLegenda = quantidadeVotosDeLegenda;
        this.totalVotosApurados = totalVotosApurados;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.partido.getNumeroTSE(),
            this.eleicao.getCodigoTSE(),
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

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        ApuracaoVotosPartidoBoletimUrna apuracaoVotosPartidoBoletimUrna = (ApuracaoVotosPartidoBoletimUrna) object;

        return
            Objects.equals(this.partido.getNumeroTSE(), apuracaoVotosPartidoBoletimUrna.partido.getNumeroTSE()) &&
            Objects.equals(this.eleicao.getCodigoTSE(), apuracaoVotosPartidoBoletimUrna.eleicao.getCodigoTSE()) &&
            Objects.equals(this.boletimUrna.getUsuario(), apuracaoVotosPartidoBoletimUrna.boletimUrna.getUsuario()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE(), apuracaoVotosPartidoBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(), apuracaoVotosPartidoBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla(), apuracaoVotosPartidoBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE(), apuracaoVotosPartidoBoletimUrna.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE());
    }
}
