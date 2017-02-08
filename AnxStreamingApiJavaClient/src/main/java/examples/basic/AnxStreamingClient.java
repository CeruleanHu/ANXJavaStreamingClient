package examples.basic;
/*
 * socket.io-java-client Test.java
 *
 * Copyright (c) 2012, Enno Boland
 * socket.io-java-client is a implementation of the socket.io protocol in Java.
 * 
 * See LICENSE file for more information
 */

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.String;

public class AnxStreamingClient{
    private Socket socket;

    String key="fd012755-ed0a-4740-ab16-f8dda02913a7";
    String secret="HpicWK9k/425hqLUF/kluflK5N9rME4xVYYlM7Ux/uJ7UZa1PV1iyeEFovKg6hl/Q59/j00+Fewl0xQMlCh85A==";


    /**
     * NOTE BIEN: need to run https://github.com/btcdude/anx-sample/blob/master/wsproxy.js first before running this project
     * steps to get above wsproxy.js running
     * 0. install node or nodejs (will use nodejs for example) as well as node package manager (npm)
     * 1. git clone https://github.com/btcdude/anx-sample.git
     * 2. cd to anx-sample repository
     * 3. npm install
     * 4. nodejs wsproxy.js
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            new AnxStreamingClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnxStreamingClient() throws Exception {
        socket = IO.socket("http://127.0.0.1:9990/");

        // Sends a string to the server.
//		socket.send("Hello Server");
//
//		// Sends a JSON object to the server.
//		socket.send(new JSONObject().put("key", "value").put("key2",
//				"another value"));

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Connection established");
            }
        }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("an Error occured");
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Connection terminated");
            }
        });

        String[] publicTopics = {"public/tick/ANX/BTCUSD","public/orderBook/ANX/BTCUSD","public/trades/ANX/BTCUSD"};
        JSONObject publicTopicJson = new JSONObject().
                put("secret", secret).
                put("key",key).
                put("topics", publicTopics);

        // Emits an event to the server to subscribe public streaming events
        socket.emit("subscribe", publicTopicJson);

        String[] privateTopics = {"private"};
        JSONObject privateTopicJson = new JSONObject().
                put("secret", secret).
                put("key",key).
                put("topics", privateTopics);

        // Emits an event to the server to subscribe private streaming events only available to user with specified api key and secret
        socket.emit("subscribe", privateTopicJson);

        socket.on("public/tick/ANX/BTCUSD", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Server triggered streaming event '" + StreamingEventType.getEventName(StreamingEventType.PUBLIC_TICK) + "', args[0]: " + args[0].toString());

                StreamingEvent streamingEvent = parseStreamingEventRawData(StreamingEventType.getEventName(StreamingEventType.PUBLIC_TICK), (JSONObject) args[0]);
            }
        });
        socket.on("public/orderBook/ANX/BTCUSD", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Server triggered streaming event '" + StreamingEventType.getEventName(StreamingEventType.PUBLIC_ORDER_BOOK) + "', args[0]: " + args[0].toString());

                StreamingEvent streamingEvent = parseStreamingEventRawData(StreamingEventType.getEventName(StreamingEventType.PUBLIC_ORDER_BOOK), (JSONObject) args[0]);
            }
        });
        socket.on("public/trades/ANX/BTCUSD", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Server triggered streaming event '" + StreamingEventType.getEventName(StreamingEventType.PUBLIC_TRADES) + "', args[0]: " + args[0].toString());

                StreamingEvent streamingEvent = parseStreamingEventRawData(StreamingEventType.getEventName(StreamingEventType.PUBLIC_TRADES), (JSONObject) args[0]);
            }
        });

        socket.on("private", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Server triggered streaming event '" +StreamingEventType.getEventName(StreamingEventType.PRIVATE) + "', args[0]: " + args[0].toString());

                StreamingEvent streamingEvent = parseStreamingEventRawData(StreamingEventType.getEventName(StreamingEventType.PRIVATE), (JSONObject) args[0]);
            }
        });

        socket.connect();

    }

    private StreamingEvent parseStreamingEventRawData(String event, JSONObject rawData){
        StreamingEvent streamingEvent = null;

        StreamingEventType[] streamingEventTypes = StreamingEventType.values();
        try{

            for (StreamingEventType eventType: streamingEventTypes){
                //if event belongs to eventType
                if(event.contains(StreamingEventType.getEventName(eventType))){
                    JSONObject eventData = rawData;
                    String userApiKey = eventData.get("key").toString();
                    JSONObject eventDetails = (JSONObject)eventData.get("event");

                    streamingEvent = new StreamingEvent(eventType,userApiKey,eventDetails);
                    System.out.println("streaming event: "+streamingEvent.toString());

                }
            }

            if(streamingEvent==null){
                System.out.println("event : "+event +" - does not contain any of "+StreamingEventType.values().toString());
            }

        }catch (Exception e){

            System.out.println("Server triggered streaming event '" + event + "' get exception "+ e.getMessage() +" when parsing");
            //TODO customized exception handling

        }finally {

            return streamingEvent;
        }
    }

}


