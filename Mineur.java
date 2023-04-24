public class Mineur extends Agent{
	
	public static int id_mineur = 0;
	public int nbr_pioche;
	
	public Mineur(int x, int y, int pioche){
		super(x,y);
		id_mineur++;
		this.nbr_pioche=pioche;
	}
	
	public int getNbrPioche(){
		 return super.getNbrPioche()+nbr_pioche;
	}
	
	public String toString(){
		String s=super.toString()+"Mineur "+id_mineur;
		return s;
	}
}