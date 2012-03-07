import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;


public class Client {
    
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("adb shell am broadcast -a MrPcSync.NotifyServiceStart");
            Thread.sleep(1000);
            Runtime.getRuntime().exec("adb forward tcp:2589 tcp:2589");
            Thread.sleep(1000);
            MrPcSyncHead head =  new MrPcSyncHead();
            Socket socket = new Socket("127.0.0.1", 2589);
            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
            
            while (true){
                
                if (!socket.isConnected()){
                    break;
                }
                head.setHead("NEW_ADD_CONTACT");
                head.setMsg("ssss#110");
                out.write(head.getBuffer());
                out.flush();
                //head.setReadBuffer(head.getBuffer());
                System.out.println("----------------------------");
                Thread.sleep(1000*10);

           }
            socket.close();
            
        } catch (Exception e) {
            System.out.println("Á¬½Ó³¬Ê±");
        }
    }
}
