/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAW;

import java.util.concurrent.Callable;

/**
 *
 * @author pascal
 */
public class MipTask implements Callable<String> {

    private Capability c;
        
    MipTask(Capability c) {
        this.c=c;
    }
    
    public String call() {
        c.buildMipVector();
        return c.getMipsAsString()+" finished";
    }
}

