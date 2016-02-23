package br.net.meditec.controller.handler;

import br.net.meditec.model.Evento;
import br.net.meditec.model.Palestrante;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Essa classse faz o mapeamento do XLM do Evento
 * @author Adriano Pauli
 *
 */
public class EventHandler extends DefaultHandler {
    private boolean IsDate;
    private boolean Ispal;
    private String date;
    private String des;
    private String dur;
    private List<Evento> eventos;
    private String fim;
    private String id;
    private String inicio;
    private boolean isDes;
    private boolean isDur;
    private boolean isFim;
    private boolean isId;
    private boolean isInicio;
    private boolean isLocal;
    private boolean isTitle;
    private boolean isType;
    private String local;
    private String pal;
    private String title;
    private String type;

    public EventHandler() {
        this.eventos = new ArrayList<Evento>();
        limpar();
    }

    private void limpar() {
        this.id = "";
        this.title = "";
        this.des = "";
        this.date = "";
        this.dur = "";
        this.inicio = "";
        this.fim = "";
        this.type = "";
        this.local = "";
        this.pal = "";
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("id")) {
            this.isId = true;
        } else if (localName.equals("title")) {
            this.isTitle = true;
        } else if (localName.equals("des")) {
            this.isDes = true;
        } else if (localName.equals("data")) {
            this.IsDate = true;
        } else if (localName.equals("dur")) {
            this.isDur = true;
        } else if (localName.equals("inicio")) {
            this.isInicio = true;
        } else if (localName.equals("fim")) {
            this.isFim = true;
        } else if (localName.equals("type")) {
            this.isType = true;
        } else if (localName.equals("local")) {
            this.isLocal = true;
        } else if (localName.equals("pal")) {
            this.Ispal = true;
        }
        super.startElement(uri, localName, qName, attributes);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("id")) {
            this.isId = false;
        } else if (localName.equals("title")) {
            this.isTitle = false;
        } else if (localName.equals("des")) {
            this.isDes = false;
        } else if (localName.equals("data")) {
            this.IsDate = false;
        } else if (localName.equals("dur")) {
            this.isDur = false;
        } else if (localName.equals("inicio")) {
            this.isInicio = false;
        } else if (localName.equals("fim")) {
            this.isFim = false;
        } else if (localName.equals("type")) {
            this.isType = false;
        } else if (localName.equals("local")) {
            this.isLocal = false;
        } else if (localName.equals("pal")) {
            this.Ispal = false;
        } else if (localName.equals("event")) {
            Evento evento = new Evento(this.title, this.des, this.date.replace("-", "/"), this.dur, this.type);
            evento.setId(Long.parseLong(this.id));
            Palestrante palestrante = new Palestrante();
            palestrante.setId(Long.parseLong(this.pal));
            evento.setPalestrante(palestrante);
            evento.setInicio(this.inicio);
            evento.setFim(this.fim);
            evento.setLocal(this.local);
            this.eventos.add(evento);
            limpar();
        }
        super.endElement(uri, localName, qName);
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        String v = new String(ch, start, length);
        if (this.isId) {
            this.id += v;
        } else if (this.IsDate) {
            this.date += v;
        } else if (this.isDes) {
            this.des += v;
        } else if (this.isDur) {
            this.dur += v;
        } else if (this.isInicio) {
            this.inicio += v;
        } else if (this.isFim) {
            this.fim += v;
        } else if (this.isTitle) {
            this.title += v;
        } else if (this.isType) {
            this.type += v;
        } else if (this.isLocal) {
            this.local += v;
        } else if (this.Ispal) {
            this.pal += v;
        }
        super.characters(ch, start, length);
    }

    public List<Evento> getEventos() {
        return this.eventos;
    }
}
