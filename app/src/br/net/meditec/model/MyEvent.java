package br.net.meditec.model;

import java.util.List;

public class MyEvent {
    private String date;
    private List<Evento> eventos;

    public MyEvent(String data) {
        setDate(data);
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Evento> getEventos() {
        return this.eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
