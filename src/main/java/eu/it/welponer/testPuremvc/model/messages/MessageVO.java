/*
 * Copyright (C) 2020  Michele Welponer, mwelponer@gmail.com (Fondazione Bruno Kessler)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/> and file GPL3.txt
 */

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
