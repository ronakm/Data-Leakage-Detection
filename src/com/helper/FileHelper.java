/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author admin
 */
public class FileHelper {

    
public static  double getDirectorySize (String dirName){
    double size=0;
       File f1 = new File(dirName) ;
       if(f1.isDirectory()){
       File[] subFiles = f1.listFiles();
       for(File file : subFiles){
           if(file.isFile()){
               size += file.length();
           }
           else {
               size += FileHelper.getDirectorySize(file.getAbsolutePath());
           }
       }
    }else{
           size += f1.length();
    }
        return size;
 }


public static void CopyDirectory(File srcPath, File dstPath) throws FileNotFoundException,
        IOException{
         if (srcPath.isDirectory()){
            if (!dstPath.exists()){
                dstPath.mkdir();
            }
            String files[] = srcPath.list();
            for(int i = 0; i < files.length; i++){
                CopyDirectory(new File(srcPath, files[i]),
                new File(dstPath, files[i]));
            }
         }
         else{
                InputStream in = new FileInputStream(srcPath);
                OutputStream out = new FileOutputStream(dstPath);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
         }
}
  public static String getFileText(String fileName) {
  StringBuffer str=new StringBuffer("");
        try{
     BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
  String strLine="";
  //Read File Line By Line
  while ((strLine = br.readLine()) != null)   {
              str.append(strLine);
              str.append('\n');
        }
        br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return  str.toString();
    }

}
