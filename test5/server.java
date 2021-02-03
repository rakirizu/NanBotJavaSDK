package test5;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class server implements Runnable {
    private static Socket socket1 = null;
    private BufferedReader bufferedReader;
    private ServerSocket serverSocket;
    @Override
    public void run() {
        System.out.println("开始");
        try {
            serverSocket = new ServerSocket(23333);
            socket1 = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("监听成功");
        while (true){
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
            System.out.println("\nClient - " + str);
        }
    }
    public static void main(String[] args){
        new server();
    }
    public server(){
        System.out.println("测试");
        Thread th = new Thread(this);
        th.start();
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(socket1.getOutputStream(), true);
            printWriter.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
