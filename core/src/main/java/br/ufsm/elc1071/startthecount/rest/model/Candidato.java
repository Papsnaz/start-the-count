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

@Entity(name = "Candidato")
@Table(name = "candidato", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class Candidato {

    @Id
    @Column(name = "id_candidato", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_candidato_tse", nullable = false)
    private Integer numeroTSE;

    @Column(name = "cod_candidato_tse", unique = true, length = 15, nullable = false)
    private String codigoTSE;

    @Column(name = "nome", length = 63, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<Candidatura> candidaturas;

    public Candidato() {
        this.candidaturas = new LinkedHashSet<>();
    }

    public Candidato(Integer numeroTSE, String codigoTSE, String nome) {
        this();
        this.numeroTSE = numeroTSE;
        this.codigoTSE = codigoTSE;
        this.nome = nome;
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

        Candidato candidato = (Candidato) object;

        return Objects.equals(this.codigoTSE, candidato.codigoTSE);
    }
}
