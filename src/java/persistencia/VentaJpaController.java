/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import negocio.Cliente;
import persistencia.Empleado;
import negocio.Detalleventa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import negocio.Venta;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author sagit
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController() {
            this.emf = Persistence.createEntityManagerFactory("ProySupermercadoJeanPU");

    }

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) throws PreexistingEntityException, Exception {
        if (venta.getDetalleventaList() == null) {
            venta.setDetalleventaList(new ArrayList<Detalleventa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente dni = venta.getDni();
            if (dni != null) {
                dni = em.getReference(dni.getClass(), dni.getDni());
                venta.setDni(dni);
            }
            Empleado cod = venta.getCod();
            if (cod != null) {
                cod = em.getReference(cod.getClass(), cod.getCod());
                venta.setCod(cod);
            }
            List<Detalleventa> attachedDetalleventaList = new ArrayList<Detalleventa>();
            for (Detalleventa detalleventaListDetalleventaToAttach : venta.getDetalleventaList()) {
                detalleventaListDetalleventaToAttach = em.getReference(detalleventaListDetalleventaToAttach.getClass(), detalleventaListDetalleventaToAttach.getDetalleventaPK());
                attachedDetalleventaList.add(detalleventaListDetalleventaToAttach);
            }
            venta.setDetalleventaList(attachedDetalleventaList);
            em.persist(venta);
            if (dni != null) {
                dni.getVentaList().add(venta);
                dni = em.merge(dni);
            }
            if (cod != null) {
                cod.getVentaList().add(venta);
                cod = em.merge(cod);
            }
            for (Detalleventa detalleventaListDetalleventa : venta.getDetalleventaList()) {
                Venta oldVentaOfDetalleventaListDetalleventa = detalleventaListDetalleventa.getVenta();
                detalleventaListDetalleventa.setVenta(venta);
                detalleventaListDetalleventa = em.merge(detalleventaListDetalleventa);
                if (oldVentaOfDetalleventaListDetalleventa != null) {
                    oldVentaOfDetalleventaListDetalleventa.getDetalleventaList().remove(detalleventaListDetalleventa);
                    oldVentaOfDetalleventaListDetalleventa = em.merge(oldVentaOfDetalleventaListDetalleventa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVenta(venta.getNum()) != null) {
                throw new PreexistingEntityException("Venta " + venta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getNum());
            Cliente dniOld = persistentVenta.getDni();
            Cliente dniNew = venta.getDni();
            Empleado codOld = persistentVenta.getCod();
            Empleado codNew = venta.getCod();
            List<Detalleventa> detalleventaListOld = persistentVenta.getDetalleventaList();
            List<Detalleventa> detalleventaListNew = venta.getDetalleventaList();
            List<String> illegalOrphanMessages = null;
            for (Detalleventa detalleventaListOldDetalleventa : detalleventaListOld) {
                if (!detalleventaListNew.contains(detalleventaListOldDetalleventa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleventa " + detalleventaListOldDetalleventa + " since its venta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (dniNew != null) {
                dniNew = em.getReference(dniNew.getClass(), dniNew.getDni());
                venta.setDni(dniNew);
            }
            if (codNew != null) {
                codNew = em.getReference(codNew.getClass(), codNew.getCod());
                venta.setCod(codNew);
            }
            List<Detalleventa> attachedDetalleventaListNew = new ArrayList<Detalleventa>();
            for (Detalleventa detalleventaListNewDetalleventaToAttach : detalleventaListNew) {
                detalleventaListNewDetalleventaToAttach = em.getReference(detalleventaListNewDetalleventaToAttach.getClass(), detalleventaListNewDetalleventaToAttach.getDetalleventaPK());
                attachedDetalleventaListNew.add(detalleventaListNewDetalleventaToAttach);
            }
            detalleventaListNew = attachedDetalleventaListNew;
            venta.setDetalleventaList(detalleventaListNew);
            venta = em.merge(venta);
            if (dniOld != null && !dniOld.equals(dniNew)) {
                dniOld.getVentaList().remove(venta);
                dniOld = em.merge(dniOld);
            }
            if (dniNew != null && !dniNew.equals(dniOld)) {
                dniNew.getVentaList().add(venta);
                dniNew = em.merge(dniNew);
            }
            if (codOld != null && !codOld.equals(codNew)) {
                codOld.getVentaList().remove(venta);
                codOld = em.merge(codOld);
            }
            if (codNew != null && !codNew.equals(codOld)) {
                codNew.getVentaList().add(venta);
                codNew = em.merge(codNew);
            }
            for (Detalleventa detalleventaListNewDetalleventa : detalleventaListNew) {
                if (!detalleventaListOld.contains(detalleventaListNewDetalleventa)) {
                    Venta oldVentaOfDetalleventaListNewDetalleventa = detalleventaListNewDetalleventa.getVenta();
                    detalleventaListNewDetalleventa.setVenta(venta);
                    detalleventaListNewDetalleventa = em.merge(detalleventaListNewDetalleventa);
                    if (oldVentaOfDetalleventaListNewDetalleventa != null && !oldVentaOfDetalleventaListNewDetalleventa.equals(venta)) {
                        oldVentaOfDetalleventaListNewDetalleventa.getDetalleventaList().remove(detalleventaListNewDetalleventa);
                        oldVentaOfDetalleventaListNewDetalleventa = em.merge(oldVentaOfDetalleventaListNewDetalleventa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = venta.getNum();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getNum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalleventa> detalleventaListOrphanCheck = venta.getDetalleventaList();
            for (Detalleventa detalleventaListOrphanCheckDetalleventa : detalleventaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Venta (" + venta + ") cannot be destroyed since the Detalleventa " + detalleventaListOrphanCheckDetalleventa + " in its detalleventaList field has a non-nullable venta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente dni = venta.getDni();
            if (dni != null) {
                dni.getVentaList().remove(venta);
                dni = em.merge(dni);
            }
            Empleado cod = venta.getCod();
            if (cod != null) {
                cod.getVentaList().remove(venta);
                cod = em.merge(cod);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Venta findVenta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
