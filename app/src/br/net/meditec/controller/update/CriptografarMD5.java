package br.net.meditec.controller.update;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografarMD5 {
    private static MessageDigest messageDigest;

    static {
        messageDigest = null;
    }

    public CriptografarMD5() {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static char[] hexCodigos(byte[] chave) {
        char[] retorno = new char[(chave.length * 2)];
        for (int i = 0; i < chave.length; i++) {
            String hexString = "00" + Integer.toHexString(chave[i]);
            hexString.toUpperCase().getChars(hexString.length() - 2, hexString.length(), retorno, i * 2);
        }
        return retorno;
    }

    public String criptografar(String texto) {
        if (messageDigest != null) {
            return new String(hexCodigos(messageDigest.digest(texto.getBytes())));
        }
        return null;
    }
}
