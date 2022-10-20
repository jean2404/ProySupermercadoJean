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
import negocio.Compra;
import negocio.Detallecompra;
import negocio.DetallecompraPK;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author sagit
 */
public class DetallecompraJpaController implements Serializable {

    public DetallecompraJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProySupermercadoJeanPU");

    }

    public DetallecompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallecompra detallecompra) throws PreexistingEntityException, Exception {
        if (detallecompra.getDetallecompraPK() == null) {
            detallecompra.setDetallecompraPK(new DetallecompraPK());
        }
        detallecompra.getDetallecompraPK().setNum(detallecompra.getCompra().getNum());
        detallecompra.getDetallecompraPK().setCod(detallecompra.getArticulo().getCod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo articulo = detallecompra.getArticulo();
            if (articulo != null) {
                articulo = em.getReference(articulo.getClass(), articulo.getCod());
                detallecompra.setArticulo(articulo);
            }
            Compra compra = detallecompra.getCompra();
            if (compra != null) {
                compra = em.getReference(compra.getClass(), compra.getNum());
                detallecompra.setCompra(compra);
            }
            em.persist(detallecompra);
            if (articulo != null) {
                articulo.getDetallecompraList().add(detallecompra);
                articulo = em.merge(articulo);
            }
            if (compra != null) {
                compra.getDetallecompraList().add(detallecompra);
                compra = em.merge(compra);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallecompra(detallecompra.getDetallecompraPK()) != null) {
                throw new PreexistingEntityException("Detallecompra " + detallecompra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallecompra detallecompra) throws NonexistentEntityException, Exception {
        detallecompra.getDetallecompraPK().setNum(detallecompra.getCompra().getNum());
        detallecompra.getDetallecompraPK().setCod(detallecompra.getArticulo().getCod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallecompra persistentDetallecompra = em.find(Detallecompra.class, detallecompra.getDetallecompraPK());
            Articulo articuloOld = persistentDetallecompra.getArticulo();
            Articulo articuloNew = detallecompra.getArticulo();
            Compra compraOld = persistentDetallecompra.getCompra();
            Compra compraNew = detallecompra.getCompra();
            if (articuloNew != null) {
                articuloNew = em.getReference(articuloNew.getClass(), articuloNew.getCod());
                detallecompra.setArticulo(articuloNew);
            }
            if (compraNew != null) {
                compraNew = em.getReference(compraNew.getClass(), compraNew.getNum());
                detallecompra.setCompra(compraNew);
            }
            detallecompra = em.merge(detallecompra);
            if (articuloOld != null && !articuloOld.equals(articuloNew)) {
                articuloOld.getDetallecompraList().remove(detallecompra);
                articuloOld = em.merge(articuloOld);
            }
            if (articuloNew != null && !articuloNew.equals(articuloOld)) {
                articuloNew.getDetallecompraList().add(detallecompra);
                articuloNew = em.merge(articuloNew);
            }
            if (compraOld != null && !compraOld.equals(compraNew)) {
                compraOld.getDetallecompraList().remove(detallecompra);
                compraOld = em.merge(compraOld);
            }
            if (compraNew != null && !compraNew.equals(compraOld)) {
                compraNew.getDetallecompraList().add(detallecompra);
                compraNew = em.merge(compraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallecompraPK id = detallecompra.getDetallecompraPK();
                if (findDetallecompra(id) == null) {
                    throw new NonexistentEntityException("The detallecompra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallecompraPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallecompra detallecompra;
            try {
                detallecompra = em.getReference(Detallecompra.class, id);
                detallecompra.getDetallecompraPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallecompra with id " + id + " no longer exists.", enfe);
            }
            Articulo articulo = detallecompra.getArticulo();
            if (articulo != null) {
                articulo.getDetallecompraList().remove(detallecompra);
                articulo = em.merge(articulo);
            }
            Compra compra = detallecompra.getCompra();
            if (compra != null) {
                compra.getDetallecompraList().remove(detallecompra);
                compra = em.merge(compra);
            }
            em.remove(detallecompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallecompra> findDetallecompraEntities() {
        return findDetallecompraEntities(true, -1, -1);
    }

    public List<Detallecompra> findDetallecompraEntities(int maxResults, int firstResult) {
        return findDetallecompraEntities(false, maxResults, firstResult);
    }

    private List<Detallecompra> findDetallecompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallecompra.class));
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

    public Detallecompra findDetallecompra(DetallecompraPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallecompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallecompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallecompra> rt = cq.from(Detallecompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
