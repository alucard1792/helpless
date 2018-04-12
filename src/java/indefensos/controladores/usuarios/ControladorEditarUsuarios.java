/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.usuarios;

import indefensos.modelo.dao.CiudadFacade;
import indefensos.modelo.dao.RolFacade;
import indefensos.modelo.dao.UsuarioFacade;
import indefensos.modelo.entidades.Ciudad;
import indefensos.modelo.entidades.Rol;
import indefensos.modelo.entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorEditarUsuarios")
@ConversationScoped
public class ControladorEditarUsuarios implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private RolFacade rolFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    @Inject
    private Conversation conversation;
    private List<Rol> listaRoles;
    private List<Ciudad> listaCiudades;
    private Usuario usuarioSeleccionado;

    public ControladorEditarUsuarios() {
    }

    @PostConstruct
    public void init() {
        listaCiudades = ciudadFacade.findAll();
        listaRoles = rolFacade.findAll();

    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public void iniciarConversacion() {
        if (conversation.isTransient()) {
            conversation.begin();
        }

    }

    public void terminarConversacion() {
        if (!conversation.isTransient()) {
            conversation.end();
        }

    }

    public String cancelar() {
        terminarConversacion();
        return "/core/usuarios/listarUsuarios.xhtml?faces-redirect=true";

    }

    public String prepararEditar(Usuario u) {
        iniciarConversacion();
        usuarioSeleccionado = u;
        return "/core/usuarios/editarUsuario.xhtml?faces-redirect=true";

    }

    public String editar() {
        usuarioFacade.edit(usuarioSeleccionado);
        return cancelar();

    }

}
