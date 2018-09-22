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
import java.util.Date;
import java.util.List;

/**
 * @author archie
 */
@Data
@Builder
@Entity
@Table(name = "User")
@NamedQueries({
      @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
      , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
      , @NamedQuery(name = "User.findByFullName", query = "SELECT u FROM User u WHERE u.fullName = :fullName")
      , @NamedQuery(name = "User.findByBirthdate", query = "SELECT u FROM User u WHERE u.birthdate = :birthdate")
      , @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name")
      , @NamedQuery(name = "User.findByHashedPassword", query = "SELECT u FROM User u WHERE u.hashedPassword = :hashedPassword")
      , @NamedQuery(name = "User.findByLastRecipeAdded", query = "SELECT u FROM User u WHERE u.lastRecipeAdded = :lastRecipeAdded")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 64)
    @Column(name = "hashedPassword")
    private String hashedPassword;
    @Column(name = "lastRecipeAdded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRecipeAdded;
    @JoinColumn(name = "experienceId", referencedColumnName = "id")
    @ManyToOne
    private Experience experienceId;
    @OneToMany(mappedBy = "userId")
    private List<Recipe> recipeList;

    @Tolerate
    public User() {

    }
}
