/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.mascotas;

import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.CiudadFacade;
import indefensos.modelo.dao.EstadoMascotaFacade;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.entidades.Ciudad;
import indefensos.modelo.entidades.EstadoMascota;
import indefensos.modelo.entidades.Mascota;
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
@Named(value = "controladorCrearMascota")
@ViewScoped
public class ControladorCrearMascota implements Serializable {
    
    @EJB
    private MascotaFacade mascotaFacade;
    @EJB
    private EstadoMascotaFacade estadoMascotaFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private List<EstadoMascota> listaEstadosMascotas;
    private List<Ciudad> listaCiudades;
    private Mascota mascota;
    
    public ControladorCrearMascota() {
    }
    
    @PostConstruct
    public void init() {
        listaCiudades = ciudadFacade.findAll();
        listaEstadosMascotas = estadoMascotaFacade.findAll();
        mascota = new Mascota();
        
    }
    
    public List<EstadoMascota> getListaEstadosMascotas() {
        return listaEstadosMascotas;
    }
    
    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }
    
    public Mascota getMascota() {
        return mascota;
    }
    
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
    
    public String crear() {
        mascota.setFechaRegistro(new Date());
        mascota.setDueñoId(controladorLogin.getUsuarioSesion());
        mascota.setHasProceso(0);
        mascotaFacade.create(mascota);
        return "/core/mascota/listarMascotas.xhtml?faces-redirect=true";
        
    }
    
}
