package examples.basic;

/**
 * Created by ceruleanhu on 5/15/14.
 */
public enum StreamingEventType {
    PUBLIC_TICK, PUBLIC_ORDER_BOOK, PUBLIC_TRADES, PRIVATE;

    public static String getEventName(StreamingEventType streamingEventType){
        String eventName="";

        switch (streamingEventType){
            case PUBLIC_TICK:
                eventName = "public/tick/";
                break;
            case PUBLIC_ORDER_BOOK:
                eventName = "public/orderBook/";
                break;
            case PUBLIC_TRADES:
                eventName = "public/trades/";
                break;
            case PRIVATE:
                eventName = "private";
                break;

        }

        return eventName;
    }
}
