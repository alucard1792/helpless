/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.mascotas;

import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.entidades.Mascota;
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
@Named(value = "controladorListarMascotas")
@ViewScoped
public class ControladorListarMascotas implements Serializable {

    @EJB
    private MascotaFacade mascotaFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private List<Mascota> listaMascotas;

    public ControladorListarMascotas() {
    }

    @PostConstruct
    public void init() {
        listaMascotas = mascotaFacade.listarMascotasUsuarioSesion(controladorLogin.getUsuarioSesion());

    }

    public List<Mascota> getListaMascotas() {
        return listaMascotas;
    }

}
