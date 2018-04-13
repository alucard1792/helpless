/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.email;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorEnvioEmail")
@ViewScoped
public class ControladorEnvioEmail implements Serializable {

    String message = "";
    String destinarios = "";

    public ControladorEnvioEmail() {
    }

    @PostConstruct
    public void init() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestinarios() {
        return destinarios;
    }

    public void setDestinarios(String destinarios) {
        this.destinarios = destinarios;
    }

    public void enviarEmail() {
        System.out.println("mensaje = " + message);
        String temp = "envio de emails"
                + message;

        MailController mc = new MailController();
        mc.enviarEmailCliente(destinarios, "Envio de emails indefensos", temp);

    }
}
