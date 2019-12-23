/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.coffeepot.pos.tef;

import com.sun.jna.Function;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

/**
 *
 * @author servidor
 */
public class FuncoesNativas {
    
    public static void bloquear(Boolean trava) {
        if (Platform.isWindows()) {
            NativeLibrary lib = NativeLibrary.getInstance("user32");
            Function fun = lib.getFunction("BlockInput");
            if (trava) {
                fun.invoke(new Object[]{"Blk"});
            } else {
                fun.invoke(new Object[]{0});
            }
        }
    }
    
     public static void focar(String titulo) {
        if (Platform.isWindows()) {
            User32 user32 = User32.INSTANCE;
            HWND win = user32.FindWindow(null, titulo);
            user32.SetFocus(win);
        }
    }
}
