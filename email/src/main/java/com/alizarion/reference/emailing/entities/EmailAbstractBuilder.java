package com.alizarion.reference.emailing.entities;

import java.net.URI;
import java.util.Locale;

/**
 * Builder class that can be used to help building <br/>
 * emails objects that can contains many properties.
 * @author Selim Bensenouci.
 * @see com.alizarion.reference.emailing.entities.Email
 */
public abstract class EmailAbstractBuilder {

    private String from;

    private String to;

    private String cc;

    private String cci;


    private Locale locale;

    private URI templateRoot;

    protected EmailAbstractBuilder(final String from,
                                   final String to,
                                   final URI templateRoot,
                                   final Locale locale) {
        this.templateRoot = templateRoot;
        this.from = from;
        this.to = to;
        this.locale = locale;
    }

    public EmailAbstractBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    public EmailAbstractBuilder setCc(String cc) {
        this.cc = cc;
        return this;
    }

    public EmailAbstractBuilder setCci(String cci) {
        this.cci = cci;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getCc() {
        return cc;
    }

    public String getCci() {
        return cci;
    }


    public Locale getLocale() {
        return locale;
    }

    public URI getTemplateRoot() {
        return templateRoot;
    }

    public void setTemplateRoot(URI templateRoot) {
        this.templateRoot = templateRoot;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public abstract Email builder();
}
