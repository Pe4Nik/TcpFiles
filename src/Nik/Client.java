package Nik;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
public class Client {
    final static int port = 8080;
    public static void main(String args[]){
        try {
            String filename = "cat.jpg";
            File file = new File("C:\\Users\\Pe4Nik\\Desktop\\"+filename);
            FileInputStream input = new FileInputStream(file);
            //byte [] mybytearray  = new byte [(int)file.length()];
            Socket socket = new Socket("127.0.0.1", port);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(filename);
            out.writeLong(file.length());
            //input.read(mybytearray, 0 ,mybytearray.length);
            //out.write(mybytearray, 0, mybytearray.length);
            byte[] buffer = new byte[4096];
            while (input.read(buffer) > 0) {
                out.write(buffer);
            }
            out.flush();
            input.close();
            out.close();
            socket.close();
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}
