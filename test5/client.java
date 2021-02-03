package test5;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class client implements Runnable {
    private BufferedReader bufferedReader;
    private static Socket socket2;
    @Override
    public void run() {
        System.out.println("开始监听");
        try {
            socket2 = new Socket("192.168.41.96",23333);
            bufferedReader = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("OK");
        while (true){ //这里可以改为变量判断是否退出,循环接收服务器发来数据
            try {
                onclient();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void onclient() throws IOException {
        String str = bufferedReader.readLine();
        if (str != null) {
            Date nowDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");

            System.out.println("\r\nServer - "   + str);
        }
    }
    public static void main(String[] args){
        new client();
    }
    public client(){
        Thread th = new Thread(this);
        th.start();
        Scanner s = new Scanner(System.in);
        while (true){
            //循环接收输入数据
            String str = s.nextLine();
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(socket2.getOutputStream(), true);
                printWriter.println(str);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
