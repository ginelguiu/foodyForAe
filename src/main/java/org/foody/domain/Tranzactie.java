package org.foody.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Tranzactie.
 */
@Entity
@Table(name = "tranzactie")
public class Tranzactie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destinatar")
    private String destinatar;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "contact")
    private String contact;

    @Column(name = "valoare")
    private Integer valoare;

    @Column(name = "continut")
    private String continut;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinatar() {
        return destinatar;
    }

    public Tranzactie destinatar(String destinatar) {
        this.destinatar = destinatar;
        return this;
    }

    public void setDestinatar(String destinatar) {
        this.destinatar = destinatar;
    }

    public String getAdresa() {
        return adresa;
    }

    public Tranzactie adresa(String adresa) {
        this.adresa = adresa;
        return this;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getContact() {
        return contact;
    }

    public Tranzactie contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getValoare() {
        return valoare;
    }

    public Tranzactie valoare(Integer valoare) {
        this.valoare = valoare;
        return this;
    }

    public void setValoare(Integer valoare) {
        this.valoare = valoare;
    }

    public String getContinut() {
        return continut;
    }

    public Tranzactie continut(String continut) {
        this.continut = continut;
        return this;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tranzactie tranzactie = (Tranzactie) o;
        if (tranzactie.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tranzactie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
            "id=" + getId() +
            ", destinatar='" + getDestinatar() + "'" +
            ", adresa='" + getAdresa() + "'" +
            ", contact='" + getContact() + "'" +
            ", valoare=" + getValoare() +
            ", continut='" + getContinut() + "'" +
            "}";
    }
}
