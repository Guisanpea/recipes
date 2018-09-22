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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author archie
 */
@Data
@Builder
@Entity
@Table(name = "Category")
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
        , @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id")
        , @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name")})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "RecipeHasCategory", joinColumns = {
            @JoinColumn(name = "categoryId", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "recipeId", referencedColumnName = "id")})
    @ManyToMany
    private List<Recipe> recipeList;

    @Tolerate
    public Category() {

    }
}
