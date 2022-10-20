/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import negocio.Articulo;
import negocio.Detalleventa;
import negocio.DetalleventaPK;
import negocio.Venta;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author sagit
 */
public class DetalleventaJpaController implements Serializable {

    public DetalleventaJpaController() {
            this.emf = Persistence.createEntityManagerFactory("ProySupermercadoJeanPU");

    }

    public DetalleventaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleventa detalleventa) throws PreexistingEntityException, Exception {
        if (detalleventa.getDetalleventaPK() == null) {
            detalleventa.setDetalleventaPK(new DetalleventaPK());
        }
        detalleventa.getDetalleventaPK().setCod(detalleventa.getArticulo().getCod());
        detalleventa.getDetalleventaPK().setNum(detalleventa.getVenta().getNum());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo articulo = detalleventa.getArticulo();
            if (articulo != null) {
                articulo = em.getReference(articulo.getClass(), articulo.getCod());
                detalleventa.setArticulo(articulo);
            }
            Venta venta = detalleventa.getVenta();
            if (venta != null) {
                venta = em.getReference(venta.getClass(), venta.getNum());
                detalleventa.setVenta(venta);
            }
            em.persist(detalleventa);
            if (articulo != null) {
                articulo.getDetalleventaList().add(detalleventa);
                articulo = em.merge(articulo);
            }
            if (venta != null) {
                venta.getDetalleventaList().add(detalleventa);
                venta = em.merge(venta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleventa(detalleventa.getDetalleventaPK()) != null) {
                throw new PreexistingEntityException("Detalleventa " + detalleventa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleventa detalleventa) throws NonexistentEntityException, Exception {
        detalleventa.getDetalleventaPK().setCod(detalleventa.getArticulo().getCod());
        detalleventa.getDetalleventaPK().setNum(detalleventa.getVenta().getNum());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleventa persistentDetalleventa = em.find(Detalleventa.class, detalleventa.getDetalleventaPK());
            Articulo articuloOld = persistentDetalleventa.getArticulo();
            Articulo articuloNew = detalleventa.getArticulo();
            Venta ventaOld = persistentDetalleventa.getVenta();
            Venta ventaNew = detalleventa.getVenta();
            if (articuloNew != null) {
                articuloNew = em.getReference(articuloNew.getClass(), articuloNew.getCod());
                detalleventa.setArticulo(articuloNew);
            }
            if (ventaNew != null) {
                ventaNew = em.getReference(ventaNew.getClass(), ventaNew.getNum());
                detalleventa.setVenta(ventaNew);
            }
            detalleventa = em.merge(detalleventa);
            if (articuloOld != null && !articuloOld.equals(articuloNew)) {
                articuloOld.getDetalleventaList().remove(detalleventa);
                articuloOld = em.merge(articuloOld);
            }
            if (articuloNew != null && !articuloNew.equals(articuloOld)) {
                articuloNew.getDetalleventaList().add(detalleventa);
                articuloNew = em.merge(articuloNew);
            }
            if (ventaOld != null && !ventaOld.equals(ventaNew)) {
                ventaOld.getDetalleventaList().remove(detalleventa);
                ventaOld = em.merge(ventaOld);
            }
            if (ventaNew != null && !ventaNew.equals(ventaOld)) {
                ventaNew.getDetalleventaList().add(detalleventa);
                ventaNew = em.merge(ventaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleventaPK id = detalleventa.getDetalleventaPK();
                if (findDetalleventa(id) == null) {
                    throw new NonexistentEntityException("The detalleventa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleventaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleventa detalleventa;
            try {
                detalleventa = em.getReference(Detalleventa.class, id);
                detalleventa.getDetalleventaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleventa with id " + id + " no longer exists.", enfe);
            }
            Articulo articulo = detalleventa.getArticulo();
            if (articulo != null) {
                articulo.getDetalleventaList().remove(detalleventa);
                articulo = em.merge(articulo);
            }
            Venta venta = detalleventa.getVenta();
            if (venta != null) {
                venta.getDetalleventaList().remove(detalleventa);
                venta = em.merge(venta);
            }
            em.remove(detalleventa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleventa> findDetalleventaEntities() {
        return findDetalleventaEntities(true, -1, -1);
    }

    public List<Detalleventa> findDetalleventaEntities(int maxResults, int firstResult) {
        return findDetalleventaEntities(false, maxResults, firstResult);
    }

    private List<Detalleventa> findDetalleventaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleventa.class));
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

    public Detalleventa findDetalleventa(DetalleventaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleventa.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleventaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleventa> rt = cq.from(Detalleventa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
