package br.net.meditec.model;


/**
 * 
 * @author Adriano Pauli
 *
 */
public class Evento {
	public static final String MY_EVENT = "Meus Eventos";
	public static final String SELECIONE_DATE = "Atualize a Agenda";
	private String date;
	private String description;
	private String duration;
	private String fim;
	private long id;
	private String inicio;
	private String local;
	private Palestrante palestrante;
	private String title;
	private String type;

	public Evento() {
		// TODO Auto-generated constructor stub
	}

	public Evento(String title, String description, String date,
			String duration, String type) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.duration = duration;
		this.type = type;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getInicio() {
		return this.inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return this.fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Palestrante getPalestrante() {
		return this.palestrante;
	}

	public void setPalestrante(Palestrante palestrante) {
		this.palestrante = palestrante;
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
}
