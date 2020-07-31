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
@Table(name = "rh_departamento", catalog = "db_rrhh", schema = "")
@NamedQueries({
    @NamedQuery(name = "RhDepartamento.findAll", query = "SELECT r FROM RhDepartamento r")})
public class RhDepartamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DEP_ID", nullable = false)
    private Integer depId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DEP_NOMBRE", nullable = false, length = 45)
    private String depNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "depId")
    private Collection<RhEmpleado> rhEmpleadoCollection;

    public RhDepartamento() {
    }

    public RhDepartamento(Integer depId) {
        this.depId = depId;
    }

    public RhDepartamento(Integer depId, String depNombre) {
        this.depId = depId;
        this.depNombre = depNombre;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepNombre() {
        return depNombre;
    }

    public void setDepNombre(String depNombre) {
        this.depNombre = depNombre;
    }

    public Collection<RhEmpleado> getRhEmpleadoCollection() {
        return rhEmpleadoCollection;
    }

    public void setRhEmpleadoCollection(Collection<RhEmpleado> rhEmpleadoCollection) {
        this.rhEmpleadoCollection = rhEmpleadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depId != null ? depId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhDepartamento)) {
            return false;
        }
        RhDepartamento other = (RhDepartamento) object;
        if ((this.depId == null && other.depId != null) || (this.depId != null && !this.depId.equals(other.depId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rrhh.entity.RhDepartamento[ depId=" + depId + " ]";
    }
    
}
