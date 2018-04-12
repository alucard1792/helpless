/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.plan;

import indefensos.controladores.usuarios.*;
import indefensos.modelo.dao.CiudadFacade;
import indefensos.modelo.dao.PlanMejoramientoFacade;
import indefensos.modelo.dao.RolFacade;
import indefensos.modelo.dao.UsuarioFacade;
import indefensos.modelo.entidades.Ciudad;
import indefensos.modelo.entidades.PlanMejoramiento;
import indefensos.modelo.entidades.Rol;
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
@Named(value = "controladorEditarPlan")
@ConversationScoped
public class ControladorEditarPlan implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private PlanMejoramientoFacade planMejoramientoFacade;
    @Inject
    private Conversation conversation;
    private List<Usuario> listaUsuarios;
    private PlanMejoramiento planSeleccionado;

    public ControladorEditarPlan() {
    }

    @PostConstruct
    public void init() {
        listaUsuarios = usuarioFacade.listarUsuariosRol(new Rol(6));

    }

    public PlanMejoramiento getPlanSeleccionado() {
        return planSeleccionado;
    }

    public void setPlanSeleccionado(PlanMejoramiento planSeleccionado) {
        this.planSeleccionado = planSeleccionado;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
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
        return "/core/plan/listarSolicitudesAsignarPersonalSoporte.xhtml?faces-redirect=true";

    }

    public String prepararEditar(PlanMejoramiento pm) {
        iniciarConversacion();
        planSeleccionado = pm;
        return "/core/plan/asignarPersonalSoporte.xhtml?faces-redirect=true";

    }
    
    public String prepararEditarCalificacion(PlanMejoramiento pm) {
        iniciarConversacion();
        planSeleccionado = pm;
        return "/core/plan/asignarCalificacion.xhtml?faces-redirect=true";

    }

    public String editar() {
        planMejoramientoFacade.edit(planSeleccionado);
        return cancelar();

    }

    public String editarUsuarioSoporte(PlanMejoramiento pm) {
        pm.setIsSolucionado(1);
        planMejoramientoFacade.edit(pm);
        terminarConversacion();
        return "";

    }
    
    public String editarUsuarioFinalRespuesta() {
        planMejoramientoFacade.edit(planSeleccionado);
        terminarConversacion();
        return "/core/plan/listarSolicitudesUsuarioFinal.xhtml";

    }

}
