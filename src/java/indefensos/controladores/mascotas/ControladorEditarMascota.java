/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.mascotas;

import indefensos.modelo.dao.CiudadFacade;
import indefensos.modelo.dao.EstadoMascotaFacade;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.entidades.Ciudad;
import indefensos.modelo.entidades.EstadoMascota;
import indefensos.modelo.entidades.Mascota;
import indefensos.modelo.entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorEditarMascota")
@ConversationScoped
public class ControladorEditarMascota implements Serializable {

    @EJB
    private MascotaFacade mascotaFacade;
    @EJB
    private EstadoMascotaFacade estadoMascotaFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    @Inject
    private Conversation conversation;
    private List<EstadoMascota> listaEstadosMascotas;
    private List<Ciudad> listaCiudades;
    private Mascota mascotaSeleccionada;

    public ControladorEditarMascota() {
    }

    @PostConstruct
    public void init() {
        listaCiudades = ciudadFacade.findAll();
        listaEstadosMascotas = estadoMascotaFacade.findAll();
    }

    public List<EstadoMascota> getListaEstadosMascotas() {
        return listaEstadosMascotas;
    }

    public void setListaEstadosMascotas(List<EstadoMascota> listaEstadosMascotas) {
        this.listaEstadosMascotas = listaEstadosMascotas;
    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public Mascota getMascotaSeleccionada() {
        return mascotaSeleccionada;
    }

    public void setMascotaSeleccionada(Mascota mascotaSeleccionada) {
        this.mascotaSeleccionada = mascotaSeleccionada;
    }



    public void iniciarConversacion() {
        if (conversation.isTransient()) {
            conversation.begin();
        }

    }

    public void terminarConversacion() {
        if (!conversation.isTransient()) {
            conversation.end();
        }

    }

    public String cancelar() {
        terminarConversacion();
        return "/core/mascota/listarMascotas.xhtml?faces-redirect=true";

    }

    public String prepararEditar(Mascota m) {
        iniciarConversacion();
        mascotaSeleccionada = m;
        return "/core/mascota/editarMascota.xhtml?faces-redirect=true";

    }

    public String editar() {
        mascotaFacade.edit(mascotaSeleccionada);
        return cancelar();

    }

}
