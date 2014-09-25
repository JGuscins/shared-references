package com.alizarion.reference.emailing.entities;


import com.alizarion.reference.person.entities.ValidateEmailToken;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.File;
import java.net.URI;
import java.util.*;

/**
 * @author selim@openlinux.fr.
 */
@Entity
@DiscriminatorValue(value = GenericRegisterEmail.TYPE)
public class GenericRegisterEmail extends Email {

    private static final long serialVersionUID = -4901018233807963289L;

    public final static String TYPE = "register-email";

    @Transient
    private List<File> attachments = new ArrayList<>();

    @Transient
    private  ValidateEmailToken emailToken;

    public GenericRegisterEmail(RegisterEmailBuilder builder) {
        super(builder.getFrom(),
                builder.getTo(),
                builder.getTemplateRoot(),
                builder.getLocale());
        this.emailToken = builder.getToken();
        super.setCc(builder.getCc());
        super.setCci(builder.getCci());

        Map<String,Object> subject = new HashMap<>();
        Map<String,Object> bodyHTML = new HashMap<>();
        Map<String,Object> bodyText = new HashMap<>();

        subject.put("emailToken",builder.getToken());
        bodyHTML.put("emailToken",builder.getToken());
        bodyText.put("emailToken",builder.getToken());

        this.params.put(MAIL_SUBJECT_TEMPLATE,subject);
        this.params.put(MAIL_HTML_BODY_TEMPLATE,bodyHTML);
        this.params.put(MAIL_TEXT_BODY_TEMPLATE,bodyText);
    }


    @Override
    public Map<String, Map<String, Object>> getParams() {
        return this.params;
    }


    @Override
    public List<File> getAttachments() {
        return this.attachments;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public void addAttachment(File file){
        this.attachments.add(file);

    }

    public ValidateEmailToken getEmailToken() {
        return emailToken;
    }

    public void setEmailToken(ValidateEmailToken emailToken) {
        this.emailToken = emailToken;
    }

    public static class RegisterEmailBuilder extends EmailAbstractBuilder {


        private ValidateEmailToken token;

        public RegisterEmailBuilder(final String from,
                                    final ValidateEmailToken token,
                                    final URI templateRoot,
                                    final Locale locale
        ) {
            super(from,token.getElectronicAddress().getEmailAddress(),templateRoot,locale);
            this.token = token;
        }

        public ValidateEmailToken getToken() {
            return token;
        }

        @Override
        public Email builder() {
            return new GenericRegisterEmail(this);
        }
    }

    @Override
    public String toString() {
        return "GenericRegisterEmail{" +
                "attachments=" + attachments +
                ", emailToken=" + emailToken +
                '}';
    }
}
