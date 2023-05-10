/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autentifikacijavezbe2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Marko
 */
public class AuthServer {
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException{
        int port = 8088;
        ServerSocket serverSocket = new ServerSocket(port);
        //hardkodovati lozinku
        //generisanje izazova - 4 bajta  slucajno generisana
        String klijentHesLozinka = "37a9a16dc0cb0dc2986c0dac3f18f9bd39392e1225cd9300ab36b3a140399b5e";
        
        
        while(true){
            Socket server = serverSocket.accept();
            DataInputStream in = new DataInputStream(server.getInputStream());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            //////////////
            String korIme = in.readUTF();
            System.out.println("Zahtev za autentifikaciju: " + korIme);
                        
            byte[] nizSlucajnih4 = new byte[4];
            new Random().nextBytes(nizSlucajnih4);            
            out.writeUTF(Hex.toHexString(nizSlucajnih4));     //slanje u klijenta   
            //////////////
            //byte[] ucitanaHesovanaKonkatenacija = in.readAllBytes();   //ucitavanje hes vrednosti iz klijenta
            
            //preuzimanje konkatenirane vrednosti iz klijenta
            String ucitanaHexKonkatenacijaIzKlijenta = in.readUTF();            //ucitavanje hes vrednosti iz klijenta
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String konkatenacija = klijentHesLozinka + Hex.toHexString(nizSlucajnih4);
            byte[] hesKonkatenacija = md.digest(konkatenacija.getBytes());
            String hexKonkatenacijaNaServeru = Hex.toHexString(hesKonkatenacija);
            System.out.println(hexKonkatenacijaNaServeru);
            
            if(hexKonkatenacijaNaServeru.equals(ucitanaHexKonkatenacijaIzKlijenta)){
                System.out.println("Jednaki su.");
            }else{
                System.out.println("Nisu jednaki.");
            }
            
            
            
            
            
            
            
            
            
            //////////////            
            in.close(); 
            out.close();
            server.close();
        }
    }

}
