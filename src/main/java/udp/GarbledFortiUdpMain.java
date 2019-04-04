package udp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GarbledFortiUdpMain {


    public static void main(String[] args) throws Exception {

        DatagramChannel datagramChannel = DatagramChannel.open();
        sendForti(datagramChannel, System.currentTimeMillis());
    }

    private static void sendForti(DatagramChannel datagramChannel, long time) throws IOException {
        final InetSocketAddress fortiSocketAddress = new InetSocketAddress("localhost", 19001);

        final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        String date1 = sdf1.format(new Date (time));
        String date2 = sdf2.format(new Date (time));
        byte[] fortiLog1 = ("<190>date=" + date1 + " time=" + date2 + " devname=\"11111111\" devid=\"1111111\" logid=\"111111111\" type=\"utm\" subtype=\"virus\" eventtype=\"analytics\" level=\"information\" vd=\"root\" eventtime=1550472247 msg=\"File xxxxx to xxxxx.\" action=\"analytics\" service=\"SMTP\" sessionid=844553113 srcip=111.99.111.37 dstip=10.111.11.111 srcport=51896 dstport=25 srcintf=\"WAN-VLAN100\" srcintfrole=\"wan\" dstintf=\"port9\" dstintfrole=\"dmz\" policyid=25 proto=6 direction=\"outgoing\" filename=\"6080　２０１９年産及び２０    ２０年産の飼料用米及び稲    ＷＣＳの生産に用いる多収    品").getBytes();
        byte[] fortiLog2 = "\" profile=\"SMTP\" from=\"xxxx-xxxx@xxxxxx.jp\" sender=\"xxxxx-xx@xxxxxx.jp\" recipient=\"xxx@xxx.xxx.xxx.xxxx.jp\" analyticscksum=\"1111111111111111111111\" analyticssubmit=\"true\"".getBytes();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(fortiLog1);
        bos.write(0xE7);
        bos.write(fortiLog2);
        byte[] log = bos.toByteArray();

        System.out.println(new String (log, "UTF-8"));
        for(int i = 0; i < log.length; i++) {
            System.out.println(String.format("%02X", log[i]) + " : " + new String (new byte [] {log[i]}));
        }

        sendToShipper(datagramChannel, fortiSocketAddress, log);
    }

    private static void sendToShipper(DatagramChannel datagramChannel, InetSocketAddress inetSocketAddress, byte[] log) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(log.length);
        byteBuffer.clear();
        byteBuffer.put(log);
        byteBuffer.flip();
        datagramChannel.send(byteBuffer, inetSocketAddress);
    }
}
