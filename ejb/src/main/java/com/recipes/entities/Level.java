/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recipes.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author archie
 */
@Data
@Builder
@Entity
@Table(name = "Level")
@NamedQueries({
    @NamedQuery(name = "Level.findAll", query = "SELECT l FROM Level l")
    , @NamedQuery(name = "Level.findById", query = "SELECT l FROM Level l WHERE l.id = :id")
    , @NamedQuery(name = "Level.findByName", query = "SELECT l FROM Level l WHERE l.name = :name")})
public class Level implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "levelId")
    private List<Recipe> recipeList;

    @Tolerate
    public Level(){

    }
}
