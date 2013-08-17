package com.cafeform.dndtools.mbean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.cafeform.dndtools.mbean.MonsterData;
import com.cafeform.dndtools.entity.DiceMaster;
import com.cafeform.dndtools.entity.MonsterMaster;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author kaizawa
 */
public class MonsterDataUTest {
    
    public MonsterDataUTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void hitPointTest(){
        DiceMaster diceMaster = mock(DiceMaster.class);
        when(diceMaster.getType()).thenReturn(6);
        
        MonsterMaster monsterMaster = mock(MonsterMaster.class);
        when(monsterMaster.getHitDiceNum()).thenReturn(6);
        when(monsterMaster.getHitDiceType()).thenReturn(diceMaster);
        
        MonsterData monsterDate = new MonsterData(monsterMaster);
        
        /* 
         * NPE happens within MonsterData.getAbilityModifierById. 
         * Becacause it requres to access Database entity, can't do test.
         */
        assertEquals(new Integer(3), monsterDate.getHitPoint());
    }
}
