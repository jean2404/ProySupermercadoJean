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
import persistencia.Empleado;
import negocio.Proveedor;
import negocio.Detallecompra;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import negocio.Compra;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author sagit
 */
public class CompraJpaController implements Serializable {

    public CompraJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProySupermercadoJeanPU");

    }

    public CompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compra compra) throws PreexistingEntityException, Exception {
        if (compra.getDetallecompraList() == null) {
            compra.setDetallecompraList(new ArrayList<Detallecompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado cod = compra.getCod();
            if (cod != null) {
                cod = em.getReference(cod.getClass(), cod.getCod());
                compra.setCod(cod);
            }
            Proveedor ruc = compra.getRuc();
            if (ruc != null) {
                ruc = em.getReference(ruc.getClass(), ruc.getRuc());
                compra.setRuc(ruc);
            }
            List<Detallecompra> attachedDetallecompraList = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListDetallecompraToAttach : compra.getDetallecompraList()) {
                detallecompraListDetallecompraToAttach = em.getReference(detallecompraListDetallecompraToAttach.getClass(), detallecompraListDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraList.add(detallecompraListDetallecompraToAttach);
            }
            compra.setDetallecompraList(attachedDetallecompraList);
            em.persist(compra);
            if (cod != null) {
                cod.getCompraList().add(compra);
                cod = em.merge(cod);
            }
            if (ruc != null) {
                ruc.getCompraList().add(compra);
                ruc = em.merge(ruc);
            }
            for (Detallecompra detallecompraListDetallecompra : compra.getDetallecompraList()) {
                Compra oldCompraOfDetallecompraListDetallecompra = detallecompraListDetallecompra.getCompra();
                detallecompraListDetallecompra.setCompra(compra);
                detallecompraListDetallecompra = em.merge(detallecompraListDetallecompra);
                if (oldCompraOfDetallecompraListDetallecompra != null) {
                    oldCompraOfDetallecompraListDetallecompra.getDetallecompraList().remove(detallecompraListDetallecompra);
                    oldCompraOfDetallecompraListDetallecompra = em.merge(oldCompraOfDetallecompraListDetallecompra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCompra(compra.getNum()) != null) {
                throw new PreexistingEntityException("Compra " + compra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compra compra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra persistentCompra = em.find(Compra.class, compra.getNum());
            Empleado codOld = persistentCompra.getCod();
            Empleado codNew = compra.getCod();
            Proveedor rucOld = persistentCompra.getRuc();
            Proveedor rucNew = compra.getRuc();
            List<Detallecompra> detallecompraListOld = persistentCompra.getDetallecompraList();
            List<Detallecompra> detallecompraListNew = compra.getDetallecompraList();
            List<String> illegalOrphanMessages = null;
            for (Detallecompra detallecompraListOldDetallecompra : detallecompraListOld) {
                if (!detallecompraListNew.contains(detallecompraListOldDetallecompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallecompra " + detallecompraListOldDetallecompra + " since its compra field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codNew != null) {
                codNew = em.getReference(codNew.getClass(), codNew.getCod());
                compra.setCod(codNew);
            }
            if (rucNew != null) {
                rucNew = em.getReference(rucNew.getClass(), rucNew.getRuc());
                compra.setRuc(rucNew);
            }
            List<Detallecompra> attachedDetallecompraListNew = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListNewDetallecompraToAttach : detallecompraListNew) {
                detallecompraListNewDetallecompraToAttach = em.getReference(detallecompraListNewDetallecompraToAttach.getClass(), detallecompraListNewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraListNew.add(detallecompraListNewDetallecompraToAttach);
            }
            detallecompraListNew = attachedDetallecompraListNew;
            compra.setDetallecompraList(detallecompraListNew);
            compra = em.merge(compra);
            if (codOld != null && !codOld.equals(codNew)) {
                codOld.getCompraList().remove(compra);
                codOld = em.merge(codOld);
            }
            if (codNew != null && !codNew.equals(codOld)) {
                codNew.getCompraList().add(compra);
                codNew = em.merge(codNew);
            }
            if (rucOld != null && !rucOld.equals(rucNew)) {
                rucOld.getCompraList().remove(compra);
                rucOld = em.merge(rucOld);
            }
            if (rucNew != null && !rucNew.equals(rucOld)) {
                rucNew.getCompraList().add(compra);
                rucNew = em.merge(rucNew);
            }
            for (Detallecompra detallecompraListNewDetallecompra : detallecompraListNew) {
                if (!detallecompraListOld.contains(detallecompraListNewDetallecompra)) {
                    Compra oldCompraOfDetallecompraListNewDetallecompra = detallecompraListNewDetallecompra.getCompra();
                    detallecompraListNewDetallecompra.setCompra(compra);
                    detallecompraListNewDetallecompra = em.merge(detallecompraListNewDetallecompra);
                    if (oldCompraOfDetallecompraListNewDetallecompra != null && !oldCompraOfDetallecompraListNewDetallecompra.equals(compra)) {
                        oldCompraOfDetallecompraListNewDetallecompra.getDetallecompraList().remove(detallecompraListNewDetallecompra);
                        oldCompraOfDetallecompraListNewDetallecompra = em.merge(oldCompraOfDetallecompraListNewDetallecompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = compra.getNum();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
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
            Compra compra;
            try {
                compra = em.getReference(Compra.class, id);
                compra.getNum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallecompra> detallecompraListOrphanCheck = compra.getDetallecompraList();
            for (Detallecompra detallecompraListOrphanCheckDetallecompra : detallecompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compra (" + compra + ") cannot be destroyed since the Detallecompra " + detallecompraListOrphanCheckDetallecompra + " in its detallecompraList field has a non-nullable compra field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleado cod = compra.getCod();
            if (cod != null) {
                cod.getCompraList().remove(compra);
                cod = em.merge(cod);
            }
            Proveedor ruc = compra.getRuc();
            if (ruc != null) {
                ruc.getCompraList().remove(compra);
                ruc = em.merge(ruc);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compra> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<Compra> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<Compra> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compra.class));
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

    public Compra findCompra(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compra> rt = cq.from(Compra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
