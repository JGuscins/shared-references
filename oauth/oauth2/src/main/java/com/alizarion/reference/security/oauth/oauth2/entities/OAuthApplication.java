package com.alizarion.reference.security.oauth.oauth2.entities;

import com.alizarion.reference.security.oauth.oauth2.exception.InvalidScopeException;

import javax.persistence.*;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

/**
 * abstract class that contain information about oauth client,
 * @author selim@openlinux.fr.
 * @see OAuthApplicationKey
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({@NamedQuery(name = OAuthApplication.FIND_BY_ClIENT_ID,
        query = "select oa from OAuthApplication oa where" +
                " oa.applicationKey.clientId = :clientId " ),
        @NamedQuery(name = OAuthApplication.FIND_BY_CLIENT_ID_REDIRECT_URI,
                query = "select oa from OAuthApplication oa where" +
                        " oa.applicationKey.clientId = :clientId " +
                        "and oa.redirectURI = :redirectURI" ),
        @NamedQuery(name = OAuthApplication.FIND_BY_CLIENT_ID_AND_SECRET,
                query = "select oa from OAuthApplication oa where" +
                        " oa.applicationKey.clientId = :clientId " +
                        "and oa.applicationKey.clientSecret = :clientSecret" ),
        @NamedQuery(name = OAuthApplication.FIND_BY_APP_NAME,
                query = "select oa from OAuthApplication" +
                        " oa where oa.name = :appName")})
public abstract class OAuthApplication<T extends OAuthScope> implements Serializable {

    private static final long serialVersionUID = 7604489775936326241L;

    public final static String FIND_BY_APP_NAME = "OAuthApplication.FIND_BY_APP_NAME";
    public final static String FIND_BY_ClIENT_ID = "OAuthApplication.FIND_BY_ClIENT_ID";
    public final static String FIND_BY_CLIENT_ID_REDIRECT_URI = "OAuthApplication.FIND_BY_CLIENT_ID_REDIRECT_URI";
    public final static String FIND_BY_CLIENT_ID_AND_SECRET = "OAuthApplication.FIND_BY_CLIENT_ID_AND_SECRET";


    @Id
    @TableGenerator(name = "security_oauth_application_SEQ",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT",
            table = "sequence")
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "security_oauth_application_SEQ")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "home_page_url",
            nullable = false,
            unique = true)
    private String homePage;

    @Column(name = "redirect_uri",
            nullable = false,
            unique = true)
    private String redirectURI;

    @Embedded
    protected OAuthApplicationKey applicationKey;




    protected OAuthApplication() {

    }

    public OAuthApplication(final String name,
                            final URL homePage,
                            final URI callback) {
        this.name = name;
        this.homePage = homePage.toString();
        this.redirectURI = callback.toString();
    }


    public void setApplicationKey(
            final OAuthApplicationKey applicationKey) {
        this.applicationKey = applicationKey;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getHomePage() throws MalformedURLException {
        return new URL(this.homePage);
    }

    public void setHomePage(URL homePage) {
        this.homePage = homePage.toString();
    }

    public URI getRedirectURI() throws URISyntaxException {
        return new URI(redirectURI);
    }

    public void setRedirectURI(URI callback) {
        this.redirectURI = callback.toString();
    }

    public abstract OAuthAuthorization addAuthorization(
            final OAuthCredential credential,
            final Set<T> roles) throws InvalidScopeException, InvalidScopeException;

    public void setId(Long id) {
        this.id = id;
    }

    public OAuthApplicationKey getApplicationKey() {
        return applicationKey;
    }


    @Override
    public String toString() {
        return "OAuthApplication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", homePage=" + homePage +
                ", callback=" + redirectURI +
                ", applicationKey=" + applicationKey +
                '}';
    }
}
