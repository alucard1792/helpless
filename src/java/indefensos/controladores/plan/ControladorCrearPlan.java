/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.plan;

import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.PlanMejoramientoFacade;
import indefensos.modelo.dao.UsuarioFacade;
import indefensos.modelo.entidades.PlanMejoramiento;
import indefensos.modelo.entidades.Usuario;
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
@Named(value = "controladorCrearPlan")
@ViewScoped
public class ControladorCrearPlan implements Serializable {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private PlanMejoramientoFacade planMejoramientoFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private List<Usuario> listaUsuarios;
    private PlanMejoramiento planMejoramiento;
    
    public ControladorCrearPlan() {
    }
    
    @PostConstruct
    public void init() {
        listaUsuarios = usuarioFacade.findAll();
        planMejoramiento = new PlanMejoramiento();
        
    }
    
    public PlanMejoramiento getPlanMejoramiento() {
        return planMejoramiento;
    }
    
    public void setPlanMejoramiento(PlanMejoramiento planMejoramiento) {
        this.planMejoramiento = planMejoramiento;
    }
    
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public String crearSolicitud() {
        planMejoramiento.setIsSolucionado(0);
        planMejoramiento.setUsuariosFinalId(controladorLogin.getUsuarioSesion());
        planMejoramientoFacade.create(planMejoramiento);
        return "/core/plan/crearSolicitud.xhtml?faces-redirect=true";
        
    }
    
}
