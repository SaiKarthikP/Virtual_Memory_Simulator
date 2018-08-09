
public class ClockPageReplacement {
	private static ClockPoint[] list;
	private static int clockPointer;
	
	public static int getClockPointer() {
		return clockPointer;
	}
	public static void setClockPointer(int clockPointer) {
		ClockPageReplacement.clockPointer = clockPointer;
	}
	public void initialize(){
		clockPointer = 0;
		list = new ClockPoint[16];	
		for (int i=0;i<16;i++){
			list[i] = new ClockPoint(-1,i,0);
		}
	}
	public ClockPoint findEvict(){
		ClockPoint cp = list[clockPointer];

		for(int i=0;i<16;i++){
			if(list[clockPointer].getReference()==0){
				return list[clockPointer];
			}
			else if (clockPointer<15) {
				clockPointer++;
			}
			else {
				clockPointer = 0;
			}
		}
		return cp;
	}
	public void update(int vNumber, int pFrame, int ref){
		list[clockPointer].setvNumber(vNumber);
		list[clockPointer].setfNumber(pFrame);
		list[clockPointer].setReference(ref);
		clockPointer++;
		if (clockPointer>15)
			clockPointer=0;
	}
}

