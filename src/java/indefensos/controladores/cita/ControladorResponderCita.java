/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.cita;

import indefensos.controladores.extraviados.*;
import indefensos.controladores.adoptado.*;
import indefensos.controladores.email.MailController;
import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.CitaFacade;
import indefensos.modelo.dao.EstadoMascotaFacade;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.dao.ProcesoFacade;
import indefensos.modelo.entidades.Cita;
import indefensos.modelo.entidades.EstadoMascota;
import indefensos.modelo.entidades.Mascota;
import indefensos.modelo.entidades.Proceso;
import indefensos.modelo.entidades.Rol;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorResponderCita")
@ViewScoped
public class ControladorResponderCita implements Serializable {

    @EJB
    private CitaFacade citaFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private Cita citaSeleccionada;
    private String respuesta;
    private Date d = new Date();

    public ControladorResponderCita() {
    }

    @PostConstruct
    public void init() {

    }

    public Cita getCitaSeleccionada() {
        return citaSeleccionada;
    }

    public void setCitaSeleccionada(Cita citaSeleccionada) {
        this.citaSeleccionada = citaSeleccionada;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }
    
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    public void prepararCita(Cita c){
        this.citaSeleccionada = c;
    }

    public String responder() {
        MailController mc = new MailController();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'a las' HH:mm:ss", new Locale("es_ES"));
        String fecha = formateador.format(d);
        String mensaje = "<h1>" + respuesta + "<h1><br/>"
                + "<h2>resumen del envio:<h2>"
                + "<h3>Nombre del remitente: " + controladorLogin.getUsuarioSesion().getNombres() + " " + controladorLogin.getUsuarioSesion().getApellidos() + "<br/>"
                + "fecha de la cita: " + fecha  + "<h3><br/>"
                + "<h4>Indefendos " + Calendar.getInstance().get(Calendar.YEAR) + "</h4>";
        mc.enviarEmailCliente(citaSeleccionada.getUsuariosId().getEmail(), "Agenda de cita", mensaje);
        citaSeleccionada.setIsLeida(1);
        citaFacade.edit(citaSeleccionada);
        return "";

    }

}
