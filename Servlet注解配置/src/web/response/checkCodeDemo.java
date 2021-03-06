package web.response;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeDemo")
public class checkCodeDemo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        //1.图片对象，在内存中代表图片
        //构造方法的参数宽高，图片类型
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //2.处理图片（实现验证码功能）
        //2.1获得画笔
        Graphics g = image.getGraphics();
        //2.2设置颜色
        g.setColor(Color.PINK);
        //2.3填充图片为青色
        g.fillRect(0,0,width,height);
        //2.4画边框
        g.setColor(Color.black);
        g.drawRect(0,0,width-1,height-1);
        //随机生成验证码
        String str = "0123456789";
        Random r = new Random();
        for (int i = 1; i <= 4; i++) {
            int index = r.nextInt(str.length());
            char c = str.charAt(index);
            g.drawString(c+"",width/5*i,height/2);
        }

        //2.5生成干扰线
        g.setColor(Color.gray);
        for (int i = 1; i < 8; i++) {
            int x1 = r.nextInt(width);
            int x2 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int y2 = r.nextInt(height);

            g.drawLine(x1,y1,x2,y2);
        }
        //3.利用ImageIO输出图片到流中
        ImageIO.write(image,"jpg",resp.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}

