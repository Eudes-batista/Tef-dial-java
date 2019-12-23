/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.coffeepot.pos.tef;

import coffeepot.pos.tef.dial.TefDial;
import java.io.File;

/**
 *
 * @author eudes
 */
public class GerenciadorPadrao {

    public static void main(String[] args) {
        try {
            new GerenciadorPadrao().testActive();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void testActive() throws Exception {
        System.out.println("");
        System.out.println("");
        System.out.println("active");
        TefDial instance = new TefDial();
        instance.setRequestDirectory(new File("C:\\tef_dial\\req"));
        instance.setResponseDirectory(new File("C:\\tef_dial\\resp"));
        boolean ativo =  instance.isActive();
        System.out.println("ativo = " + ativo);
    }
    
}
