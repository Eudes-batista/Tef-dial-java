/*
 * Copyright 2013 - Jeandeson O. Merelis
 */
package tef.dial.dispatch;

import tef.dial.TefDial;

/**
 *
 * @author Jeandeson O. Merelis
 */
public class ATV extends DispatchMessage{

    public ATV() {
        operation = TefDial.Operation.ATV;
    }
    
}
