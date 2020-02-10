/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.sakila.services.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "film_actor")
public class FilmActor implements Serializable {

    @EmbeddedId
    private FilmActorPK filmActorPK;

    @ManyToOne
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public FilmActorPK getFilmActorPK() {
        return filmActorPK;
    }

    public void setFilmActorPK(FilmActorPK filmActorPK) {
        this.filmActorPK = filmActorPK;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public LocalDateTime getLastDate() {
        return lastUpdate;
    }

    public void setLastDate(LocalDateTime lastDate) {
        this.lastUpdate = lastDate;
    }

}
