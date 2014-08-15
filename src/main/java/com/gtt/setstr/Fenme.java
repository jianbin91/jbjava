package com.gtt.setstr;


import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by hidida on 14-8-9.
 */
public class Fenme {

    protected Logger log= Logger.getLogger(this.getClass().getName());

    public void setHandler(Handler handler){
        this.log.addHandler(handler);
    }



    public void info(String log_info){
        log.log(Level.INFO, log_info);
    }

    public String thingsdoing(String member){
        String lbs = this.getClass().getName();
        System.out.println(lbs);
        return null;
    }
    public static void main(String args[]) {
        Fenme fme = new Fenme();

        Logger logg = Logger.getLogger("com.gtt.setstr.Fenme");
        logg.setLevel(Level.INFO);

//        ConsoleHandler cHandler = new ConsoleHandler();
//        fme.setHandler( cHandler );

        fme.info("so you doing!");

        
    }
}
