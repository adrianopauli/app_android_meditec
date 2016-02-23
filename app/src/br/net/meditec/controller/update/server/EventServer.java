package br.net.meditec.controller.update.server;

import android.content.Context;
import br.net.meditec.controller.dao.DaoEvento;
import br.net.meditec.controller.handler.EventHandler;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class EventServer extends ConnectionServer {
    public EventServer(String xml) {
        super(xml);
    }

    public boolean insertData(Context context) {
        boolean z = false;
        try {
            EventHandler handler = new EventHandler();
            this.xr.setContentHandler(handler);
            this.xr.parse(new InputSource(this.input));
            DaoEvento dao = new DaoEvento(context);
            dao.delete();
            z = dao.insertAll(handler.getEventos());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e2) {
            e2.printStackTrace();
        }
        return z;
    }
}
