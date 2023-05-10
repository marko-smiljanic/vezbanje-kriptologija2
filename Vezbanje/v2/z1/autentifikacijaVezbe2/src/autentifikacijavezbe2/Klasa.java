/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autentifikacijavezbe2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Marko
 */
public class Klasa {
    
    public static void main(String[] args) throws NoSuchAlgorithmException{
        String lozinka = "nekalozinka";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hesLozinka = md.digest(lozinka.getBytes());
        System.out.println(Arrays.toString(hesLozinka));

//        String b64 = Base64.getEncoder().encodeToString(hesLozinka);
//        System.out.println(b64);


        //drugi nacin da napravimo niz bajtova iz dodate biblioteke 
        String hex = Hex.toHexString(hesLozinka);
        System.out.println(hex);
    
    }

    
}
