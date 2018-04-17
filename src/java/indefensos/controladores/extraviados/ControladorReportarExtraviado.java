/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.extraviados;

import indefensos.controladores.adoptado.*;
import indefensos.controladores.email.MailController;
import indefensos.controladores.login.ControladorLogin;
import indefensos.controladores.mail.Mailer;
import indefensos.modelo.dao.EstadoMascotaFacade;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.dao.ProcesoFacade;
import indefensos.modelo.entidades.EstadoMascota;
import indefensos.modelo.entidades.Mascota;
import indefensos.modelo.entidades.Proceso;
import indefensos.modelo.entidades.Rol;
import java.io.Serializable;
import java.util.Calendar;
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
@Named(value = "controladorReportarExtraviado")
@ViewScoped
public class ControladorReportarExtraviado implements Serializable {

    @EJB
    private MascotaFacade mascotaFacade;
    @EJB
    private EstadoMascotaFacade estadoMascotaFacade;
    @EJB
    private ProcesoFacade procesoFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private List<EstadoMascota> listaEstadosMascotas;
    private Mascota mascotaSeleccionada;
    private Proceso procesoSeleccionado;

    public ControladorReportarExtraviado() {
    }

    @PostConstruct
    public void init() {

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

    public Proceso getProcesoSeleccionado() {
        return procesoSeleccionado;
    }

    public void setProcesoSeleccionado(Proceso procesoSeleccionado) {
        this.procesoSeleccionado = procesoSeleccionado;
    }

    public void prepararProceso(Proceso p) {
        procesoSeleccionado = p;

    }

    public String reportarMascota() {
        MailController mc = new MailController();
        /*String mensaje = "<h1>" + respuesta + "<h1><br/>"
                + "<h2>resumen del envio:<h2>"
                + "<h3>Nombre del remitente: " + controladorLogin.getUsuarioSesion().getNombres() + " " + controladorLogin.getUsuarioSesion().getApellidos() + "<br/>"
                + "fecha de la cita: " + fecha + "<h3><br/>"
                + "<h4>Indefendos " + Calendar.getInstance().get(Calendar.YEAR) + "</h4>";

        mc.enviarEmailCliente(procesoSeleccionado.getMascotasId().getDueñoId().getEmail(), "Respuesta extraviado", procesoSeleccionado.getRespuesta());*/
        //procesoSeleccionado.setIsAutorizado(1);
        procesoSeleccionado.setUsuariosId(controladorLogin.getUsuarioSesion());
        procesoFacade.edit(procesoSeleccionado);
        return "/core/extraviados/listarExtraviados.xhtml?faces-redirect=true";

    }

}
