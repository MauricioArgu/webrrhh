/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rrhh.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Maury
 */
@Entity
@Table(name = "rh_detalle_planilla", catalog = "db_rrhh", schema = "")
@NamedQueries({
    @NamedQuery(name = "RhDetallePlanilla.findAll", query = "SELECT r FROM RhDetallePlanilla r")})
public class RhDetallePlanilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DET_PLN_ID", nullable = false)
    private Integer detPlnId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_PLN_TOTAL", nullable = false)
    private double detPlnTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_PLN_TOTAL_DESCUENTOS", nullable = false)
    private double detPlnTotalDescuentos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_PLN_RENTA", nullable = false)
    private double detPlnRenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_PLN_ISSS", nullable = false)
    private double detPlnIsss;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_PLN_AFP", nullable = false)
    private double detPlnAfp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_PLN_CANTIDAD_HORAS_DIURNAS", nullable = false)
    private int detPlnCantidadHorasDiurnas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_PLN_BONO_HORAS_EXTRA", nullable = false)
    private double detPlnBonoHorasExtra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DET_PLN_CANTIDAD_HORAS_NOCTURNAS", nullable = false)
    private int detPlnCantidadHorasNocturnas;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID", nullable = false)
    @ManyToOne(optional = false)
    private RhEmpleado empId;
    @JoinColumn(name = "PLN_ID", referencedColumnName = "PLN_ID", nullable = false)
    @ManyToOne(optional = false)
    private RhPlanilla plnId;

    public RhDetallePlanilla() {
    }

    public RhDetallePlanilla(Integer detPlnId) {
        this.detPlnId = detPlnId;
    }

    public RhDetallePlanilla(Integer detPlnId, double detPlnTotal, double detPlnTotalDescuentos, double detPlnRenta, double detPlnIsss, double detPlnAfp, int detPlnCantidadHorasDiurnas, double detPlnBonoHorasExtra, int detPlnCantidadHorasNocturnas) {
        this.detPlnId = detPlnId;
        this.detPlnTotal = detPlnTotal;
        this.detPlnTotalDescuentos = detPlnTotalDescuentos;
        this.detPlnRenta = detPlnRenta;
        this.detPlnIsss = detPlnIsss;
        this.detPlnAfp = detPlnAfp;
        this.detPlnCantidadHorasDiurnas = detPlnCantidadHorasDiurnas;
        this.detPlnBonoHorasExtra = detPlnBonoHorasExtra;
        this.detPlnCantidadHorasNocturnas = detPlnCantidadHorasNocturnas;
    }

    public Integer getDetPlnId() {
        return detPlnId;
    }

    public void setDetPlnId(Integer detPlnId) {
        this.detPlnId = detPlnId;
    }

    public double getDetPlnTotal() {
        return detPlnTotal;
    }

    public void setDetPlnTotal(double detPlnTotal) {
        this.detPlnTotal = detPlnTotal;
    }

    public double getDetPlnTotalDescuentos() {
        return detPlnTotalDescuentos;
    }

    public void setDetPlnTotalDescuentos(double detPlnTotalDescuentos) {
        this.detPlnTotalDescuentos = detPlnTotalDescuentos;
    }

    public double getDetPlnRenta() {
        return detPlnRenta;
    }

    public void setDetPlnRenta(double detPlnRenta) {
        this.detPlnRenta = detPlnRenta;
    }

    public double getDetPlnIsss() {
        return detPlnIsss;
    }

    public void setDetPlnIsss(double detPlnIsss) {
        this.detPlnIsss = detPlnIsss;
    }

    public double getDetPlnAfp() {
        return detPlnAfp;
    }

    public void setDetPlnAfp(double detPlnAfp) {
        this.detPlnAfp = detPlnAfp;
    }

    public int getDetPlnCantidadHorasDiurnas() {
        return detPlnCantidadHorasDiurnas;
    }

    public void setDetPlnCantidadHorasDiurnas(int detPlnCantidadHorasDiurnas) {
        this.detPlnCantidadHorasDiurnas = detPlnCantidadHorasDiurnas;
    }

    public double getDetPlnBonoHorasExtra() {
        return detPlnBonoHorasExtra;
    }

    public void setDetPlnBonoHorasExtra(double detPlnBonoHorasExtra) {
        this.detPlnBonoHorasExtra = detPlnBonoHorasExtra;
    }

    public int getDetPlnCantidadHorasNocturnas() {
        return detPlnCantidadHorasNocturnas;
    }

    public void setDetPlnCantidadHorasNocturnas(int detPlnCantidadHorasNocturnas) {
        this.detPlnCantidadHorasNocturnas = detPlnCantidadHorasNocturnas;
    }

    public RhEmpleado getEmpId() {
        return empId;
    }

    public void setEmpId(RhEmpleado empId) {
        this.empId = empId;
    }

    public RhPlanilla getPlnId() {
        return plnId;
    }

    public void setPlnId(RhPlanilla plnId) {
        this.plnId = plnId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detPlnId != null ? detPlnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhDetallePlanilla)) {
            return false;
        }
        RhDetallePlanilla other = (RhDetallePlanilla) object;
        if ((this.detPlnId == null && other.detPlnId != null) || (this.detPlnId != null && !this.detPlnId.equals(other.detPlnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rrhh.entity.RhDetallePlanilla[ detPlnId=" + detPlnId + " ]";
    }
    
}
