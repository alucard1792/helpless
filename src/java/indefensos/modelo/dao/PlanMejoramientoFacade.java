/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.modelo.dao;

import indefensos.modelo.entidades.PlanMejoramiento;
import indefensos.modelo.entidades.Rol;
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
public class PlanMejoramientoFacade extends AbstractFacade<PlanMejoramiento> {

    @PersistenceContext(unitName = "IndefensosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanMejoramientoFacade() {
        super(PlanMejoramiento.class);
    }

    public List<PlanMejoramiento> listarSolicitudesAsignadas(Usuario u) {
        List<PlanMejoramiento> listaPlanes = new ArrayList<>();
        Query q = getEntityManager().createQuery("SELECT p FROM PlanMejoramiento p WHERE p.usuariosSoporteId = :usuariosSoporteId", Usuario.class);
        q.setParameter("usuariosSoporteId", u);
        listaPlanes = q.getResultList();
        return listaPlanes;

    }
    
    public List<PlanMejoramiento> listarSolicitudesUsuarioFinal(Usuario u) {
        List<PlanMejoramiento> listaPlanes = new ArrayList<>();
        Query q = getEntityManager().createQuery("SELECT p FROM PlanMejoramiento p WHERE p.usuariosFinalId = :usuariosFinalId", Usuario.class);
        q.setParameter("usuariosFinalId", u);
        listaPlanes = q.getResultList();
        return listaPlanes;

    }

}
