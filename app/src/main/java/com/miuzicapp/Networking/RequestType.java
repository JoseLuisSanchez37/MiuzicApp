package com.miuzicapp.Networking;

/**
 * Created by jose.sanchez on 06/09/2015.
 */
public enum RequestType {
    GET_USER    (0, "getUsers"),
    SIGN_IN     (1, "signIn"),
    SIGN_UP     (2, "signUp"),
    GET_NEWS    (3, "getNews");

    private final int id;
    private final String method;

    private RequestType(final int id, final String method){
        this.id = id;
        this.method = method;
    }

    public int getId(){
        return id;
    }

    public String getMethod(){
        return method;
    }

}
