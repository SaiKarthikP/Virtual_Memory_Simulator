
public class PageEntry {

	private int Valid;
	private int Ref;
	private int Dirty;
	private int PFrame;
	
	public PageEntry(int Valid, int Ref, int Dirty, int PFrame){
		this.Valid = Valid;
		this.Ref = Ref;
		this.Dirty = Dirty;
		this.PFrame = PFrame;
	}
	public int getValid() {
		return Valid;
	}

	public int getRef() {
		return Ref;
	}

	public int getDirty() {
		return Dirty;
	}

	public int getPFrame() {
		return PFrame;
	}

	public void setValid(int valid) {
		Valid = valid;
	}

	public void setRef(int ref) {
		Ref = ref;
	}

	public void setDirty(int dirty) {
		Dirty = dirty;
	}

	public void setPFrame(int pFrame) {
		PFrame = pFrame;
	}
	
}
