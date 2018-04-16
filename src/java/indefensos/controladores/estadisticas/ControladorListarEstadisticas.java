/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.estadisticas;

import indefensos.controladores.cita.*;
import indefensos.controladores.login.ControladorLogin;
import indefensos.controladores.mail.Mailer;
import indefensos.controladores.usuarios.*;
import indefensos.modelo.dao.CitaFacade;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.dao.ProcesoFacade;
import indefensos.modelo.dao.UsuarioFacade;
import indefensos.modelo.entidades.Cita;
import indefensos.modelo.entidades.Mascota;
import indefensos.modelo.entidades.Proceso;
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
@Named(value = "controladorListarEstadisticas")
@ViewScoped
public class ControladorListarEstadisticas implements Serializable {

    @EJB
    private MascotaFacade mascotaFacade;
    @EJB
    private ProcesoFacade procesoFacade;
    private List<Mascota> listaMascota;
    private List<Proceso> listaProceso;

    private int numeroPerros,
            numeroGatos,
            numeroAdoptados,
            numeroExtraviados;

    public ControladorListarEstadisticas() {
    }

    @PostConstruct
    public void init() {
        listaMascota = mascotaFacade.findAll();
        listaProceso = procesoFacade.findAll();
        for (Mascota m : listaMascota) {
            if (m.getTipoMascota().equals("perro")) {
                ++numeroPerros;

            } else if (m.getTipoMascota().equals("gato")) {
                ++numeroGatos;

            }

        }

        for (Proceso p : listaProceso) {
            if (p.getTipoProceso().equals("extraviado")) {
                ++numeroExtraviados;

            } else if (p.getTipoProceso().equals("adopcion")) {
                ++numeroAdoptados;

            }

        }

    }

    public int getNumeroPerros() {
        return numeroPerros;
    }

    public void setNumeroPerros(int numeroPerros) {
        this.numeroPerros = numeroPerros;
    }

    public int getNumeroGatos() {
        return numeroGatos;
    }

    public void setNumeroGatos(int numeroGatos) {
        this.numeroGatos = numeroGatos;
    }

    public int getNumeroAdoptados() {
        return numeroAdoptados;
    }

    public void setNumeroAdoptados(int numeroAdoptados) {
        this.numeroAdoptados = numeroAdoptados;
    }

    public int getNumeroExtraviados() {
        return numeroExtraviados;
    }

    public void setNumeroExtraviados(int numeroExtraviados) {
        this.numeroExtraviados = numeroExtraviados;
    }

}
