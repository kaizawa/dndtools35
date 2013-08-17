/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cafeform.dndtools.mbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version Footer.java
 * @version Created on 2009/03/29, 17:09:24
 * @author ka78231
 */

@Named
@RequestScoped
public class PageFooter  {


    public PageFooter() {
    }

    public String gotoTopLink_action() {
        return null;
    }

}
