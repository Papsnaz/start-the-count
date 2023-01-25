package br.ufsm.elc1071.startthecount.rest.dto.retrieval;

public interface ApuracaoVotosPartidoRetrievalDTO {

    Integer getNumeroPartido();

    String getNomePartido();

    String getNomePleito();

    Integer getQuantidadeVotosDeLegenda();

    Integer getTotalVotosApurados();
}
