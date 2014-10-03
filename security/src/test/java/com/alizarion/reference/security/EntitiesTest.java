package com.alizarion.reference.security;

import com.alizarion.reference.security.entities.oauth.OAuthAccessToken;
import com.alizarion.reference.security.entities.oauth.server.OAuthClientApplication;
import com.alizarion.reference.security.exception.oauth.OAuthException;
import org.junit.*;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author selim@openlinux.fr.
 */
public class EntitiesTest {

    EntityManagerFactory emf;
    EntityManager em;

    @Rule
    public ExpectedException expectedException =
            ExpectedException.none();

    @Before
    public void init(){
        emf = Persistence.createEntityManagerFactory("SecurityTestPU");
        em = emf.createEntityManager();

    }

    @After
    public void destroy(){
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    /**
     * Simple Method to test coherency of jpa annotations
     */
    @Test
    public void TestJPAEntities(){
        EntityTransaction trx = em.getTransaction();

        try {
            // Start new transaction
            trx.begin();

            // Add some objects

            // commit the transaction
            trx.commit();
        } catch (RuntimeException e) {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            throw e;
        }


    }

    /**
     * Simple Method to test coherency of jpa annotations
     */
   // @Test
    public void TestOauthEntitiesPersist()
            throws MalformedURLException, OAuthException {
        this.expectedException.expect(OAuthException.class);
        EntityTransaction trx = em.getTransaction();
        OAuthClientApplication oauthApplication =
                new OAuthClientApplication("Pixefolio.com",
                        new URL("http://pixefolio.com"),
                        new URL("http://pixefolio.com/callback"));
        try {
            // Start new transaction
            trx.begin();
            oauthApplication = this.em.merge(oauthApplication);
            // Add some objects
            //TODO corrigé le test
           // oauthApplication.addAuthorization(3000);
            System.out.println(oauthApplication);
            System.out.println(new ArrayList<>(
                    oauthApplication.
                            getAuthorizations()).get(0));
            OAuthAccessToken accessToken = new ArrayList<>(
                    oauthApplication.getAuthorizations()).
                    get(0).getNewAccessToken(34L);
            oauthApplication = this.em.merge(oauthApplication);
            this.em.flush();
            this.em.clear();
            OAuthAccessToken accessToken2  = this.em.find(
                    OAuthAccessToken.class,accessToken.getBearer());

            System.out.println(accessToken2);
            Assert.assertNotNull(oauthApplication.
                    getApplicationKey().toString());
            Assert.assertNotNull(accessToken2.toString());
            System.out.println(accessToken2.
                    getAuthorization().getCredential());
            // commit the transaction
            trx.commit();
        } catch (RuntimeException e) {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            throw e;
        }


    }
}
