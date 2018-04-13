/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.adoptado;

import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.ProcesoFacade;
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
@Named(value = "controladorListarAdoptados")
@ViewScoped
public class ControladorListarAdoptados implements Serializable {

    @EJB
    private ProcesoFacade procesoFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private List<Proceso> listaProcesos;

    public ControladorListarAdoptados() {
    }

    @PostConstruct
    public void init() {
        if (controladorLogin.getUsuarioSesion().getRolesId().equals(new Rol(5))) {
            listaProcesos = procesoFacade.findAll();

        } else {
            listaProcesos = procesoFacade.listarMascotasConProcesoAdoptar();

        }

    }

    public List<Proceso> getListaProcesos() {
        return listaProcesos;
    }

}
