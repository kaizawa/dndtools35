/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cafeform.dndtools.mbean;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kaizawa
 */
public class LoginControllerTest
{
    @Test
    public void testGetDigest () throws Exception
    {
        LoginController login = new LoginController();
        System.out.println(login.getDigest(""));
    }
    
}
