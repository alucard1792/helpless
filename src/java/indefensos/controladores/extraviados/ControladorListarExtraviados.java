/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.extraviados;

import indefensos.controladores.adoptado.*;
import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.ProcesoFacade;
import indefensos.modelo.entidades.Proceso;
import indefensos.modelo.entidades.Rol;
import java.io.Serializable;
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
@Named(value = "controladorListarExtraviados")
@ViewScoped
public class ControladorListarExtraviados implements Serializable {

    @EJB
    private ProcesoFacade procesoFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private List<Proceso> listaProcesos;

    public ControladorListarExtraviados() {
    }

    @PostConstruct
    public void init() {
        if (controladorLogin.getUsuarioSesion().getRolesId().equals(new Rol(5))) {
            listaProcesos = procesoFacade.listarMascotasConProcesoExtraviadoAdmin();
            System.out.println("es admin");
        } else {
            listaProcesos = procesoFacade.listarMascotasConProcesoExtraviado(controladorLogin.getUsuarioSesion());
            for (Proceso p : new ArrayList<Proceso>(listaProcesos)) {
                System.out.println(p.getMascotasId().getDueñoId().getNombres() + " " + p.getMascotasId().getDueñoId().getApellidos());
                if (p.getMascotasId().getDueñoId().equals(controladorLogin.getUsuarioSesion())) {
                    listaProcesos.remove(p);

                }

            }
            System.out.println("es otro");

        }

    }

    public List<Proceso> getListaProcesos() {
        return listaProcesos;
    }

}
