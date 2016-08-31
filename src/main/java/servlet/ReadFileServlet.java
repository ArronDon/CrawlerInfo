package servlet;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by arron on 2016/8/29.
 */
public class ReadFileServlet {
    public String readfromFileNIO(String path) throws Exception {
        String content = null;
        FileInputStream fis = new FileInputStream(path);
        //System.out.println(path+":");
        //获取文件通道
        FileChannel fc = fis.getChannel();
        long size = fc.size();
        //指定缓冲区
        ByteBuffer bf = ByteBuffer.allocate(102400);
        byte[] bytes = new byte[102400];
        StringBuffer stringBuffer = new StringBuffer();
        while (fc.read(bf) != -1) {
            bf.flip();
            bytes = bf.array();
            stringBuffer.append(new String(bytes));

        }

        content = stringBuffer.toString();
        //System.out.println(new String(bytes));
        return content;
    }

    public String readfromFile(String path) throws Exception {
        File file = new File(path);
        FileInputStream fis=new FileInputStream(file);
        InputStreamReader reader=new InputStreamReader(fis,"utf-8");
        StringBuffer buffer=new StringBuffer();
        while(reader.ready()){
            buffer.append((char) reader.read());
        }
        reader.close();
        fis.close();
        /*InputStream fis = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line=null;
        try {
            while ((line=reader.readLine())!=null) {
                buffer.append(line);
                buffer.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
            fis.close();
        }*/
        return buffer.toString();
    }
}
