import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            MrPcSyncHead head =  new MrPcSyncHead();
            ServerSocket mSocket = new ServerSocket(2589);
            byte[] buffer = new byte[MrPcSyncHead.getLength()];
            
            Socket socket = mSocket.accept();
            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
            
            while (true){
                if (socket.isConnected()){
                    in.read(buffer);
                    head.setReadBuffer(buffer);
                    //System.out.println(head.getHeadString());
                    //System.out.println(head.getMsgString());
                    
                    System.out.println(head.getMsgCmd()[0]);
                    System.out.println(head.getMsgCmd()[1]);
                    //System.out.println(head.getMsgCmd()[2]);
                    
                }else{
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
