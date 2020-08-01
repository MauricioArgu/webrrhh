/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Maury
 */
@Entity
@Table(name = "rh_rol", catalog = "db_rrhh", schema = "")
@NamedQueries({
    @NamedQuery(name = "RhRol.findAll", query = "SELECT r FROM RhRol r")})
public class RhRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROL_ID", nullable = false)
    private Integer rolId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ROL_NOMBRE", nullable = false, length = 45)
    private String rolNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolId")
    private Collection<RhUsuario> rhUsuarioCollection;

    public RhRol() {
    }

    public RhRol(Integer rolId) {
        this.rolId = rolId;
    }

    public RhRol(Integer rolId, String rolNombre) {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Collection<RhUsuario> getRhUsuarioCollection() {
        return rhUsuarioCollection;
    }

    public void setRhUsuarioCollection(Collection<RhUsuario> rhUsuarioCollection) {
        this.rhUsuarioCollection = rhUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhRol)) {
            return false;
        }
        RhRol other = (RhRol) object;
        if ((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RhRol{" + "rolId=" + rolId + ", rolNombre=" + rolNombre + ", rhUsuarioCollection=" + rhUsuarioCollection + '}';
    }

    
    
}
