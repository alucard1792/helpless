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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres")
    , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuario.findByDocumento", query = "SELECT u FROM Usuario u WHERE u.documento = :documento")
    , @NamedQuery(name = "Usuario.findByFechaNacimiento", query = "SELECT u FROM Usuario u WHERE u.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Usuario.findByFechaRegistro", query = "SELECT u FROM Usuario u WHERE u.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "documento")
    private Integer documento;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "email")
    private String email;
    @Column(name = "clave")
    private String clave;
    @Column(name = "estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosId", fetch = FetchType.EAGER)
    private List<Cita> citaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosFinalId", fetch = FetchType.EAGER)
    private List<PlanMejoramiento> planMejoramientoList;
    @OneToMany(mappedBy = "usuariosSoporteId", fetch = FetchType.EAGER)
    private List<PlanMejoramiento> planMejoramientoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "due\u00f1oId", fetch = FetchType.EAGER)
    private List<Mascota> mascotaList;
    @JoinColumn(name = "ciudades_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ciudad ciudadesId;
    @JoinColumn(name = "roles_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rol rolesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosId", fetch = FetchType.EAGER)
    private List<Proceso> procesoList;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    @XmlTransient
    public List<PlanMejoramiento> getPlanMejoramientoList() {
        return planMejoramientoList;
    }

    public void setPlanMejoramientoList(List<PlanMejoramiento> planMejoramientoList) {
        this.planMejoramientoList = planMejoramientoList;
    }

    @XmlTransient
    public List<PlanMejoramiento> getPlanMejoramientoList1() {
        return planMejoramientoList1;
    }

    public void setPlanMejoramientoList1(List<PlanMejoramiento> planMejoramientoList1) {
        this.planMejoramientoList1 = planMejoramientoList1;
    }

    @XmlTransient
    public List<Mascota> getMascotaList() {
        return mascotaList;
    }

    public void setMascotaList(List<Mascota> mascotaList) {
        this.mascotaList = mascotaList;
    }

    public Ciudad getCiudadesId() {
        return ciudadesId;
    }

    public void setCiudadesId(Ciudad ciudadesId) {
        this.ciudadesId = ciudadesId;
    }

    public Rol getRolesId() {
        return rolesId;
    }

    public void setRolesId(Rol rolesId) {
        this.rolesId = rolesId;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "indefensos.modelo.entidades.Usuario[ id=" + id + " ]";
    }
    
}
