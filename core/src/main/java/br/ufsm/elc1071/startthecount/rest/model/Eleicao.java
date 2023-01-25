package br.ufsm.elc1071.startthecount.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Eleicao")
@Table(name = "eleicao", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class Eleicao {

    @Id
    @Column(name = "id_eleicao", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cod_eleicao_tse", unique = true, nullable = false)
    private Integer codigoTSE;

    @Column(name = "nome", length = 63)
    private String nome;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "qtde_votos_cargos_majoritarios")
    private Integer quantidadeVotosCargosMajoritarios;

    @Column(name = "qtde_votos_cargos_proporcionais")
    private Integer quantidadeVotosCargosProporcionais;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_pleito", nullable = false)
    private Pleito pleito;

    @OneToMany(mappedBy = "eleicao", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<CargoEleicao> cargos;

    @OneToMany(mappedBy = "eleicao", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<SecaoEleicao> secoes;

    @OneToMany(mappedBy = "eleicao", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<ApuracaoVotosPartidoBoletimUrna> apuracoesVotosPartido;

    public Eleicao() {
        this.cargos = new LinkedHashSet<>();
        this.secoes = new LinkedHashSet<>();
        this.apuracoesVotosPartido = new LinkedHashSet<>();
    }

    public Eleicao(Integer codigoTSE, String nome, Integer ano, Integer quantidadeVotosCargosMajoritarios, Integer quantidadeVotosCargosProporcionais, Pleito pleito) {
        this();
        this.codigoTSE = codigoTSE;
        this.nome = nome;
        this.ano = ano;
        this.quantidadeVotosCargosMajoritarios = quantidadeVotosCargosMajoritarios;
        this.quantidadeVotosCargosProporcionais = quantidadeVotosCargosProporcionais;
        this.pleito = pleito;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigoTSE);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        Eleicao eleicao = (Eleicao) object;

        return Objects.equals(this.codigoTSE, eleicao.codigoTSE);
    }
}
