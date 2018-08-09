
public class ClockPoint {
	private int vNumber;
	private int fNumber;
	private int reference;
	
	
	public ClockPoint(int vNumber, int fNumber, int reference) {
		this.vNumber = vNumber;
		this.fNumber = fNumber;
		this.reference = reference;
	}
	public int getvNumber() {
		return vNumber;
	}
	public void setvNumber(int vNumber) {
		this.vNumber = vNumber;
	}
	public int getfNumber() {
		return fNumber;
	}
	public void setfNumber(int fNumber) {
		this.fNumber = fNumber;
	}
	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
		this.reference = reference;
	}

	
	
}
