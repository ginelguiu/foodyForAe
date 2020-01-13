package org.foody.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Categorie entity.
 */
public class CategorieDTO implements Serializable {

    private Long id;

    @NotNull
    private String numeCategorie;

    private String imagineCategorie;

    private Integer pret;

    private Integer stoc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeCategorie() {
        return numeCategorie;
    }

    public void setNumeCategorie(String numeCategorie) {
        this.numeCategorie = numeCategorie;
    }

    public String getImagineCategorie() {
        return imagineCategorie;
    }

    public void setImagineCategorie(String imagineCategorie) {
        this.imagineCategorie = imagineCategorie;
    }

    public Integer getPret() {
        return pret;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    public Integer getStoc() {
        return stoc;
    }

    public void setStoc(Integer stoc) {
        this.stoc = stoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategorieDTO categorieDTO = (CategorieDTO) o;
        if(categorieDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categorieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategorieDTO{" +
            "id=" + getId() +
            ", numeCategorie='" + getNumeCategorie() + "'" +
            ", imagineCategorie='" + getImagineCategorie() + "'" +
            ", pret=" + getPret() +
            ", stoc=" + getStoc() +
            "}";
    }
}
