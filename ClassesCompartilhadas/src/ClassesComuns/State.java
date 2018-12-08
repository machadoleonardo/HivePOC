package ClassesComuns;

import InterfacesComuns.Throw;
import br.ufsc.inf.leobr.cliente.Jogada;

public class State implements Throw, Jogada {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String stateMessage;
	protected int boardFilling[][] = new int [3][3];
	
	public String getStateMessage() {
		return stateMessage;
	}
	
	public void setStateMessage(String text) {
		stateMessage = text;
	}
	 
	public int getValue(int line, int column) {
		int ret;
		ret = boardFilling [(line-1)][(column-1)];
		return (ret);
	}
	 	 
	public void setValue(int line, int column, int value) {
		boardFilling [(line-1)][(column-1)] = value;
	}
	 
	public boolean informEmpty() {
		boolean empty = true;
		for (int line=1; line<4; line++){
			for (int column=1; column<4; column++){
				if (this.InformOccupiedPosition(line, column)) {
					empty = false;
				};
			};
		};
		return empty;
	}
	 
	public boolean informEmptyMiddle() {
		return (this.informEmptyPosition(2, 2));
	}
	 
	public boolean informEmptyPosition(int line, int column) {
		return ((this.getValue(line, column)) == 0);
	}
	 
	public boolean InformOccupiedPosition(int line, int column) {
		return ((this.getValue(line, column)) != 0);
	}
	 
	public int occupiedInLine(int ord) {
		int cont = 0;
		for (int column=1; column<4; column++){
			if (this.InformOccupiedPosition(ord, column)) {
				cont++;
			};
		};
		return cont;
	}
	 
	public int occupiedInColumn(int ord) {
		int cont = 0;
		for (int line=1; line<4; line++){
			if (this.InformOccupiedPosition(line, ord)) {
				cont++;
			};
		};
		return cont;
	}

}
