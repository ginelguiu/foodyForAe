package org.foody.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

public class ComentariuDTO implements Serializable {

    private Long id;

    @NotNull
    private String continutComentariu;

    private LocalDate dataComentariu;

    private Long reviewId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContinutComentariu() {
        return continutComentariu;
    }

    public void setContinutComentariu(String continutComentariu) {
        this.continutComentariu = continutComentariu;
    }

    public LocalDate getDataComentariu() {
        return dataComentariu;
    }

    public void setDataComentariu(LocalDate dataComentariu) {
        this.dataComentariu = dataComentariu;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComentariuDTO comentariuDTO = (ComentariuDTO) o;
        if(comentariuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comentariuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComentariuDTO{" +
            "id=" + getId() +
            ", continutComentariu='" + getContinutComentariu() + "'" +
            ", dataComentariu='" + getDataComentariu() + "'" +
            "}";
    }
}
