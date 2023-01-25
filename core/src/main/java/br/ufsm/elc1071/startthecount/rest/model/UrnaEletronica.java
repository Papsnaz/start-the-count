package br.ufsm.elc1071.startthecount.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "UrnaEletronica")
@Table(name = "urna_eletronica", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class UrnaEletronica {

    @Id
    @Column(name = "id_urna_eletronica", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_serie_urna_eletronica_tse", unique = true, nullable = false)
    private Integer numeroSerieTSE;

    @Column(name = "cod_identificacao_carga", nullable = false, length = 24, columnDefinition = "bpchar")
    private String codigoIdentificacaoCarga;

    @Column(name = "versao_software", nullable = false, length = 15)
    private String versaoSoftware;

    @Column(name = "data_abertura", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura;

    @Column(name = "horario_abertura", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioAbertura;

    @Column(name = "data_fechamento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    @Column(name = "horario_fechamento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioFechamento;

    @OneToMany(mappedBy = "urnaEletronica", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<BoletimUrna> boletinsUrna;

    public UrnaEletronica() {
        this.boletinsUrna = new LinkedHashSet<>();
    }

    public UrnaEletronica(Integer numeroSerieTSE, String codigoIdentificacaoCarga, String versaoSoftware, LocalDate dataAbertura, LocalTime horarioAbertura, LocalDate dataFechamento, LocalTime horarioFechamento) {
        this();
        this.numeroSerieTSE = numeroSerieTSE;
        this.codigoIdentificacaoCarga = codigoIdentificacaoCarga;
        this.versaoSoftware = versaoSoftware;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.numeroSerieTSE);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        UrnaEletronica urnaEletronica = (UrnaEletronica) object;

        return Objects.equals(this.numeroSerieTSE, urnaEletronica.numeroSerieTSE);
    }
}
