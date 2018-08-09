import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhysicalMem {
	private static int[][] memArray = new int[16][256];
	private static int evictPointer =0;
	
	public int get(int x, int y){
		return memArray[x][y];
	}
	public void set(int x, int y, int data){
		 memArray[x][y] = data;
	}
	public void setPage(int oldPFrame, String fileName) throws FileNotFoundException{
		File file = new File(fileName);
		Scanner s = new Scanner(file);
		for(int i=0; i < 256; i++){
			memArray[oldPFrame][i] = s.nextInt();
		}
		s.close();
		
	}
	public static int getEvictPointer() {
		return evictPointer;
	}
	public static void setEvictPointer(int evictPointer) {
		PhysicalMem.evictPointer = evictPointer;
	}
}
