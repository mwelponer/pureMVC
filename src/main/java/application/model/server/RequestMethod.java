package application.model.server;

public enum RequestMethod {
    GET(0),
    HEAD(1),
    POST(2),
    PUT(3),
    PATCH(4),
    DELETE(5),
    OPTIONS(6),
    TRACE(7);

    public final int index;

    RequestMethod(int index){
        this.index = index;
    }

    public static RequestMethod parse(int index){
        if(index == 0)
            return GET;
        if(index == 1)
            return HEAD;
        if(index == 2)
            return POST;
        if(index == 3)
            return PUT;
        if(index == 4)
            return PATCH;
        if(index == 5)
            return DELETE;
        if(index == 6)
            return OPTIONS;
        if(index == 7)
            return TRACE;

        return POST;
    }
}
