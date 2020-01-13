package org.foody.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


public class ReviewDTO implements Serializable {

    private Long id;

    @NotNull
    private String titlu;

    private String imagine;

    @NotNull
    private String coordonateLocatie;

    @NotNull
    private Integer numarStele;

    @NotNull
    private String continutReview;

    private LocalDate dataReview;

    private Integer numarLikeuri;

    private Integer numarDislikeuri;

    private Boolean moderat;

    private Long userId;

    private Long categorieId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }

    public String getCoordonateLocatie() {
        return coordonateLocatie;
    }

    public void setCoordonateLocatie(String coordonateLocatie) {
        this.coordonateLocatie = coordonateLocatie;
    }

    public Integer getNumarStele() {
        return numarStele;
    }

    public void setNumarStele(Integer numarStele) {
        this.numarStele = numarStele;
    }

    public String getContinutReview() {
        return continutReview;
    }

    public void setContinutReview(String continutReview) {
        this.continutReview = continutReview;
    }

    public LocalDate getDataReview() {
        return dataReview;
    }

    public void setDataReview(LocalDate dataReview) {
        this.dataReview = dataReview;
    }

    public Integer getNumarLikeuri() {
        return numarLikeuri;
    }

    public void setNumarLikeuri(Integer numarLikeuri) {
        this.numarLikeuri = numarLikeuri;
    }

    public Integer getNumarDislikeuri() {
        return numarDislikeuri;
    }

    public void setNumarDislikeuri(Integer numarDislikeuri) {
        this.numarDislikeuri = numarDislikeuri;
    }

    public Boolean isModerat() {
        return moderat;
    }

    public void setModerat(Boolean moderat) {
        this.moderat = moderat;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReviewDTO reviewDTO = (ReviewDTO) o;
        if(reviewDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reviewDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
            "id=" + getId() +
            ", titlu='" + getTitlu() + "'" +
            ", imagine='" + getImagine() + "'" +
            ", coordonateLocatie='" + getCoordonateLocatie() + "'" +
            ", numarStele=" + getNumarStele() +
            ", continutReview='" + getContinutReview() + "'" +
            ", dataReview='" + getDataReview() + "'" +
            ", numarLikeuri=" + getNumarLikeuri() +
            ", numarDislikeuri=" + getNumarDislikeuri() +
            ", moderat='" + isModerat() + "'" +
            "}";
    }
}
