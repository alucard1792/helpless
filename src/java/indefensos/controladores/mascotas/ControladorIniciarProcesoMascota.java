/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.mascotas;

import indefensos.modelo.dao.EstadoMascotaFacade;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.dao.ProcesoFacade;
import indefensos.modelo.entidades.EstadoMascota;
import indefensos.modelo.entidades.Mascota;
import indefensos.modelo.entidades.Proceso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorIniciarProcesoMascota")
@ViewScoped
public class ControladorIniciarProcesoMascota implements Serializable {

    @EJB
    private MascotaFacade mascotaFacade;
    @EJB
    private EstadoMascotaFacade estadoMascotaFacade;
    @EJB
    private ProcesoFacade procesoFacade;
    @Inject
    private Conversation conversation;
    private List<EstadoMascota> listaEstadosMascotas;
    private Mascota mascotaSeleccionada;
    private Proceso proceso;

    public ControladorIniciarProcesoMascota() {
    }

    @PostConstruct
    public void init() {
        proceso = new Proceso();

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

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public void prepararProceso(Mascota m) {
        System.out.println(m.getNombre());
        mascotaSeleccionada = m;

    }

    public String crear() {
        mascotaSeleccionada.setHasProceso(1);
        if (proceso.getTipoProceso().equals("extraviado")) {
            mascotaSeleccionada.setEstadoMascotasId(new EstadoMascota(2));

        } else if (proceso.getTipoProceso().equals("adopcion")) {
            mascotaSeleccionada.setEstadoMascotasId(new EstadoMascota(3));

        }
        mascotaFacade.edit(mascotaSeleccionada);
        proceso.setMascotasId(mascotaSeleccionada);
        proceso.setFechaProceso(new Date());
        proceso.setMascotasId(mascotaSeleccionada);
        proceso.setIsAutorizado(0);
        procesoFacade.create(proceso);
        return "/core/mascota/listarMascotas.xhtml?faces-redirect=true";

    }

}
