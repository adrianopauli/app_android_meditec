package br.net.meditec.controller.update.server;

public abstract class Connection {

    public static final String SERVER_HEADER_STATUS = "Status";
    public static final String SERVER_LOGIN_STATUS_NOT = "not";
    public static final String SERVER_LOGIN_STATUS_OK = "ok";
    
    public static final String SERVER_TAG_LOGIN = "login";
    public static final String SERVER_TAG_PASSWORD = "senha";
    
    public static final String SERVER_URL_XMl = "http://192.168.3.101:8080";
    public static final String SERVER_MY_EVENT = SERVER_URL_XMl+"/autenticacao/autenticacao.php";
    public static final String SERVER_EVENTOS = SERVER_URL_XMl+"/meditec/eventos.xml";
    public static final String SERVER_PALESTRANTES = SERVER_URL_XMl+"/meditec/palestrantes.xml";
}
