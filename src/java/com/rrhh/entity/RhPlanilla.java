/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Maury
 */
@Entity
@Table(name = "rh_planilla", catalog = "db_rrhh", schema = "")
@NamedQueries({
    @NamedQuery(name = "RhPlanilla.findAll", query = "SELECT r FROM RhPlanilla r")})
public class RhPlanilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PLN_ID", nullable = false)
    private Integer plnId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLN_FECHA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date plnFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLN_TOTAL", nullable = false)
    private double plnTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLN_ESTADO", nullable = false)
    private int plnEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plnId")
    private Collection<RhDetallePlanilla> rhDetallePlanillaCollection;

    public RhPlanilla() {
    }

    public RhPlanilla(Integer plnId) {
        this.plnId = plnId;
    }

    public RhPlanilla(Integer plnId, Date plnFecha, double plnTotal, int plnEstado) {
        this.plnId = plnId;
        this.plnFecha = plnFecha;
        this.plnTotal = plnTotal;
        this.plnEstado = plnEstado;
    }

    public Integer getPlnId() {
        return plnId;
    }

    public void setPlnId(Integer plnId) {
        this.plnId = plnId;
    }

    public Date getPlnFecha() {
        return plnFecha;
    }

    public void setPlnFecha(Date plnFecha) {
        this.plnFecha = plnFecha;
    }

    public double getPlnTotal() {
        return plnTotal;
    }

    public void setPlnTotal(double plnTotal) {
        this.plnTotal = plnTotal;
    }

    public int getPlnEstado() {
        return plnEstado;
    }

    public void setPlnEstado(int plnEstado) {
        this.plnEstado = plnEstado;
    }

    public Collection<RhDetallePlanilla> getRhDetallePlanillaCollection() {
        return rhDetallePlanillaCollection;
    }

    public void setRhDetallePlanillaCollection(Collection<RhDetallePlanilla> rhDetallePlanillaCollection) {
        this.rhDetallePlanillaCollection = rhDetallePlanillaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plnId != null ? plnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhPlanilla)) {
            return false;
        }
        RhPlanilla other = (RhPlanilla) object;
        if ((this.plnId == null && other.plnId != null) || (this.plnId != null && !this.plnId.equals(other.plnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rrhh.entity.RhPlanilla[ plnId=" + plnId + " ]";
    }
    
}
