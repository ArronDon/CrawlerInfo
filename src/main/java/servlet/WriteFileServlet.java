package servlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by arron on 2016/8/29.
 */
public class WriteFileServlet {
    public void writetoFile(String input) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("F:/Git/Crawler/resources/page.html");
        //得到文件通道
        FileChannel fc = fos.getChannel();
        //指定缓冲区
        ByteBuffer bf = ByteBuffer.allocate(input.getBytes().length);
        //字符放入缓冲区
        bf.put(input.getBytes());
        //执行flip是postion=0,limit=30
        bf.flip();
        //缓冲区数据写入到文件
        fc.write(bf);
        fc.close();
        fos.close();
    }
}
