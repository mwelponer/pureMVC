package eu.it.welponer.testPuremvc.model.messages;

import eu.it.welponer.testPuremvc.model.client.RequestMethod;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.net.InetAddress;

public class MessageVO {

    @Getter @Setter
    private RequestMethod method;
    @Getter @Setter
    private String targetURL;
    @Getter @Setter
    private long timestamp;
    @Getter @Setter
    private InetAddress clientIP;
    @Getter @Setter
    private JSONObject jsonObject;
//    private float coordX;
//    @Getter @Setter
//    private float coordY;

    // targetURL -> "http://localhost:9000"
    // payload -> "{\"coordX\":\"1.0\", \"coordY\":\"1.0\"}"
    //{"coordX":"1.0", "coordY":"1.0"}

//    JSONObject payload = new JSONObject();
//    payload.put("coordX", "1.0");
//    payload.put("coordY", "2.0");
}
