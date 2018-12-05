package BDA;

/**
 * Allows to create date objects
 */



public class Data implements Comparable<Data> {
	private int dia;
	private int mes;
	private int ano;
	private int hora;
	private int min;
	private int seg;
	

	
	public Data(int dia, int mes, int ano, int hora, int min, int seg) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.hora = hora;
		this.min = min;
		this.seg = seg;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	public int getHora() {
		return hora;
	}

	public int getMin() {
		return min;
	}

	public int getSeg() {
		return seg;
	}

	@Override
	public int compareTo(Data data) {
		if(this.getAno()<data.getAno()){
			return -1;
		}else if((this.getAno()==data.getAno()) && (this.getMes() <data.getMes())){
			return -1;
		}else if((this.getAno()==data.getAno()) && (this.getMes()==data.getMes()) && (this.getDia()<data.getDia())){
			return -1;
		}else if((this.getAno()==data.getAno()) && (this.getMes()==data.getMes()) && (this.getDia()==data.getDia()) && (this.getHora()<data.getHora())){
			return -1;
		}else if((this.getAno()==data.getAno())  && (this.getMes()==data.getMes()) && (this.getDia()==data.getDia()) && (this.getHora()==data.getHora()) && (this.getMin()<data.getMin())){
			return -1;
		}else if((this.getAno()==data.getAno()) && (this.getDia()==data.getDia()) && (this.getHora()==data.getHora()) && (this.getMin()==data.getMin())
				&& (this.getSeg()<data.getSeg())){
			return -1;
		}else if((this.getAno()==data.getAno()) && (this.getDia()==data.getDia()) && (this.getHora()==data.getHora()) && (this.getMin()==data.getMin())
				&& (this.getSeg()==data.getSeg())){
			return 0;
		}else {
			return 1;
		}
		
	}

	@Override
	public String toString() {
		return "Data [dia=" + dia + ", mes=" + mes + ", ano=" + ano + ", hora=" + hora + ", min=" + min + ", seg=" + seg
				+ "]";
	}
	
	
	
	
}
