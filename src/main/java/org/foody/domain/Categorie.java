package org.foody.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Categorie.
 */
@Entity
@Table(name = "categorie")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nume_categorie", nullable = false)
    private String numeCategorie;

    @Column(name = "imagine_categorie")
    private String imagineCategorie;

    @Column(name = "pret")
    private Integer pret;

    @Column(name = "stoc")
    private Integer stoc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeCategorie() {
        return numeCategorie;
    }

    public Categorie numeCategorie(String numeCategorie) {
        this.numeCategorie = numeCategorie;
        return this;
    }

    public void setNumeCategorie(String numeCategorie) {
        this.numeCategorie = numeCategorie;
    }

    public String getImagineCategorie() {
        return imagineCategorie;
    }

    public Categorie imagineCategorie(String imagineCategorie) {
        this.imagineCategorie = imagineCategorie;
        return this;
    }

    public void setImagineCategorie(String imagineCategorie) {
        this.imagineCategorie = imagineCategorie;
    }

    public Integer getPret() {
        return pret;
    }

    public Categorie pret(Integer pret) {
        this.pret = pret;
        return this;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    public Integer getStoc() {
        return stoc;
    }

    public Categorie stoc(Integer stoc) {
        this.stoc = stoc;
        return this;
    }

    public void setStoc(Integer stoc) {
        this.stoc = stoc;
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
        Categorie categorie = (Categorie) o;
        if (categorie.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categorie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Categorie{" +
            "id=" + getId() +
            ", numeCategorie='" + getNumeCategorie() + "'" +
            ", imagineCategorie='" + getImagineCategorie() + "'" +
            ", pret=" + getPret() +
            ", stoc=" + getStoc() +
            "}";
    }
}
