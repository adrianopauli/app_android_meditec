package br.net.meditec.controller.update.server;

public abstract class Connection {

    public static final String SERVER_HEADER_STATUS = "Status";
    public static final String SERVER_LOGIN_STATUS_NOT = "not";
    public static final String SERVER_LOGIN_STATUS_OK = "ok";
    
    public static final String SERVER_TAG_LOGIN = "login";
    public static final String SERVER_TAG_PASSWORD = "senha";
    
    public static final String SERVER_URL_XMl = "https://raw.githubusercontent.com";
    public static final String SERVER_MY_EVENT = SERVER_URL_XMl+"/adrianopauli/app_android_meditec/master/fake_server/eventos.xml";
    public static final String SERVER_EVENTOS = SERVER_URL_XMl+"/adrianopauli/app_android_meditec/master/fake_server/eventos.xml";
    public static final String SERVER_PALESTRANTES = SERVER_URL_XMl+"/adrianopauli/app_android_meditec/master/fake_server/palestrantes.xml";
}
