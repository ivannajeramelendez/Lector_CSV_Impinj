package es.alpha.pack;

import java.sql.Timestamp;

public class Participante {

	private Timestamp inicio;
	private Timestamp fin;
	private short id=0;
	public Timestamp getInicio() {
		return inicio;
	}
	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}
	public Timestamp getFin() {
		return fin;
	}
	public void setFin(Timestamp fin) {
		this.fin = fin;
	}
	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Participante [inicio=" + inicio + ", fin=" + fin + ", id=" + id + "]";
	}
	
}
