package br.ufsm.elc1071.startthecount.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "LocalVotacao")
@Table(name = "local_votacao", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"num_local_votacao_tse", "id_zona"})})
@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class LocalVotacao {

    @Id
    @Column(name = "id_local_votacao", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_local_votacao_tse", nullable = false)
    private Integer numeroTSE;

    @Column(name = "nome")
    private String nome;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_zona", nullable = false)
    private Zona zona;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_municipio", nullable = false)
    private Municipio municipio;

    @OneToMany(mappedBy = "localVotacao", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<SecaoProcessoEleitoral> secoes;

    public LocalVotacao() {
        this.secoes = new LinkedHashSet<>();
    }

    public LocalVotacao(Integer numeroTSE, Zona zona, Municipio municipio) {
        this();
        this.numeroTSE = numeroTSE;
        this.zona = zona;
        this.municipio = municipio;
    }

    public LocalVotacao(Integer numeroTSE, String nome, Zona zona, Municipio municipio) {
        this(numeroTSE, zona, municipio);
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.numeroTSE, this.zona.getNumeroTSE(), this.zona.getUF().getSigla());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        LocalVotacao localVotacao = (LocalVotacao) object;

        return
            Objects.equals(this.numeroTSE, localVotacao.numeroTSE) &&
            Objects.equals(this.zona.getNumeroTSE(), localVotacao.zona.getNumeroTSE()) &&
            Objects.equals(this.zona.getUF().getSigla(), localVotacao.zona.getUF().getSigla());
    }
}
