/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.modelo.dao;

import indefensos.modelo.entidades.Proceso;
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
public class ProcesoFacade extends AbstractFacade<Proceso> {

    @PersistenceContext(unitName = "IndefensosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoFacade() {
        super(Proceso.class);
    }

    public List<Proceso> listarMascotasConProcesoAdoptar() {
        Query q = getEntityManager().createQuery("SELECT p FROM Proceso p JOIN FETCH p.mascotasId.dueñoId WHERE p.tipoProceso = :tipoProceso AND p.isAutorizado = 0 AND p.usuariosId IS NULL", Proceso.class);
        q.setParameter("tipoProceso", "adopcion");
        return q.getResultList();

    }

}
