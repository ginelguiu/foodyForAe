package org.foody.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Tranzactie entity.
 */
public class TranzactieDTO implements Serializable {

    private Long id;

    private String destinatar;

    private String adresa;

    private String contact;

    private Integer valoare;

    private String continut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinatar() {
        return destinatar;
    }

    public void setDestinatar(String destinatar) {
        this.destinatar = destinatar;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getValoare() {
        return valoare;
    }

    public void setValoare(Integer valoare) {
        this.valoare = valoare;
    }

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TranzactieDTO tranzactieDTO = (TranzactieDTO) o;
        if(tranzactieDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tranzactieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TranzactieDTO{" +
            "id=" + getId() +
            ", destinatar='" + getDestinatar() + "'" +
            ", adresa='" + getAdresa() + "'" +
            ", contact='" + getContact() + "'" +
            ", valoare=" + getValoare() +
            ", continut='" + getContinut() + "'" +
            "}";
    }
}
