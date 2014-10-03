package com.alizarion.reference.security.exception.oauth;

/**
 * Invalid response_type parameter in initial Authorization.
 * @author selim@openlinux.fr.
 */
public class UnsupportedResponseTypeException extends OAuthException {

    private static final long serialVersionUID = -1637158089078719543L;

    public UnsupportedResponseTypeException(String msg) {
        super(msg);
    }

    public UnsupportedResponseTypeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UnsupportedResponseTypeException(Throwable cause) {
        super(cause);
    }
}
