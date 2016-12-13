package Nik;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.*;
public class Server {
    static final int port = 8080;
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket (port);
            Socket client = server.accept();
            DataInputStream input = new DataInputStream(client.getInputStream());
            String filename = input.readUTF();
            long fileLength = input.readLong();
            File file = new File("C:\\Users\\Pe4Nik\\Desktop\\123\\"+filename);
            FileOutputStream fos = new FileOutputStream(file);
            byte [] mybytearray  = new byte [(int)fileLength];
            int bytesRead = input.read(mybytearray, 0, mybytearray.length);
            int current = bytesRead;
            while(true) {
                bytesRead = input.read(mybytearray, current, (mybytearray.length-current));
                if(bytesRead > 0)
                    current += bytesRead;
                else
                    break;
            }

            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(mybytearray, 0 , current);
            bos.flush();

            input.close();
            client.close();
            server.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
