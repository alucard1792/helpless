/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.usuarios;

import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.CiudadFacade;
import indefensos.modelo.dao.RolFacade;
import indefensos.modelo.dao.UsuarioFacade;
import indefensos.modelo.entidades.Ciudad;
import indefensos.modelo.entidades.Rol;
import indefensos.modelo.entidades.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "controladorCrearUsuarios")
@ViewScoped
public class ControladorCrearUsuarios implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private RolFacade rolFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private List<Rol> listaRoles;
    private List<Ciudad> listaCiudades;
    private Usuario usuario;

    public ControladorCrearUsuarios() {
    }

    @PostConstruct
    public void init() {
        listaCiudades = ciudadFacade.findAll();
        listaRoles = rolFacade.findAll();
        usuario = new Usuario();

    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String crear() {
        usuario.setRolesId(new Rol(6));
        usuario.setFechaRegistro(new Date());
        usuarioFacade.create(usuario);
        return "/core/usuarios/listarUsuarios.xhtml?faces-redirect=true";

    }

}
