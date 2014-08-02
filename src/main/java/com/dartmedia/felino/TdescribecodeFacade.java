/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dartmedia.felino;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ni
 */
@Stateless
public class TdescribecodeFacade extends AbstractFacade<Tdescribecode> {
    @PersistenceContext(unitName = "com.ninofelino_ninofelino_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TdescribecodeFacade() {
        super(Tdescribecode.class);
    }
    
}
