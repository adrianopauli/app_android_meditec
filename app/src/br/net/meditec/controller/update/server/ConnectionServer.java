package br.net.meditec.controller.update.server;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public abstract class ConnectionServer {
    protected InputStream input;
    private String xml;
    protected XMLReader xr;

    public abstract boolean insertData(Context context) throws IOException, SAXException;

    public ConnectionServer(String xml) {
        this.xml = xml;
    }

    public boolean connection() {
        try {
            URL url = new URL(this.xml);
            Log.d(url.getHost(), url.getFile());
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.connect();
            this.input = conexao.getInputStream();
            this.xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            return true;
        } catch (ConnectException e) {
            e.printStackTrace();
            return false;
        } catch (ParserConfigurationException e2) {
            e2.printStackTrace();
            return false;
        } catch (SAXException e3) {
            e3.printStackTrace();
            return false;
        } catch (IOException e4) {
            e4.printStackTrace();
            return false;
        }
    }
}
