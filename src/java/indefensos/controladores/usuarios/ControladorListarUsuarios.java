/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.usuarios;

import indefensos.modelo.dao.UsuarioFacade;
import indefensos.modelo.entidades.Rol;
import indefensos.modelo.entidades.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author David
 */
@Named(value = "controladorListarUsuarios")
@ViewScoped
public class ControladorListarUsuarios implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    private List<Usuario> listaUsuario;

    public ControladorListarUsuarios() {
    }

    @PostConstruct
    public void init() {
        listaUsuario = usuarioFacade.findAll();
        for (Usuario u : new ArrayList<Usuario>(listaUsuario)) {
            if (u.getRolesId().equals(new Rol(5))) {
                listaUsuario.remove(u);

            }
            
        }

    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

}
