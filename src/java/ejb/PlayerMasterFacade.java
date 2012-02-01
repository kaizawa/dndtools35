/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.PlayerMaster;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class PlayerMasterFacade extends AbstractFacade<PlayerMaster> {
    @PersistenceContext(unitName = "dndPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlayerMasterFacade() {
        super(PlayerMaster.class);
    }
     public PlayerMaster findByUsername(String username){
        try {
            return (PlayerMaster) em.createNamedQuery("PlayerMaster.findByUsername").setParameter("username", username).getSingleResult();
        } catch (NoResultException ex ){
            return null;
        }
    }   
}
