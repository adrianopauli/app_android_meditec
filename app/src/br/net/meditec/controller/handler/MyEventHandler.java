package br.net.meditec.controller.handler;

import android.util.Log;

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
public class MyEventHandler extends DefaultHandler {
    private List<String> events;
    private String id;
    private boolean isId;

    public MyEventHandler() {
        this.events = new ArrayList<String>();
        this.id = "";
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("id")) {
            this.isId = true;
        }
        super.startElement(uri, localName, qName, attributes);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("id")) {
            this.isId = false;
            this.events.add(this.id);
            Log.d("event", this.id);
            this.id = "";
        }
        super.endElement(uri, localName, qName);
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        String v = new String(ch, start, length);
        if (this.isId) {
            this.id += v;
        }
        super.characters(ch, start, length);
    }

    public List<String> getEvents() {
        return this.events;
    }
}
