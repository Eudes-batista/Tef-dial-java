/*
 * Copyright 2013 - Jeandeson O. Merelis
 */
package br.com.saiphpdv.tef.dial.dispatch;

import br.com.saiphpdv.tef.dial.TefDial;

/**
 *
 * @author Jeandeson O. Merelis
 */
public class ADM extends DispatchMessage{

    public ADM() {
        operation = TefDial.Operation.ADM;
    }
    
}