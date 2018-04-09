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
@Table(name = "citas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c")
    , @NamedQuery(name = "Cita.findById", query = "SELECT c FROM Cita c WHERE c.id = :id")
    , @NamedQuery(name = "Cita.findByAsunto", query = "SELECT c FROM Cita c WHERE c.asunto = :asunto")
    , @NamedQuery(name = "Cita.findByIsLeida", query = "SELECT c FROM Cita c WHERE c.isLeida = :isLeida")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Asunto")
    private String asunto;
    @Basic(optional = false)
    @Column(name = "is_leida")
    private String isLeida;
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuariosId;

    public Cita() {
    }

    public Cita(Integer id) {
        this.id = id;
    }

    public Cita(Integer id, String asunto, String isLeida) {
        this.id = id;
        this.asunto = asunto;
        this.isLeida = isLeida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getIsLeida() {
        return isLeida;
    }

    public void setIsLeida(String isLeida) {
        this.isLeida = isLeida;
    }

    public Usuario getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(Usuario usuariosId) {
        this.usuariosId = usuariosId;
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
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "indefensos.modelo.entidades.Cita[ id=" + id + " ]";
    }
    
}
