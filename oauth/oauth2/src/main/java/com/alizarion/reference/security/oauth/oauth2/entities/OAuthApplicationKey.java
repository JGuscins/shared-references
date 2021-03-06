package com.alizarion.reference.security.oauth.oauth2.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * oauth clientId and clientSecret container
 * @author selim@openlinux.fr.
 */
@Embeddable
public class OAuthApplicationKey {

    protected OAuthApplicationKey() {

    }

    public OAuthApplicationKey(final String clientId,
                               final String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Column(name = "client_id",
            unique = true,
            updatable = false)
    private String clientId;

    @Column(name = "client_secret",
            nullable = false,
            unique = true)
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public String toString() {
        return "OauthApplicationKey{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                '}';
    }
}
