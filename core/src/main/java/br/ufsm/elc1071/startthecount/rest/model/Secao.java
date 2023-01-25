package br.ufsm.elc1071.startthecount.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Secao")
@Table(name = "secao", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"num_secao_tse", "id_zona"})})
@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class Secao {

    @Id
    @Column(name = "id_secao", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_secao_tse", nullable = false)
    private Integer numeroTSE;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_zona", nullable = false)
    private Zona zona;

    @OneToMany(mappedBy = "secao", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<SecaoProcessoEleitoral> processosEleitorais;

    @OneToMany(mappedBy = "secao", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<SecaoPleito> pleitos;

    @OneToMany(mappedBy = "secao", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<SecaoEleicao> eleicoes;

    public Secao() {
        this.processosEleitorais = new LinkedHashSet<>();
        this.pleitos = new LinkedHashSet<>();
        this.eleicoes = new LinkedHashSet<>();
    }

    public Secao(Integer numeroTSE, Zona zona) {
        this();
        this.numeroTSE = numeroTSE;
        this.zona = zona;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.getNumeroTSE(),
            this.getZona().getNumeroTSE(),
            this.getZona().getUF().getSigla()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        Secao secao = (Secao) object;

        return
            Objects.equals(this.getNumeroTSE(), secao.getNumeroTSE()) &&
            Objects.equals(this.getZona().getNumeroTSE(), secao.getZona().getNumeroTSE()) &&
            Objects.equals(this.getZona().getUF().getSigla(), secao.getZona().getUF().getSigla());
    }
}
