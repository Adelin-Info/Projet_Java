public class Agent{

    protected int x;
    protected int y;
    

    public Agent(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int distance(int x, int y){
        int d = (int)Math.sqrt(((int)(Math.pow(x-this.x,2)))+((int)(Math.pow(y-this.y,2))));
        return d;
    }
	
	public void seDeplacer(int xnew, int ynew){
		this.x=xnew;
		this.y=ynew;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getNbrPioche(){
		return 0;
	}
	
	public String toString(){
		String s = "";
		return s;
	}
	
}