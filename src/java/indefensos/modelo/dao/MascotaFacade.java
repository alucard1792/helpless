/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.modelo.dao;

import indefensos.modelo.entidades.Mascota;
import indefensos.modelo.entidades.Usuario;
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
public class MascotaFacade extends AbstractFacade<Mascota> {

    @PersistenceContext(unitName = "IndefensosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MascotaFacade() {
        super(Mascota.class);
    }

    public List<Mascota> listarMascotasUsuarioSesion(Usuario u) {
        Query q = getEntityManager().createQuery("SELECT m FROM Mascota m WHERE m.dueñoId = :dueñoId", Mascota.class);
        q.setParameter("dueñoId", u);
        return q.getResultList();

    }

}
