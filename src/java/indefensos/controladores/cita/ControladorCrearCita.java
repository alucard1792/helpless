/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.cita;

import indefensos.controladores.email.MailController;
import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.CitaFacade;
import indefensos.modelo.entidades.Cita;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorCrearCita")
@ViewScoped
public class ControladorCrearCita implements Serializable {

    @EJB
    private CitaFacade citaFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private Cita cita;

    public ControladorCrearCita() {
    }

    @PostConstruct
    public void init() {
        cita = new Cita();

    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public String crear() {
        cita.setIsLeida(0);
        cita.setUsuariosId(controladorLogin.getUsuarioSesion());
        citaFacade.create(cita);
        MailController mc = new MailController();
        mc.enviarEmailCliente("correofixedup@gmail.com", cita.getAsunto(), cita.getMensaje());
        return "/core/citas/listarMensajes.xhtml?faces-redirect=true";

    }

}
