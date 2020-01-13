package org.foody.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "titlu", nullable = false)
    private String titlu;

    @Column(name = "imagine")
    private String imagine;

    @NotNull
    @Column(name = "coordonate_locatie", nullable = false)
    private String coordonateLocatie;

    @NotNull
    @Column(name = "numar_stele", nullable = false)
    private Integer numarStele;

    @NotNull
    @Column(name = "continut_review", nullable = false)
    private String continutReview;

    @Column(name = "data_review")
    private LocalDate dataReview;

    @Column(name = "numar_likeuri")
    private Integer numarLikeuri;

    @Column(name = "numar_dislikeuri")
    private Integer numarDislikeuri;

    @Column(name = "moderat")
    private Boolean moderat;

    @ManyToOne
    private User user;

    @ManyToOne
    private Categorie categorie;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public Review titlu(String titlu) {
        this.titlu = titlu;
        return this;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getImagine() {
        return imagine;
    }

    public Review imagine(String imagine) {
        this.imagine = imagine;
        return this;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }

    public String getCoordonateLocatie() {
        return coordonateLocatie;
    }

    public Review coordonateLocatie(String coordonateLocatie) {
        this.coordonateLocatie = coordonateLocatie;
        return this;
    }

    public void setCoordonateLocatie(String coordonateLocatie) {
        this.coordonateLocatie = coordonateLocatie;
    }

    public Integer getNumarStele() {
        return numarStele;
    }

    public Review numarStele(Integer numarStele) {
        this.numarStele = numarStele;
        return this;
    }

    public void setNumarStele(Integer numarStele) {
        this.numarStele = numarStele;
    }

    public String getContinutReview() {
        return continutReview;
    }

    public Review continutReview(String continutReview) {
        this.continutReview = continutReview;
        return this;
    }

    public void setContinutReview(String continutReview) {
        this.continutReview = continutReview;
    }

    public LocalDate getDataReview() {
        return dataReview;
    }

    public Review dataReview(LocalDate dataReview) {
        this.dataReview = dataReview;
        return this;
    }

    public void setDataReview(LocalDate dataReview) {
        this.dataReview = dataReview;
    }

    public Integer getNumarLikeuri() {
        return numarLikeuri;
    }

    public Review numarLikeuri(Integer numarLikeuri) {
        this.numarLikeuri = numarLikeuri;
        return this;
    }

    public void setNumarLikeuri(Integer numarLikeuri) {
        this.numarLikeuri = numarLikeuri;
    }

    public Integer getNumarDislikeuri() {
        return numarDislikeuri;
    }

    public Review numarDislikeuri(Integer numarDislikeuri) {
        this.numarDislikeuri = numarDislikeuri;
        return this;
    }

    public void setNumarDislikeuri(Integer numarDislikeuri) {
        this.numarDislikeuri = numarDislikeuri;
    }

    public Boolean isModerat() {
        return moderat;
    }

    public Review moderat(Boolean moderat) {
        this.moderat = moderat;
        return this;
    }

    public void setModerat(Boolean moderat) {
        this.moderat = moderat;
    }

    public User getUser() {
        return user;
    }

    public Review user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Review categorie(Categorie categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Review review = (Review) o;
        if (review.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), review.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Review{" +
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
