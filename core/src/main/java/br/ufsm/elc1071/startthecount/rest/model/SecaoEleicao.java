package br.ufsm.elc1071.startthecount.rest.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.Objects;

@Entity(name = "SecaoEleicao")
@Table(name = "secao_eleicao", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_secao", "id_eleicao"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class SecaoEleicao {

    @Id
    @Column(name = "id_secao_eleicao", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_secao", nullable = false)
    private Secao secao;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_eleicao", nullable = false)
    private Eleicao eleicao;

    @Column(name = "qtde_eleitores_aptos")
    private Integer quantidadeEleitoresAptos;

    @Column(name = "qtde_eleitores_comparecentes")
    private Integer quantidadeEleitoresComparecentes;

    @Column(name = "qtde_eleitores_faltosos")
    private Integer quantidadeEleitoresFaltosos;

    @Column(name = "qtde_eleitores_habilitados_por_ano_nasc")
    private Integer quantidadeEleitoresHabilitadosPorAnoNascimento;

    public SecaoEleicao(Secao secao, Eleicao eleicao, Integer quantidadeEleitoresAptos, Integer quantidadeEleitoresComparecentes, Integer quantidadeEleitoresFaltosos, Integer quantidadeEleitoresHabilitadosPorAnoNascimento) {
        this.secao = secao;
        this.eleicao = eleicao;
        this.quantidadeEleitoresAptos = quantidadeEleitoresAptos;
        this.quantidadeEleitoresComparecentes = quantidadeEleitoresComparecentes;
        this.quantidadeEleitoresFaltosos = quantidadeEleitoresFaltosos;
        this.quantidadeEleitoresHabilitadosPorAnoNascimento = quantidadeEleitoresHabilitadosPorAnoNascimento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.secao.getNumeroTSE(),
            this.secao.getZona().getNumeroTSE(),
            this.secao.getZona().getUF().getSigla(),
            this.eleicao.getCodigoTSE()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        SecaoEleicao secaoEleicao = (SecaoEleicao) object;

        return
            Objects.equals(this.secao.getNumeroTSE(), secaoEleicao.secao.getNumeroTSE()) &&
            Objects.equals(this.secao.getZona().getNumeroTSE(), secaoEleicao.secao.getZona().getNumeroTSE()) &&
            Objects.equals(this.secao.getZona().getUF().getSigla(), secaoEleicao.secao.getZona().getUF().getSigla()) &&
            Objects.equals(this.eleicao.getCodigoTSE(), secaoEleicao.eleicao.getCodigoTSE());
    }
}
