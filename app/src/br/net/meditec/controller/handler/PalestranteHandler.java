package br.net.meditec.controller.handler;

import br.net.meditec.model.Palestrante;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author Adriano Pauli
 *
 */
public class PalestranteHandler extends DefaultHandler {
    private String des;
    private String id;
    private boolean isDes;
    private boolean isId;
    private boolean isName;
    private String name;
    private List<Palestrante> palestrantes;

    public PalestranteHandler() {
        this.palestrantes = new ArrayList<Palestrante>();
        limpar();
    }

    private void limpar() {
        this.id = "";
        this.name = "";
        this.des = "";
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("id")) {
            this.isId = true;
        } else if (localName.equals("name")) {
            this.isName = true;
        } else if (localName.equals("des")) {
            this.isDes = true;
        }
        super.startElement(uri, localName, qName, attributes);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("id")) {
            this.isId = false;
        } else if (localName.equals("name")) {
            this.isName = false;
        } else if (localName.equals("des")) {
            this.isDes = false;
        } else if (localName.equals("pal")) {
            Palestrante palestrante = new Palestrante(this.name, this.des);
            palestrante.setId(Long.parseLong(this.id));
            this.palestrantes.add(palestrante);
            limpar();
        }
        super.endElement(uri, localName, qName);
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        String v = new String(ch, start, length);
        if (this.isId) {
            this.id += v;
        } else if (this.isName) {
            this.name += v;
        } else if (this.isDes) {
            this.des += v;
        }
        super.characters(ch, start, length);
    }

    public List<Palestrante> getPalestrantes() {
        return this.palestrantes;
    }
}
