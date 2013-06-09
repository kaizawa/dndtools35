package ejb;

import entity.RulebookMaster;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class RulebookMasterFacade extends AbstractFacade<RulebookMaster> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RulebookMasterFacade() {
        super(RulebookMaster.class);
    }

}
