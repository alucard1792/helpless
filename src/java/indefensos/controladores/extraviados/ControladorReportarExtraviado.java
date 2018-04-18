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
    private String respuesta = "";

    public ControladorReportarExtraviado() {
    }

    @PostConstruct
    public void init() {

    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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
        procesoSeleccionado.setUsuariosId(controladorLogin.getUsuarioSesion());
        String mensaje = "<h1>Notificacion reporte mascota<h1><br/>"
                + "<h2>El usuario " + procesoSeleccionado.getUsuariosId().getNombres() + " " + procesoSeleccionado.getUsuariosId().getNombres() + " ha reportado su mascota<h2><br/>"
                + "<h3>Le solicitamos que se comunique con el usuario y valide la informacion en el sistema para actualizar el estado de su mascota<br/>"
                + "Datos del usuario que reporto a la mascota:<br/>"
                + "nombre: " + procesoSeleccionado.getUsuariosId().getNombres() + " " + procesoSeleccionado.getUsuariosId().getApellidos() + "<br/>"
                + "email: " + procesoSeleccionado.getUsuariosId().getEmail() + "<br/>"
                + "</h3><br/>"
                + "<h4>Indefendos " + Calendar.getInstance().get(Calendar.YEAR) + "</h4>";
        mc.enviarEmailCliente(procesoSeleccionado.getMascotasId().getDueñoId().getEmail(), "Respuesta extraviado", mensaje);
        
        //procesoSeleccionado.setIsAutorizado(1);
        procesoFacade.edit(procesoSeleccionado);
        return "/core/extraviados/listarExtraviados.xhtml?faces-redirect=true";

    }

}
