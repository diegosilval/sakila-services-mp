/*
 * Copyright 2020 Diego Silva Limaco <diego.silva at apuntesdejava.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
@Table(name = "film_category")
public class FilmCategory implements Serializable {
    @EmbeddedId
    private FilmCategoryPK filmCategoryPK;
    
    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    private Film film;
    
    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;
    
     @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public FilmCategoryPK getFilmCategoryPK() {
        return filmCategoryPK;
    }

    public void setFilmCategoryPK(FilmCategoryPK filmCategoryPK) {
        this.filmCategoryPK = filmCategoryPK;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getLastDate() {
        return lastUpdate;
    }

    public void setLastDate(LocalDateTime lastDate) {
        this.lastUpdate = lastDate;
    }
     
     
}
