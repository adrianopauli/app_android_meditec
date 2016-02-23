package br.net.meditec.model;

public class Palestrante {
    private String Description;
    private String Name;
    private long id;

    public Palestrante() {
		// TODO Auto-generated constructor stub
	}
    
    public Palestrante(String name, String description) {
        this.Name = name;
        this.Description = description;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}
