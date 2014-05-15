package examples.basic;

import org.json.JSONObject;

/**
 * Created by ceruleanhu on 5/15/14.
 */
public class StreamingEvent {
    StreamingEventType streamingEventType;
    String key;
    JSONObject event;

    public StreamingEvent(StreamingEventType streamingEventType,String key, JSONObject event){
        this.streamingEventType = streamingEventType;
        this.key = key;
        this.event = event;
    }

    public String toString(){
        String streamingEventMsg = "";

        if(streamingEventType!=null){
                streamingEventMsg+="\nstreaming event type: "+streamingEventType.toString();
        }

        if(key!=null && (!key.isEmpty())){
            streamingEventMsg+="\nuser api key: "+key;
        }

        if(event!=null){
            streamingEventMsg+="\nevent details: "+ event.toString();
        }

        return streamingEventMsg;
    }
}
