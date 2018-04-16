/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.extraviados;

import indefensos.controladores.adoptado.*;
import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.EstadoMascotaFacade;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.dao.ProcesoFacade;
import indefensos.modelo.entidades.EstadoMascota;
import indefensos.modelo.entidades.Mascota;
import indefensos.modelo.entidades.Proceso;
import indefensos.modelo.entidades.Rol;
import java.io.Serializable;
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
@Named(value = "controladorValidarExtraviado")
@ViewScoped
public class ControladorValidarExtraviado implements Serializable {

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
    private int respuesta;

    public ControladorValidarExtraviado() {
    }

    @PostConstruct
    public void init() {

    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
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

    public String validarInformacion() {
        if (respuesta == 1) {
            procesoSeleccionado.setIsAutorizado(1);
            Mascota m = procesoSeleccionado.getMascotasId();
            m.setEstadoMascotasId(new EstadoMascota(1));
            procesoSeleccionado.setIsAutorizado(1);
            procesoFacade.edit(procesoSeleccionado);
            mascotaFacade.edit(m);
            return "/core/extraviados/listarMisMascotasExtraviadas.xhtml?faces-redirect=true";

        } else if (respuesta == 0) {
            procesoSeleccionado.setUsuariosId(null);
            procesoSeleccionado.setRespuesta(" ");
            procesoFacade.edit(procesoSeleccionado);
            return "/core/extraviados/listarMisMascotasExtraviadas.xhtml?faces-redirect=true";

        } else {
            return "";

        }

    }

}
