package br.ufsm.elc1071.startthecount.rest.dto.retrieval;

public interface ApuracaoVotosCandidaturaRetrievalDTO {

    Integer getNumeroCandidato();

    String getNomeCandidato();

    String getNomeCargo();

    String getNomeEleicao();

    String getNomePleito();

    Integer getTotalVotosApurados();
}
