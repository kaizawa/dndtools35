package ejb;

import entity.ArmType1Master;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kaizawa
 */
@Stateless
public class ArmType1MasterFacade extends AbstractFacade<ArmType1Master> {
    @PersistenceContext(unitName = "dndtoolsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArmType1MasterFacade() {
        super(ArmType1Master.class);
    }

}
