public class Ouvrier extends Agent{
	
	public static int id_ouvrier = 0;
	
	public Ouvrier(int x, int y){
		super(x,y);
		id_ouvrier++;
	}
	
	public String toString(){
		String s="Ouvrier "+id_ouvrier+" fabrique";
		
		return s;
	}
}