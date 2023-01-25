package br.ufsm.elc1071.startthecount.rest.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.Objects;

@Entity(name = "ApuracaoVotosCargo")
@Table(name = "apuracao_votos_cargo_boletim_urna", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_cargo_eleicao", "id_boletim_urna"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class ApuracaoVotosCargoBoletimUrna {

    @Id
    @Column(name = "id_apuracao_votos_cargo_boletim_urna", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_cargo_eleicao", nullable = false)
    private CargoEleicao cargoEleicao;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_boletim_urna", nullable = false)
    private BoletimUrna boletimUrna;

    @Column(name = "qtde_eleitores_aptos")
    private Integer quantidadeEleitoresAptos;

    @Column(name = "qtde_comparecimento_cargo_sem_candidatos")
    private Integer quantidadeComparecimentoCargoSemCandidatos;

    @Column(name = "qtde_votos_nominais", nullable = false)
    private Integer quantidadeVotosNominais;

    @Column(name = "qtde_votos_de_legenda", nullable = false)
    private Integer quantidadeVotosDeLegenda;

    @Column(name = "qtde_votos_em_branco", nullable = false)
    private Integer quantidadeVotosEmBranco;

    @Column(name = "qtde_votos_nulos", nullable = false)
    private Integer quantidadeVotosNulos;

    @Column(name = "total_votos_apurados", nullable = false)
    private Integer totalVotosApurados;

    public ApuracaoVotosCargoBoletimUrna(CargoEleicao cargoEleicao, BoletimUrna boletimUrna, Integer quantidadeEleitoresAptos, Integer quantidadeComparecimentoCargoSemCandidatos, Integer quantidadeVotosNominais, Integer quantidadeVotosDeLegenda, Integer quantidadeVotosEmBranco, Integer quantidadeVotosNulos, Integer totalVotosApurados) {
        this.cargoEleicao = cargoEleicao;
        this.boletimUrna = boletimUrna;
        this.quantidadeEleitoresAptos = quantidadeEleitoresAptos;
        this.quantidadeComparecimentoCargoSemCandidatos = quantidadeComparecimentoCargoSemCandidatos;
        this.quantidadeVotosNominais = quantidadeVotosNominais;
        this.quantidadeVotosDeLegenda = quantidadeVotosDeLegenda;
        this.quantidadeVotosEmBranco = quantidadeVotosEmBranco;
        this.quantidadeVotosNulos = quantidadeVotosNulos;
        this.totalVotosApurados = totalVotosApurados;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.cargoEleicao.getCargo().getCodigoTSE(),
            this.cargoEleicao.getEleicao().getCodigoTSE(),
            this.boletimUrna.getUsuario().getUsername(),
            this.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE(),
            this.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE(),
            this.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            this.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (object == null) return false;

        if (this.getClass() != object.getClass()) return false;

        ApuracaoVotosCargoBoletimUrna apuracaoVotosCargoBoletimUrna = (ApuracaoVotosCargoBoletimUrna) object;

        return
            Objects.equals(this.cargoEleicao.getCargo().getCodigoTSE(), apuracaoVotosCargoBoletimUrna.cargoEleicao.getCargo().getCodigoTSE()) &&
            Objects.equals(this.cargoEleicao.getEleicao().getCodigoTSE(), apuracaoVotosCargoBoletimUrna.cargoEleicao.getEleicao().getCodigoTSE()) &&
            Objects.equals(this.boletimUrna.getUsuario().getUsername(), apuracaoVotosCargoBoletimUrna.boletimUrna.getUsuario().getUsername()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE(), apuracaoVotosCargoBoletimUrna.boletimUrna.getSecaoPleito().getPleito().getCodigoTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE(), apuracaoVotosCargoBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getNumeroTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE(), apuracaoVotosCargoBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getZona().getNumeroTSE()) &&
            Objects.equals(this.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla(), apuracaoVotosCargoBoletimUrna.boletimUrna.getSecaoPleito().getSecao().getZona().getUF().getSigla());
    }
}
