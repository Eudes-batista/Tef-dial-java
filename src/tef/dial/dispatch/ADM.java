/*
 * Copyright 2013 - Jeandeson O. Merelis
 */
package tef.dial.dispatch;

import tef.dial.TefDial;

/**
 *
 * @author Jeandeson O. Merelis
 */
public class ADM extends DispatchMessage{

    public ADM() {
        operation = TefDial.Operation.ADM;
    }
    
}
