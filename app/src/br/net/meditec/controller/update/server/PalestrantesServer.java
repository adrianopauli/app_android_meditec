package br.net.meditec.controller.update.server;

import android.content.Context;
import br.net.meditec.controller.dao.DaoPalestrante;
import br.net.meditec.controller.handler.PalestranteHandler;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class PalestrantesServer extends ConnectionServer {
    public PalestrantesServer(String xml) {
        super(xml);
    }

    public boolean insertData(Context context) throws IOException, SAXException {
        boolean z = false;
        try {
            PalestranteHandler handler = new PalestranteHandler();
            this.xr.setContentHandler(handler);
            this.xr.parse(new InputSource(this.input));
            DaoPalestrante dao = new DaoPalestrante(context);
            dao.delete();
            z = dao.insertAll(handler.getPalestrantes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e2) {
            e2.printStackTrace();
        }
        return z;
    }
}
