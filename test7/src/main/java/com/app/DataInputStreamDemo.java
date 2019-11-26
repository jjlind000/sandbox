package com.app;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataInputStreamDemo {
   public static void main(String[] args) throws IOException {
      InputStream is = null;
      DataInputStream dis = null;
      byte[] buf = {65, 0, 0, 68, 69};
      
      try {
         // create new byte array input stream
         is = new ByteArrayInputStream(buf);
         
         // create data input stream
         dis = new DataInputStream(is);
         
         // readBoolean till the data available to read
         while( dis.available() >0) {
            System.out.println(dis.readBoolean());
         }
         
      } catch(Exception e) {
         // if any I/O error occurs
         e.printStackTrace();
      } finally {
         // releases any associated system files with this stream
         if(is!=null)
            is.close();
         if(dis!=null)
            dis.close();
      }   
   }
}