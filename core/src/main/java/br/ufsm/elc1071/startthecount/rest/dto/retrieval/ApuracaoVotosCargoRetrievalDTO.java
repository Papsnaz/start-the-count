package br.ufsm.elc1071.startthecount.rest.dto.retrieval;

public interface ApuracaoVotosCargoRetrievalDTO {

    String getNomeCargo();

    String getNomeEleicao();

    String getNomePleito();

    Integer getQuantidadeVotosNominais();

    Integer getQuantidadeVotosDeLegenda();

    Integer getQuantidadeVotosEmBranco();

    Integer getQuantidadeVotosNulos();

    Integer getTotalVotosApurados();
}
