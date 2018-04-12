/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "plan_mejoramiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanMejoramiento.findAll", query = "SELECT p FROM PlanMejoramiento p")
    , @NamedQuery(name = "PlanMejoramiento.findById", query = "SELECT p FROM PlanMejoramiento p WHERE p.id = :id")
    , @NamedQuery(name = "PlanMejoramiento.findByCalificacion", query = "SELECT p FROM PlanMejoramiento p WHERE p.calificacion = :calificacion")
    , @NamedQuery(name = "PlanMejoramiento.findByTipoError", query = "SELECT p FROM PlanMejoramiento p WHERE p.tipoError = :tipoError")
    , @NamedQuery(name = "PlanMejoramiento.findByIsSolucionado", query = "SELECT p FROM PlanMejoramiento p WHERE p.isSolucionado = :isSolucionado")})
public class PlanMejoramiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "calificacion")
    private String calificacion;
    @Column(name = "tipo_error")
    private String tipoError;
    @Column(name = "is_solucionado")
    private int isSolucionado;
    @JoinColumn(name = "usuarios_final_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuariosFinalId;
    @JoinColumn(name = "usuarios_soporte_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuariosSoporteId;

    public PlanMejoramiento() {
    }

    public PlanMejoramiento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getTipoError() {
        return tipoError;
    }

    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }

    public int getIsSolucionado() {
        return isSolucionado;
    }

    public void setIsSolucionado(int isSolucionado) {
        this.isSolucionado = isSolucionado;
    }

    public Usuario getUsuariosFinalId() {
        return usuariosFinalId;
    }

    public void setUsuariosFinalId(Usuario usuariosFinalId) {
        this.usuariosFinalId = usuariosFinalId;
    }

    public Usuario getUsuariosSoporteId() {
        return usuariosSoporteId;
    }

    public void setUsuariosSoporteId(Usuario usuariosSoporteId) {
        this.usuariosSoporteId = usuariosSoporteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanMejoramiento)) {
            return false;
        }
        PlanMejoramiento other = (PlanMejoramiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "indefensos.modelo.entidades.PlanMejoramiento[ id=" + id + " ]";
    }
    
}
