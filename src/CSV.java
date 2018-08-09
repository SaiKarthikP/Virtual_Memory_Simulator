import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSV {
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "Virtual Address, Read/Write, Soft Miss, Hard Miss, Hit, Dirty bit, Value";
	private static String filename;
	
	private static int TdiskAccess=0;
	private static int TsoftMiss=0;
	private static int ThardMiss=0;
	private static int Thits=0;
	private static int ThdRead=0;
	private static int ThdWrite=0;
	
	private static String vAddress ="";
	private static int rw = 0;
	private static int softMiss = 0;
	private static int hardMiss = 0;
	private static int hit =0;
	private static int dbit =-1;
	private static int value =0;
	
	public void header() throws IOException{
		File file = new File(filename);

        FileWriter  fw = new FileWriter(file);
        fw.append(FILE_HEADER.toString());
		fw.append(NEW_LINE_SEPARATOR);

		fw.close();
	}
	
	public void totals() throws IOException{
		File file = new File(filename);

        FileWriter  fw = new FileWriter(file, true);
        fw.append("Totals:");
		fw.append(NEW_LINE_SEPARATOR);
		fw.append("Total Disk Accesses: " + "," + TdiskAccess);
		fw.append(NEW_LINE_SEPARATOR);
		fw.append("Total Soft Misses " + "," + TsoftMiss);
		fw.append(NEW_LINE_SEPARATOR);
		fw.append("Total Hard Misses: " + "," + ThardMiss);
		fw.append(NEW_LINE_SEPARATOR);
		fw.append("Total Hits: " + "," + Thits);
		fw.append(NEW_LINE_SEPARATOR);
		fw.append("Total Disk Reads: " + "," + ThdRead);
		fw.append(NEW_LINE_SEPARATOR);
		fw.append("Total Disk Writes: " + "," + ThdWrite);
		fw.close();
	}
	
	public void print() throws IOException{
		File file = new File(filename);
		FileWriter fw = new FileWriter(file, true);
		fw.append(vAddress);
		fw.append(COMMA_DELIMITER);
		fw.append(rw + "");
		fw.append(COMMA_DELIMITER);
		fw.append(softMiss + "");
		fw.append(COMMA_DELIMITER);
		fw.append(hardMiss + "");
		fw.append(COMMA_DELIMITER);
		fw.append(hit + "");
		fw.append(COMMA_DELIMITER);
		fw.append(dbit + "");
		fw.append(COMMA_DELIMITER);
		fw.append(value + "");
		fw.append(NEW_LINE_SEPARATOR);
		
		fw.close();
	}
	public void output(){
		
	}
	public void incThdRead(){
		ThdRead++;
		TdiskAccess++;
	}
	public void incThdWrite(){
		ThdWrite++;
		TdiskAccess++;
	}
	public void CSVoutput(){
		//print for each address
		
	}

	public String getvAddress() {
		return vAddress;
	}

	public int getRw() {
		return rw;
	}

	public int getSoftMiss() {
		return softMiss;
	}

	public int getHardMiss() {
		return hardMiss;
	}

	public int getHit() {
		return hit;
	}

	public int getDbit() {
		return dbit;
	}

	public int getValue() {
		return value;
	}

	public void setvAddress(String vAddress) {
		this.vAddress = vAddress;
	}

	public void setRw(int rw) {
		this.rw = rw;
	}

	public void setSoftMiss(int softMiss) {
		this.softMiss = softMiss;
		TsoftMiss++;
	}

	public void setHardMiss(int hardMiss) {
		this.hardMiss = hardMiss;
		ThardMiss++;
		TsoftMiss--;
	}

	public void setHit(int hit) {
		this.hit = hit;
		Thits++;
	}

	public void setDbit(int dbit) {
		this.dbit = dbit;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static String getFilename() {
		return filename;
	}

	public static void setFilename(String filename) {
		CSV.filename = filename;
	}


}
