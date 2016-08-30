package servlet;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by arron on 2016/8/29.
 */
public class ReadFileServlet {
    public String readfromFile(String path) throws Exception{
        String content=null;
        FileInputStream fis=new FileInputStream(path);
        //System.out.println(path+":");
        //获取文件通道
        FileChannel fc=fis.getChannel();
        long size=fc.size();
        //指定缓冲区
        ByteBuffer bf=ByteBuffer.allocate(1024);
        byte[] bytes=new byte[102400];
        StringBuffer stringBuffer=new StringBuffer();
        while(fc.read(bf)!=-1){
            bf.flip();
            bytes=bf.array();
            stringBuffer.append(new String(bytes));
        }
        content=stringBuffer.toString();
        return content;
    }
}
