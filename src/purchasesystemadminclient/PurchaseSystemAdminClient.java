/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package purchasesystemadminclient;

import administrador.AdministradorRemote;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author jmagalhaes
 */
public class PurchaseSystemAdminClient {
    
    private static AdministradorRemote administrador;
     public static void getRemoteReferences() {

        InitialContext ctx = null;

        Properties prop = new Properties();

        

        prop.setProperty("java.naming.factory.initial",

                     "com.sun.enterprise.naming.SerialInitContextFactory");

        prop.setProperty("java.naming.factory.url.pkgs",

                     "com.sun.enterprise.naming");

        prop.setProperty("java.naming.factory.state",

                     "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

        prop.setProperty("org.omg.CORBA.ORBInitialHost", "192.168.56.175");

        prop.setProperty("org.omg.CORBA.ORBInitialPort", "3700");



        try {

            ctx = new InitialContext(prop);

        }

        catch (NamingException e) {

            System.out.println(e.getMessage());

            System.exit(1);

        }

        System.out.println("InitialContext done");



        String remotename = "java:global/PurchaseSystemServer/PurchaseSystemServer-ejb/Administrador!administrador.AdministradorRemote";

        try {

            System.out.println("start lookup");

            administrador = (AdministradorRemote)  ctx.lookup(remotename);

        }

        catch (NamingException e) {

            System.out.println(e.getMessage());

            e.printStackTrace();  // melhor -> mandar para log

            System.exit(1);

        }

        System.out.println("JNDI lookup done");

    }
    public static void main(String[] args) {
        // TODO code application logic here
               getRemoteReferences();
               System.out.println("Login:"+ administrador.login("admin", "admin"));
               System.exit(0);
    }
}
