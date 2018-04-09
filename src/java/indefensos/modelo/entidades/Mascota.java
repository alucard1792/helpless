/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "mascotas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mascota.findAll", query = "SELECT m FROM Mascota m")
    , @NamedQuery(name = "Mascota.findById", query = "SELECT m FROM Mascota m WHERE m.id = :id")
    , @NamedQuery(name = "Mascota.findByNombre", query = "SELECT m FROM Mascota m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Mascota.findByEdad", query = "SELECT m FROM Mascota m WHERE m.edad = :edad")
    , @NamedQuery(name = "Mascota.findByDescripcion", query = "SELECT m FROM Mascota m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Mascota.findByFechaRegistro", query = "SELECT m FROM Mascota m WHERE m.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Mascota.findByFechaPerdida", query = "SELECT m FROM Mascota m WHERE m.fechaPerdida = :fechaPerdida")
    , @NamedQuery(name = "Mascota.findByFechaAdopcion", query = "SELECT m FROM Mascota m WHERE m.fechaAdopcion = :fechaAdopcion")
    , @NamedQuery(name = "Mascota.findByTipoMascota", query = "SELECT m FROM Mascota m WHERE m.tipoMascota = :tipoMascota")})
public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "edad")
    private Integer edad;
    @Lob
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "fecha_perdida")
    private String fechaPerdida;
    @Column(name = "fecha_adopcion")
    private String fechaAdopcion;
    @Basic(optional = false)
    @Column(name = "tipo_mascota")
    private String tipoMascota;
    @JoinColumn(name = "ciudades_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ciudad ciudadesId;
    @JoinColumn(name = "estado_mascotas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadoMascota estadoMascotasId;
    @JoinColumn(name = "due\u00f1o_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario dueñoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascotasId", fetch = FetchType.EAGER)
    private List<Proceso> procesoList;

    public Mascota() {
    }

    public Mascota(Integer id) {
        this.id = id;
    }

    public Mascota(Integer id, String tipoMascota) {
        this.id = id;
        this.tipoMascota = tipoMascota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaPerdida() {
        return fechaPerdida;
    }

    public void setFechaPerdida(String fechaPerdida) {
        this.fechaPerdida = fechaPerdida;
    }

    public String getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(String fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public Ciudad getCiudadesId() {
        return ciudadesId;
    }

    public void setCiudadesId(Ciudad ciudadesId) {
        this.ciudadesId = ciudadesId;
    }

    public EstadoMascota getEstadoMascotasId() {
        return estadoMascotasId;
    }

    public void setEstadoMascotasId(EstadoMascota estadoMascotasId) {
        this.estadoMascotasId = estadoMascotasId;
    }

    public Usuario getDueñoId() {
        return dueñoId;
    }

    public void setDueñoId(Usuario dueñoId) {
        this.dueñoId = dueñoId;
    }

    @XmlTransient
    public List<Proceso> getProcesoList() {
        return procesoList;
    }

    public void setProcesoList(List<Proceso> procesoList) {
        this.procesoList = procesoList;
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
        if (!(object instanceof Mascota)) {
            return false;
        }
        Mascota other = (Mascota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "indefensos.modelo.entidades.Mascota[ id=" + id + " ]";
    }
    
}
