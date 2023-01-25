package br.ufsm.elc1071.startthecount.rest.exception;

public class UsuarioNaoAutenticadoException extends RuntimeException {

    public UsuarioNaoAutenticadoException(String message) {
        super(message);
    }
}
