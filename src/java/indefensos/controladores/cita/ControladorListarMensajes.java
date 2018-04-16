/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.cita;

import indefensos.controladores.login.ControladorLogin;
import indefensos.controladores.mail.Mailer;
import indefensos.controladores.usuarios.*;
import indefensos.modelo.dao.CitaFacade;
import indefensos.modelo.dao.UsuarioFacade;
import indefensos.modelo.entidades.Cita;
import indefensos.modelo.entidades.Rol;
import indefensos.modelo.entidades.Usuario;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
@Named(value = "controladorListarMensajes")
@ViewScoped
public class ControladorListarMensajes implements Serializable {

    @EJB
    private CitaFacade citaFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private List<Cita> listaCitas;

    public ControladorListarMensajes() {
    }

    @PostConstruct
    public void init() {
        if (controladorLogin.getUsuarioSesion().getRolesId().equals(new Rol(5))) {
            listaCitas = citaFacade.findAll();
        } else {
            listaCitas = citaFacade.listarCitasUsuarioEnSesion(controladorLogin.getUsuarioSesion());

        }

    }

    public List<Cita> getListaCitas() {
        return listaCitas;
    }

    public String prueba() throws UnsupportedEncodingException {
        Mailer.send("shalashaska-1992@hotmail.com", "prueba");
        return "";

    }

}
