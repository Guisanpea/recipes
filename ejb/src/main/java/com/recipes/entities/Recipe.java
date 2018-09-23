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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author archie
 */
@Data
@Builder
@Entity
@Table(name = "Recipe")
@NamedQueries({
        @NamedQuery(name = "Recipe.findAll", query = "SELECT r FROM Recipe r")
        , @NamedQuery(name = "Recipe.findById", query = "SELECT r FROM Recipe r WHERE r.id = :id")
        , @NamedQuery(name = "Recipe.findByName", query = "SELECT r FROM Recipe r WHERE r.name = :name")
        , @NamedQuery(name = "Recipe.findByDescription", query = "SELECT r FROM Recipe r WHERE r.description = :description")
        , @NamedQuery(name = "Recipe.findByPreparation", query = "SELECT r FROM Recipe r WHERE r.preparation = :preparation")
        , @NamedQuery(name = "Recipe.findByPublic1", query = "SELECT r FROM Recipe r WHERE r.bPublic = :public1")
        , @NamedQuery(name = "Recipe.findByUser", query = "SELECT r FROM Recipe r WHERE r.userId = :user")
        , @NamedQuery(name = "Recipe.findByCreatedAt", query = "SELECT r FROM Recipe r WHERE r.createdAt = :createdAt")})
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 1000)
    @Column(name = "description")
    private String description;
    @Size(max = 1000)
    @Column(name = "preparation")
    private String preparation;
    @Column(name = "public")
    private Boolean bPublic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToMany(mappedBy = "recipeList")
    private List<Ingredient> ingredientList;
    @ManyToMany(mappedBy = "recipeList")
    private List<Category> categoryList;
    @JoinColumn(name = "levelId", referencedColumnName = "id")
    @ManyToOne
    private Level levelId;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    @Tolerate
    public Recipe() {

    }
}
