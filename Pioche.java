public class Pioche{
	
	private int hp;
	
	public Pioche(){
		this.hp=10;
	}
	
	public void degatPioche(int quantite){
		this.hp-=quantite;
	}
	
	public int getHp(){
		return hp;
	}
	
	public Pioche clone(){
		return new Pioche();
	}
}