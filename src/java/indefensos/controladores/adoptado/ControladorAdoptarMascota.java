/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.adoptado;

import indefensos.controladores.email.MailController;
import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.EstadoMascotaFacade;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.dao.ProcesoFacade;
import indefensos.modelo.entidades.EstadoMascota;
import indefensos.modelo.entidades.Mascota;
import indefensos.modelo.entidades.Proceso;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
@Named(value = "controladorAdoptarMascota")
@ViewScoped
public class ControladorAdoptarMascota implements Serializable {

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

    public ControladorAdoptarMascota() {
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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String iniciarAdopcion() {
        MailController mc = new MailController();
        procesoSeleccionado.setUsuariosId(controladorLogin.getUsuarioSesion());
        procesoFacade.edit(procesoSeleccionado);
        String mensaje = "<h1>Notificacion adopcion<h1><br/>"
                + "<h2>El usuario " + controladorLogin.getUsuarioSesion().getNombres() + " " + controladorLogin.getUsuarioSesion().getApellidos() + " ha iniciado proceso de adopcion<h2><br/>"
                + "<h3>El proceso se hara valido cuando el admin valide la informacion<br/>"
                + "Datos de la mascota:<br/>"
                + "nombre: " + procesoSeleccionado.getMascotasId().getNombre() + "<br/>"
                + "edad: " + procesoSeleccionado.getMascotasId().getEdad() + "<br/>"
                + "</h3><br/>"
                + "<h4>Indefendos " + Calendar.getInstance().get(Calendar.YEAR) + "</h4>";
        mc.enviarEmailCliente(procesoSeleccionado.getMascotasId().getDueñoId().getEmail(), "Notificacion adopcion", mensaje);
        mc.enviarEmailCliente(procesoSeleccionado.getUsuariosId().getEmail(), "Notificacion adopcion", mensaje);
        return "/core/adopcion/listarAdoptados.xhtml?faces-redirect=true";

    }

    public String aprobarAdopcion() {
        MailController mc = new MailController();
        String mensaje = "<h1>Notificacion adopcion<h1><br/>"
                + "<h2>La fundacion ha aprobado el proceso de adopcion<h2><br/>"
                + "<h3>Por favor darle mucho amor a su nuevo compañero<br/>"
                + "Datos de la mascota:<br/>"
                + "nombre: " + procesoSeleccionado.getMascotasId().getNombre() + "<br/>"
                + "edad: " + procesoSeleccionado.getMascotasId().getEdad() + "<br/>"
                + "otra informacion: " +  (respuesta.length() == 0 ? "n/a":respuesta)
                + "</h3><br/>"
                + "<h4>Indefendos " + Calendar.getInstance().get(Calendar.YEAR) + "</h4>";
        mc.enviarEmailCliente(procesoSeleccionado.getMascotasId().getDueñoId().getEmail(), "Notificacion adopcion", mensaje);
        mc.enviarEmailCliente(procesoSeleccionado.getUsuariosId().getEmail(), "Notificacion adopcion", mensaje);
        procesoSeleccionado.setIsAutorizado(1);
        Mascota m = procesoSeleccionado.getMascotasId();
        m.setDueñoId(procesoSeleccionado.getUsuariosId());
        m.setEstadoMascotasId(new EstadoMascota(1));
        m.setHasProceso(0);
        
        
        procesoFacade.edit(procesoSeleccionado);
        mascotaFacade.edit(m);

        return "/core/adopcion/listarAdoptados.xhtml?faces-redirect=true";

    }

}
