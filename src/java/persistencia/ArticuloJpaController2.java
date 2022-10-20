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
import negocio.Detalleventa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import negocio.Articulo;
import negocio.Detallecompra;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author sagit
 */
public class ArticuloJpaController2 implements Serializable {

    public ArticuloJpaController2() {
              this.emf = Persistence.createEntityManagerFactory("ProySupermercadoJeanPU");

    }

    public ArticuloJpaController2(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articulo articulo) throws PreexistingEntityException, Exception {
        if (articulo.getDetalleventaList() == null) {
            articulo.setDetalleventaList(new ArrayList<Detalleventa>());
        }
        if (articulo.getDetallecompraList() == null) {
            articulo.setDetallecompraList(new ArrayList<Detallecompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalleventa> attachedDetalleventaList = new ArrayList<Detalleventa>();
            for (Detalleventa detalleventaListDetalleventaToAttach : articulo.getDetalleventaList()) {
                detalleventaListDetalleventaToAttach = em.getReference(detalleventaListDetalleventaToAttach.getClass(), detalleventaListDetalleventaToAttach.getDetalleventaPK());
                attachedDetalleventaList.add(detalleventaListDetalleventaToAttach);
            }
            articulo.setDetalleventaList(attachedDetalleventaList);
            List<Detallecompra> attachedDetallecompraList = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListDetallecompraToAttach : articulo.getDetallecompraList()) {
                detallecompraListDetallecompraToAttach = em.getReference(detallecompraListDetallecompraToAttach.getClass(), detallecompraListDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraList.add(detallecompraListDetallecompraToAttach);
            }
            articulo.setDetallecompraList(attachedDetallecompraList);
            em.persist(articulo);
            for (Detalleventa detalleventaListDetalleventa : articulo.getDetalleventaList()) {
                Articulo oldArticuloOfDetalleventaListDetalleventa = detalleventaListDetalleventa.getArticulo();
                detalleventaListDetalleventa.setArticulo(articulo);
                detalleventaListDetalleventa = em.merge(detalleventaListDetalleventa);
                if (oldArticuloOfDetalleventaListDetalleventa != null) {
                    oldArticuloOfDetalleventaListDetalleventa.getDetalleventaList().remove(detalleventaListDetalleventa);
                    oldArticuloOfDetalleventaListDetalleventa = em.merge(oldArticuloOfDetalleventaListDetalleventa);
                }
            }
            for (Detallecompra detallecompraListDetallecompra : articulo.getDetallecompraList()) {
                Articulo oldArticuloOfDetallecompraListDetallecompra = detallecompraListDetallecompra.getArticulo();
                detallecompraListDetallecompra.setArticulo(articulo);
                detallecompraListDetallecompra = em.merge(detallecompraListDetallecompra);
                if (oldArticuloOfDetallecompraListDetallecompra != null) {
                    oldArticuloOfDetallecompraListDetallecompra.getDetallecompraList().remove(detallecompraListDetallecompra);
                    oldArticuloOfDetallecompraListDetallecompra = em.merge(oldArticuloOfDetallecompraListDetallecompra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArticulo(articulo.getCod()) != null) {
                throw new PreexistingEntityException("Articulo " + articulo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articulo articulo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo persistentArticulo = em.find(Articulo.class, articulo.getCod());
            List<Detalleventa> detalleventaListOld = persistentArticulo.getDetalleventaList();
            List<Detalleventa> detalleventaListNew = articulo.getDetalleventaList();
            List<Detallecompra> detallecompraListOld = persistentArticulo.getDetallecompraList();
            List<Detallecompra> detallecompraListNew = articulo.getDetallecompraList();
            List<String> illegalOrphanMessages = null;
            for (Detalleventa detalleventaListOldDetalleventa : detalleventaListOld) {
                if (!detalleventaListNew.contains(detalleventaListOldDetalleventa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleventa " + detalleventaListOldDetalleventa + " since its articulo field is not nullable.");
                }
            }
            for (Detallecompra detallecompraListOldDetallecompra : detallecompraListOld) {
                if (!detallecompraListNew.contains(detallecompraListOldDetallecompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallecompra " + detallecompraListOldDetallecompra + " since its articulo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalleventa> attachedDetalleventaListNew = new ArrayList<Detalleventa>();
            for (Detalleventa detalleventaListNewDetalleventaToAttach : detalleventaListNew) {
                detalleventaListNewDetalleventaToAttach = em.getReference(detalleventaListNewDetalleventaToAttach.getClass(), detalleventaListNewDetalleventaToAttach.getDetalleventaPK());
                attachedDetalleventaListNew.add(detalleventaListNewDetalleventaToAttach);
            }
            detalleventaListNew = attachedDetalleventaListNew;
            articulo.setDetalleventaList(detalleventaListNew);
            List<Detallecompra> attachedDetallecompraListNew = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListNewDetallecompraToAttach : detallecompraListNew) {
                detallecompraListNewDetallecompraToAttach = em.getReference(detallecompraListNewDetallecompraToAttach.getClass(), detallecompraListNewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraListNew.add(detallecompraListNewDetallecompraToAttach);
            }
            detallecompraListNew = attachedDetallecompraListNew;
            articulo.setDetallecompraList(detallecompraListNew);
            articulo = em.merge(articulo);
            for (Detalleventa detalleventaListNewDetalleventa : detalleventaListNew) {
                if (!detalleventaListOld.contains(detalleventaListNewDetalleventa)) {
                    Articulo oldArticuloOfDetalleventaListNewDetalleventa = detalleventaListNewDetalleventa.getArticulo();
                    detalleventaListNewDetalleventa.setArticulo(articulo);
                    detalleventaListNewDetalleventa = em.merge(detalleventaListNewDetalleventa);
                    if (oldArticuloOfDetalleventaListNewDetalleventa != null && !oldArticuloOfDetalleventaListNewDetalleventa.equals(articulo)) {
                        oldArticuloOfDetalleventaListNewDetalleventa.getDetalleventaList().remove(detalleventaListNewDetalleventa);
                        oldArticuloOfDetalleventaListNewDetalleventa = em.merge(oldArticuloOfDetalleventaListNewDetalleventa);
                    }
                }
            }
            for (Detallecompra detallecompraListNewDetallecompra : detallecompraListNew) {
                if (!detallecompraListOld.contains(detallecompraListNewDetallecompra)) {
                    Articulo oldArticuloOfDetallecompraListNewDetallecompra = detallecompraListNewDetallecompra.getArticulo();
                    detallecompraListNewDetallecompra.setArticulo(articulo);
                    detallecompraListNewDetallecompra = em.merge(detallecompraListNewDetallecompra);
                    if (oldArticuloOfDetallecompraListNewDetallecompra != null && !oldArticuloOfDetallecompraListNewDetallecompra.equals(articulo)) {
                        oldArticuloOfDetallecompraListNewDetallecompra.getDetallecompraList().remove(detallecompraListNewDetallecompra);
                        oldArticuloOfDetallecompraListNewDetallecompra = em.merge(oldArticuloOfDetallecompraListNewDetallecompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = articulo.getCod();
                if (findArticulo(id) == null) {
                    throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.");
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
            Articulo articulo;
            try {
                articulo = em.getReference(Articulo.class, id);
                articulo.getCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalleventa> detalleventaListOrphanCheck = articulo.getDetalleventaList();
            for (Detalleventa detalleventaListOrphanCheckDetalleventa : detalleventaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Articulo (" + articulo + ") cannot be destroyed since the Detalleventa " + detalleventaListOrphanCheckDetalleventa + " in its detalleventaList field has a non-nullable articulo field.");
            }
            List<Detallecompra> detallecompraListOrphanCheck = articulo.getDetallecompraList();
            for (Detallecompra detallecompraListOrphanCheckDetallecompra : detallecompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Articulo (" + articulo + ") cannot be destroyed since the Detallecompra " + detallecompraListOrphanCheckDetallecompra + " in its detallecompraList field has a non-nullable articulo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(articulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articulo> findArticuloEntities() {
        return findArticuloEntities(true, -1, -1);
    }

    public List<Articulo> findArticuloEntities(int maxResults, int firstResult) {
        return findArticuloEntities(false, maxResults, firstResult);
    }

    private List<Articulo> findArticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articulo.class));
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

    public Articulo findArticulo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articulo> rt = cq.from(Articulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
