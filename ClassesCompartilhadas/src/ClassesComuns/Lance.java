package ClassesComuns;

import InterfacesComuns.Throw;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Lance implements Jogada, Throw {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	String z;
	Peca p;
	int n;
	int origX;
	int origY;
	String jogadorDoLance;
	
	

	public Lance(int x, int y, String z, int n, String jogadorDoLance) {
		super();

		this.x = x;
		this.y = y;
		this.z = z;
		this.n = n;
		this.jogadorDoLance = jogadorDoLance;
	}
	
	public Lance(int x, int y, Peca p, String jogadorDoLance) {
		super();

		this.x = x;
		this.y = y;
		this.p = p;
		this.jogadorDoLance = jogadorDoLance;

	}

	public Lance(int x, int y, int origX, int origY, Peca p, String jogadorDoLance) {
		super();

		this.x = x;
		this.y = y;
		this.p = p;
		this.origX = origX;
		this.origY = origY;
		this.jogadorDoLance = jogadorDoLance;

	}

	public int getX() {
		return this.x;

	}

	public int getY() {
		return this.y;
	}

	public String getZ() {
		return this.z;

	}

	public int getN() {

		return this.n;
	}

	public Peca getP() {

		return this.p;
	}

	public int getOrigX() {
		return origX;
	}

	public int getOrigY() {
		return origY;
	}

	public String getJogadorDoLance() {
		return jogadorDoLance;
	}

}
