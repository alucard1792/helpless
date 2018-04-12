/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.plan;

import indefensos.controladores.usuarios.*;
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

/**
 *
 * @author David
 */
@Named(value = "controladorListarPlan")
@ViewScoped
public class ControladorListarPlan implements Serializable {

    @EJB
    private PlanMejoramientoFacade planMejoramientoFacade;
    private List<PlanMejoramiento> listaPlanes;

    public ControladorListarPlan() {
    }

    @PostConstruct
    public void init() {
        listaPlanes = planMejoramientoFacade.findAll();

    }

    public List<PlanMejoramiento> getListaPlanes() {
        return listaPlanes;
    }

}
