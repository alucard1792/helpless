/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.modelo.dao;

import indefensos.modelo.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author David
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "IndefensosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario iniciarSesion(int documento, String clave) {
        List<Usuario> listaUsuario = new ArrayList<>();
        Usuario usuario = null;
        Query q = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.documento = :documento AND u.clave = :clave AND u.estado = 1", Usuario.class);
        q.setParameter("documento", documento);
        q.setParameter("clave", clave);
        listaUsuario = q.getResultList();
        for (Usuario u : listaUsuario) {
            usuario = u;

        }
        return usuario;

    }
