/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autentifikacijavezbe2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Marko
 */
public class AuthKlijent {
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException{
        String serverName = "localhost";
        int port = 8088;
        String lozinka = "nekalozinka";
        
        System.out.println("Konekcija na: " + serverName + " port:" + port);
        Socket klijent = new Socket(serverName, port);
        System.out.println("Konekcija uspesna: " + klijent.getRemoteSocketAddress());

        DataInputStream in = new DataInputStream(klijent.getInputStream());
        DataOutputStream out = new DataOutputStream(klijent.getOutputStream());
        ////////////////
        System.out.println("Unesite korisnicko ime: ");
        String korIme = new Scanner(System.in).nextLine();        
        long start = System.currentTimeMillis(); //samo pocetak merenja vremena, merimo vreme da vidimo koliko vremena treba da se uspostavi handshake, odnosno konekcija        
        out.writeUTF(korIme);
        ///////////////Zavrsena razmena kor imena
        
        //Ucitano iz servera, ovo je poslato kao hex
        String ucitanoNounceOdServera = in.readUTF();  
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hesLozinka = md.digest(lozinka.getBytes());               //hesujemo nativnu lozinku
        String hexHesLozinka = Hex.toHexString(hesLozinka);
        
        String konkatenacija = hexHesLozinka + ucitanoNounceOdServera;   //konkateniraju se obe hex vrednosti
        System.out.println(konkatenacija);
        //sifrovanje
        md.reset();
        //zbog cega se radi Hex ponovo ovde kada su u konkatenaciji bile obe Hex vrednosti
        String odgovor = Hex.toHexString(md.digest(konkatenacija.getBytes()));   //konkatenacija se prevodi ponovo u hex, i tek onda se salje
        out.writeUTF(odgovor);
        
        long stop = System.currentTimeMillis();  //kraj merenja vremena
        System.out.println("Vreme koje je bilo potrebno da se odradi handshake :" + (stop-start));

        ///////////////
        in.close(); 
        out.close();
        klijent.close();
    }
    

    
    
    
    
    
}
