/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import negocio.EmpleadoObj;
import persistencia.AccesoDao;

/**
 *
 * @author sagit
 */
public class AccesoServicioImp implements AccesoServicio {

    private AccesoDao ad;

    public void setAd(AccesoDao ad) {
        this.ad = ad;
    }

    @Override
    public Object[] validar(String usu, String pas) {
        
        EmpleadoObj eo = ad.validar(usu, pas);
        if (eo != null) {
            Object[] fil = new Object[2];
            fil[0] = eo.getCod();
            fil[1] = eo.getNom();
            return fil;
        }
        return null;
    }

}
