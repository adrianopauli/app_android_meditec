package br.net.meditec.controller.update;

import android.content.Context;
import android.util.Log;
import br.net.meditec.controller.dao.DaoUserHasEvents;
import br.net.meditec.controller.handler.MyEventHandler;
import br.net.meditec.controller.update.server.Connection;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public abstract class Login {
    public static final String SERVER_ERROR = "error_servidor";
    public static final String STR_OK = "ok";
    public static final String USER_INVALIDO = "error_usuario";

    public static String execute(String login, String senha, Context context) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(Connection.SERVER_MY_EVENT);
            Log.d(login, senha);
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair(Connection.SERVER_TAG_LOGIN, login));
            parameters.add(new BasicNameValuePair(Connection.SERVER_TAG_PASSWORD, senha));
            httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            try {
                XMLReader xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
                MyEventHandler handler = new MyEventHandler();
                xr.setContentHandler(handler);
                xr.parse(new InputSource(response.getEntity().getContent()));
                DaoUserHasEvents daoUserHasEvents = new DaoUserHasEvents(context);
                daoUserHasEvents.delete();
                daoUserHasEvents.insertAll(handler.getEvents());
                return STR_OK;
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
                return USER_INVALIDO;
            } catch (SAXException e2) {
                e2.printStackTrace();
                return USER_INVALIDO;
            }
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
            return SERVER_ERROR;
        } catch (ClientProtocolException e4) {
            e4.printStackTrace();
            return SERVER_ERROR;
        } catch (IOException e5) {
            e5.printStackTrace();
            return SERVER_ERROR;
        }
    }
}
