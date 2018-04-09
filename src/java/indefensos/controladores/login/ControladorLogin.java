/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.login;

import indefensos.modelo.dao.UsuarioFacade;
import indefensos.modelo.entidades.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author David
 */
@Named(value = "controladorLogin")
@SessionScoped
public class ControladorLogin implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario usuarioSesion = null;
    private int documento;
    private String clave;

    public ControladorLogin() {
    }

    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String iniciarSesion() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if ((documento != 0) && clave != null && !clave.equals("")) {
            usuarioSesion = usuarioFacade.iniciarSesion(documento, clave);

            if (usuarioSesion != null && usuarioSesion.getEstado() == 1) {
                this.documento = 0;
                this.clave = "";
                return "/core/index.xhtml?faces-redirect=true";

            } else {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "contraseña, documento incorrecto o usuario desactivado. ", "Contacte con el administrador");
                fc.addMessage(null, fm);

            }
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Todos los campos son obligatorios", "Diligencie todos los campos");
            fc.addMessage(null, fm);

        }
        return "";

    }

    public void cerrarSesion() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.invalidateSession();
        this.usuarioSesion = null;

        try {
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml?faces-redirect=true");

        } catch (IOException ex) {
            Logger.getLogger(ControladorLogin.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void validarSesion() {
        if (!isValidado()) {
            cerrarSesion();

        }

    }

    public boolean isValidado() {
        if (usuarioSesion != null) {
            return true;

        }
        return false;

    }

}
