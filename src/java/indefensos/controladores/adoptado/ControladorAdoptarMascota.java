/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.adoptado;

import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.EstadoMascotaFacade;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.dao.ProcesoFacade;
import indefensos.modelo.entidades.EstadoMascota;
import indefensos.modelo.entidades.Mascota;
import indefensos.modelo.entidades.Proceso;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorAdoptarMascota")
@ViewScoped
public class ControladorAdoptarMascota implements Serializable {
    
    @EJB
    private MascotaFacade mascotaFacade;
    @EJB
    private EstadoMascotaFacade estadoMascotaFacade;
    @EJB
    private ProcesoFacade procesoFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private List<EstadoMascota> listaEstadosMascotas;
    private Mascota mascotaSeleccionada;
    private Proceso procesoSeleccionado;
    
    public ControladorAdoptarMascota() {
    }
    
    @PostConstruct
    public void init() {
        
    }
    
    public List<EstadoMascota> getListaEstadosMascotas() {
        return listaEstadosMascotas;
    }
    
    public void setListaEstadosMascotas(List<EstadoMascota> listaEstadosMascotas) {
        this.listaEstadosMascotas = listaEstadosMascotas;
    }
    
    public Mascota getMascotaSeleccionada() {
        return mascotaSeleccionada;
    }
    
    public void setMascotaSeleccionada(Mascota mascotaSeleccionada) {
        this.mascotaSeleccionada = mascotaSeleccionada;
    }
    
    public Proceso getProcesoSeleccionado() {
        return procesoSeleccionado;
    }
    
    public void setProcesoSeleccionado(Proceso procesoSeleccionado) {
        this.procesoSeleccionado = procesoSeleccionado;
    }
    
    public void prepararProceso(Proceso p) {
        procesoSeleccionado = p;
        
    }
    
    public String iniciarAdopcion() {
        procesoSeleccionado.setUsuariosId(controladorLogin.getUsuarioSesion());
        procesoFacade.edit(procesoSeleccionado);
        return "/core/adopcion/listarAdoptados.xhtml?faces-redirect=true";
        
    }
    
    public String aprobarAdopcion() {
        procesoSeleccionado.setIsAutorizado(1);
        Mascota m = procesoSeleccionado.getMascotasId();
        m.setDueñoId(procesoSeleccionado.getUsuariosId());
        m.setEstadoMascotasId(new EstadoMascota(1));
        procesoFacade.edit(procesoSeleccionado);
        mascotaFacade.edit(m);
        return "/core/adopcion/listarAdoptados.xhtml?faces-redirect=true";
        
    }
    
}
