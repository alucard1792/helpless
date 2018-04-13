/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.modelo.dao;

import indefensos.modelo.entidades.Mascota;
import indefensos.modelo.entidades.Proceso;
import indefensos.modelo.entidades.Usuario;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public List<Mascota> listarMascotasParaAdoptar() {
        Set<Mascota> listaMascotas = new HashSet<>();
        Query q = getEntityManager().createQuery("SELECT p FROM Proceso p WHERE p.tipoProceso = :tipoProceso", Proceso.class);
        q.setParameter("tipoProceso", "adopcion");
        for (Proceso p : new ArrayList<Proceso>(q.getResultList())) {
            listaMascotas.add(p.getMascotasId());

        }
        List<Mascota> lm = new ArrayList<>(listaMascotas);
        return lm;

    }

    public List<Mascota> listarMascotasExtraviadas() {
        Set<Mascota> listaMascotas = new HashSet<>();
        Query q = getEntityManager().createQuery("SELECT p FROM Proceso p WHERE p.tipoProceso = :tipoProceso", Proceso.class);
        q.setParameter("tipoProceso", "extraviado");
        for (Proceso p : new ArrayList<Proceso>(q.getResultList())) {
            listaMascotas.add(p.getMascotasId());

        }
        List<Mascota> lm = new ArrayList<>(listaMascotas);
        return lm;

    }

}
