package org.foody.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "comentariu")
public class Comentariu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "continut_comentariu", nullable = false)
    private String continutComentariu;

    @Column(name = "data_comentariu")
    private LocalDate dataComentariu;

    @ManyToOne
    private Review review;

    @ManyToOne
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContinutComentariu() {
        return continutComentariu;
    }

    public Comentariu continutComentariu(String continutComentariu) {
        this.continutComentariu = continutComentariu;
        return this;
    }

    public void setContinutComentariu(String continutComentariu) {
        this.continutComentariu = continutComentariu;
    }

    public LocalDate getDataComentariu() {
        return dataComentariu;
    }

    public Comentariu dataComentariu(LocalDate dataComentariu) {
        this.dataComentariu = dataComentariu;
        return this;
    }

    public void setDataComentariu(LocalDate dataComentariu) {
        this.dataComentariu = dataComentariu;
    }

    public Review getReview() {
        return review;
    }

    public Comentariu review(Review review) {
        this.review = review;
        return this;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public Comentariu user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comentariu comentariu = (Comentariu) o;
        if (comentariu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comentariu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Comentariu{" +
            "id=" + getId() +
            ", continutComentariu='" + getContinutComentariu() + "'" +
            ", dataComentariu='" + getDataComentariu() + "'" +
            "}";
    }
}
