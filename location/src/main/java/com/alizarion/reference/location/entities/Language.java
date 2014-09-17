package com.alizarion.reference.location.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author selim@openlinux.fr.
 */
@Entity
@Table(name = "location_language")
public class Language implements Serializable {

    private static final long serialVersionUID = -1283144562330733745L;

    @Column(name = "managed")
    private Boolean managed;

    @Id
    @Column(name = "lang_id")
    private String LangCode;

    public Language() {
        this.managed = false;
    }

    public Boolean getManaged() {
        return managed;
    }

    public void setManaged(Boolean managed) {
        this.managed = managed;
    }

    public String getLangCode() {
        return LangCode;
    }

    public void setLangCode(String langCode) {
        LangCode = langCode;
    }
}
