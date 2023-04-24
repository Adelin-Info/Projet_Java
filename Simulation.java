public class Simulation{
	public int x;
	public int y;
	private int ascenseur_x;
	private int ascenseur_y;
	private Terrain mine;
	private Terrain usine;
	private int nbr_agent;
	private Agent[][] tab_agent;
	private int nbr_ressource;
	private Ressource[][] tab_ressource;
	private Ressource[] tab_stock;
	private Pioche pioche;
	private Pioche[] tab_pioche;
	
	public Simulation(int x, int y,int n,int m){
		this.x=x;
		this.y=y;
		this.ascenseur_x=(int)(Math.random()*x);
		this.ascenseur_y=(int)(Math.random()*y);
		
		this.mine=new Terrain(x,y);
		this.usine= new Terrain(3,4);
		
		this.nbr_agent=n;
		tab_agent=new Agent[2][];
		for(int i=0;i<2;i++){
			tab_agent[i]=new Agent[20];
		}
		
		this.nbr_ressource=m;
		tab_ressource=new Ressource[5][];
		tab_ressource[0]=new Ressource[1];
		for(int i=1;i<5;i++){
			tab_ressource[i]=new Ressource[19];
		}
		
		tab_stock=new Ressource[8];
		
		pioche = new Pioche();
		tab_pioche=new Pioche[5];
		
	}
	
	public void terrainIni(){
		//La mine est remplie de pierre
		Ressource pierre=new Ressource("rock",1);
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				mine.setCase(i,j,pierre);
			}
		}
		//Création des ressources
		tab_ressource[0][0]=pierre;
		int coord_x;
		int coord_y;
		boolean b;
		
		for(int i=0;i<nbr_ressource;i++){
			b=false;
			int random_ressource=(int)(Math.random()*10);
			
			coord_x=(int)(Math.random()*x);
			coord_y=(int)(Math.random()*y);
			if((random_ressource>=0) && (random_ressource<4)){
				Ressource charbon=new Ressource("COAL",(int)(Math.random()*10+1));
				for(int j=0;j<19;j++){
					if(tab_ressource[1][j]==null){
						tab_ressource[1][j]=charbon;
						while (b==false){
							coord_x=(int)(Math.random()*x);
							coord_y=(int)(Math.random()*y);
							if(mine.getCase(coord_x,coord_y)==pierre){
								b=true;
								mine.setCase(coord_x,coord_y,tab_ressource[1][j]);
							}
						}
						
						
						break;
					}
				}
				
			} else if ((random_ressource>=4) && (random_ressource<7)){
				Ressource fer=new Ressource("IRON",(int)(Math.random()*5+1));
				for(int j=0;j<19;j++){
					if(tab_ressource[2][j]==null){
						tab_ressource[2][j]=fer;
						while (b==false){
							coord_x=(int)(Math.random()*x);
							coord_y=(int)(Math.random()*y);
							if(mine.getCase(coord_x,coord_y)==pierre){
								b=true;
								mine.setCase(coord_x,coord_y,tab_ressource[2][j]);
							}
						}
						
						
						break;
					}
				}
				
			} else if ((random_ressource>=7) && (random_ressource<9)){
				Ressource cuivre=new Ressource("COPPER",(int)(Math.random()*3+1));
				for(int j=0;j<19;j++){
					if(tab_ressource[3][j]==null){
						tab_ressource[3][j]=cuivre;
						while (b==false){
							coord_x=(int)(Math.random()*x);
							coord_y=(int)(Math.random()*y);
							if(mine.getCase(coord_x,coord_y)==pierre){
								b=true;
								mine.setCase(coord_x,coord_y,tab_ressource[3][j]);
							}
						}
						
						
						break;
					}
				}
				
			} else if (random_ressource==9){
				Ressource or=new Ressource("GOLD",(int)(Math.random()*1+1));
				for(int j=0;j<19;j++){
					if(tab_ressource[4][j]==null){
						tab_ressource[4][j]=or;
						while (b==false){
							coord_x=(int)(Math.random()*x);
							coord_y=(int)(Math.random()*y);
							if(mine.getCase(coord_x,coord_y)==pierre){
								b=true;
								mine.setCase(coord_x,coord_y,tab_ressource[4][j]);
							}
						}
						

						break;
					}
				}
				
			}
		}
		
		//Création de l'ascenseur
		int case_valide=0;
		while (case_valide==0){
			
			if (mine.getCase(ascenseur_x,ascenseur_y)==pierre){
				mine.videCase(ascenseur_x,ascenseur_y);
				case_valide=1;
			}
		}
		//Création des agents
		Agent mineur_1=new Mineur(ascenseur_x,ascenseur_y,(int)(Math.random()*(3)+1));
		tab_agent[0][0]=mineur_1;
		Agent ouvrier_1=new Ouvrier(0,2);
		tab_agent[1][0]=ouvrier_1;
		for(int n_agent=2;n_agent<nbr_agent;n_agent++){
			
				int random_agent=(int)(Math.random()*4);
				if ((random_agent>=0) && (random_agent<3)){
					Agent mineur=new Mineur(ascenseur_x,ascenseur_y,(int)(Math.random()*(3)+1));
					for(int j=0;j<20;j++){
						if(tab_agent[0][j]==null){
							tab_agent[0][j]=mineur;
							break;
						}
					}
				} else {
					Agent ouvrier=new Ouvrier(0,2);
					for(int j=0;j<20;j++){
						if(tab_agent[1][j]==null){
							tab_agent[1][j]=ouvrier;
							break;
						}
					}
				}
		}
		
		
		
		//Création des stocks des différentes ressources
		Ressource stock_de_pierre=new Ressource("rock",0);
		usine.setCase(0,0,stock_de_pierre);
		tab_stock[0]=stock_de_pierre;
		
		Ressource stock_de_charbon=new Ressource("COAL",0);
		usine.setCase(1,0,stock_de_charbon);
		tab_stock[1]=stock_de_charbon;
		
		Ressource stock_de_fer=new Ressource("IRON",0);
		usine.setCase(2,0,stock_de_fer);
		tab_stock[2]=stock_de_fer;
		
		Ressource stock_de_cuivre=new Ressource("COPPER",0);
		usine.setCase(0,1,stock_de_cuivre);
		tab_stock[3]=stock_de_cuivre;
		
		Ressource stock_or=new Ressource("GOLD",0);
		usine.setCase(1,1,stock_or);
		tab_stock[4]=stock_or;
		
		Ressource lingot_de_fer=new Ressource("LdeF",0);
		usine.setCase(0,3,lingot_de_fer);
		tab_stock[5]=lingot_de_fer;
		
		Ressource lingot_de_cuivre=new Ressource("LdeC",0);
		usine.setCase(1,3,lingot_de_cuivre);
		tab_stock[6]=lingot_de_cuivre;
		
		Ressource lingot_or=new Ressource("Ld'OR",0);
		usine.setCase(2,3,lingot_or);
		tab_stock[7]=lingot_or;
		
		
		for(int tab_ress=1;tab_ress<tab_ressource.length;tab_ress++){
				for(int ress=0;ress<tab_ressource[tab_ress].length;ress++){
		
				if(tab_ressource[tab_ress][ress]!=null){
					System.out.println(tab_ressource[tab_ress][ress]);
				}
			}
		}
		
		//affichage de la mine et de l'usine après initialisation
		System.out.println("MINE :");
		mine.affiche();
		System.out.println("USINE :");
		usine.affiche();
	}
	
	public void creuserX_Y(int agent){
		if(mine.getCase(tab_agent[0][agent].getX(),tab_agent[0][agent].getY())==tab_ressource[0][0]){
			//degat pioche
			tab_stock[0].setQuantite(tab_stock[0].getQuantite()+tab_ressource[0][0].getQuantite());
			mine.videCase(tab_agent[0][agent].getX(),tab_agent[0][agent].getY());
		} else if(mine.caseEstVide(tab_agent[0][agent].getX(),tab_agent[0][agent].getY())==false){
			for(int tab_ress=1;tab_ress<tab_ressource.length;tab_ress++){
				for(int ress=0;ress<tab_ressource[tab_ress].length;ress++){
					if(tab_ressource[tab_ress][ress]!=null){
						if(mine.getCase(tab_agent[0][agent].getX(),tab_agent[0][agent].getY())==tab_ressource[tab_ress][ress]){
							//degat pioche
							tab_stock[tab_ress].setQuantite(tab_stock[tab_ress].getQuantite()+tab_ressource[tab_ress][ress].getQuantite());
							System.out.println(tab_ressource[tab_ress][ress]+" a été récolté");
							mine.videCase(tab_agent[0][agent].getX(),tab_agent[0][agent].getY());
							
						}
					} 
				}
			}
		}
	}
	
	public void transformer(int ouv){
		
		if (ouv==0){
			tab_agent[1][ouv].seDeplacer(2,0);
			if(usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).getQuantite()%2==0){
				
				tab_stock[5].setQuantite(tab_stock[5].getQuantite()+((usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).getQuantite())/2));
				usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).setQuantite(0);
			}else{
				
				tab_stock[5].setQuantite(tab_stock[5].getQuantite()+((usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).getQuantite()-1)/2));
				usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).setQuantite(1);
			}
		} else if (ouv==1){
			tab_agent[1][ouv].seDeplacer(0,1);
			if(usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).getQuantite()%2==0){
				
				tab_stock[6].setQuantite(tab_stock[6].getQuantite()+((usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).getQuantite())/2));
				usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).setQuantite(0);
			}else{
				
				tab_stock[6].setQuantite(tab_stock[6].getQuantite()+((usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).getQuantite()-1)/2));
				usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).setQuantite(1);
			}
		} else if (ouv==2){
			System.out.println("2");
			tab_agent[1][ouv].seDeplacer(1,1);
			tab_stock[7].setQuantite(tab_stock[7].getQuantite()+usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).getQuantite());
			usine.getCase(tab_agent[1][ouv].getX(),tab_agent[1][ouv].getY()).setQuantite(0);
		}
		
	}
	
	
	public void creuser(){
		
		
		int x_ressource=0;
		int y_ressource=0;
		boolean b;
		for(int agent=0;agent<tab_agent[0].length;agent++){
			if (tab_agent[0][agent]!=null){
				b=false;
				for(int i=0;i<x;i++){
					for(int j=0;j<y;j++){
						for(int tab_ress=1;tab_ress<tab_ressource.length;tab_ress++){
							for(int ress=0;ress<tab_ressource[tab_ress].length;ress++){
								if(tab_ressource[tab_ress][ress]!=null){
									if(mine.getCase(i,j)==tab_ressource[tab_ress][ress]) {
										x_ressource=mine.getCase(i,j).getX();
										y_ressource=mine.getCase(i,j).getY();
										b=true;
									}
								}
							}
						}	
					}
				}
				if (b==false){
					System.out.println("\nToutes les ressources ont été récoltées");
					break;
				}
				
				if((Math.abs(tab_agent[0][agent].getX()-x_ressource))>=(Math.abs(tab_agent[0][agent].getY()-y_ressource))){
					if (x_ressource>tab_agent[0][agent].getX()){
						for(int x_creuse=tab_agent[0][agent].getX();x_creuse<=x_ressource;x_creuse++){
							tab_agent[0][agent].seDeplacer(x_creuse,tab_agent[0][agent].getY());
							this.creuserX_Y(agent);
						}
						if (y_ressource>tab_agent[0][agent].getY()){
							for(int y_creuse=tab_agent[0][agent].getY();y_creuse<=y_ressource;y_creuse++){
								tab_agent[0][agent].seDeplacer(tab_agent[0][agent].getX(),y_creuse);	
								this.creuserX_Y(agent);
							}
						} else if (y_ressource<tab_agent[0][agent].getY()){
							for(int y_creuse=tab_agent[0][agent].getY();y_creuse>=y_ressource;y_creuse--){
								tab_agent[0][agent].seDeplacer(tab_agent[0][agent].getX(),y_creuse);
								this.creuserX_Y(agent);
							}
						}
					} else if(x_ressource<tab_agent[0][agent].getX()){
						for(int x_creuse=tab_agent[0][agent].getX();x_creuse>=x_ressource;x_creuse--){
							tab_agent[0][agent].seDeplacer(x_creuse,tab_agent[0][agent].getY());
							this.creuserX_Y(agent);
						}
						if (y_ressource>tab_agent[0][agent].getY()){
							for(int y_creuse=tab_agent[0][agent].getY();y_creuse<=y_ressource;y_creuse++){
								tab_agent[0][agent].seDeplacer(tab_agent[0][agent].getX(),y_creuse);	
								this.creuserX_Y(agent);
							}
						} else if (y_ressource<tab_agent[0][agent].getY()){
							for(int y_creuse=tab_agent[0][agent].getY();y_creuse>=y_ressource;y_creuse--){
								tab_agent[0][agent].seDeplacer(tab_agent[0][agent].getX(),y_creuse);
								this.creuserX_Y(agent);
							}
						}
								
						}
					
				} else {
					
					if (y_ressource>tab_agent[0][agent].getY()){
						for(int y_creuse=tab_agent[0][agent].getY();y_creuse<=y_ressource;y_creuse++){
							tab_agent[0][agent].seDeplacer(tab_agent[0][agent].getX(),y_creuse);
							this.creuserX_Y(agent);
							
						}
						if (x_ressource>tab_agent[0][agent].getX()){
							for(int x_creuse=tab_agent[0][agent].getX();x_creuse<=x_ressource;x_creuse++){
								tab_agent[0][agent].seDeplacer(x_creuse,tab_agent[0][agent].getY());
								this.creuserX_Y(agent);
							}
						} else if(x_ressource<tab_agent[0][agent].getX()){
							for(int x_creuse=tab_agent[0][agent].getX();x_creuse>=x_ressource;x_creuse--){
								tab_agent[0][agent].seDeplacer(x_creuse,tab_agent[0][agent].getY());
								this.creuserX_Y(agent);
							}
						}
					} else if(y_ressource<tab_agent[0][agent].getY()){
						for(int y_creuse=tab_agent[0][agent].getY();y_creuse>=y_ressource;y_creuse--){
							tab_agent[0][agent].seDeplacer(tab_agent[0][agent].getX(),y_creuse);
							this.creuserX_Y(agent);
							
						}
						if (x_ressource>tab_agent[0][agent].getX()){
							for(int x_creuse=tab_agent[0][agent].getX();x_creuse<=x_ressource;x_creuse++){
								tab_agent[0][agent].seDeplacer(x_creuse,tab_agent[0][agent].getY());
								this.creuserX_Y(agent);
							}
						} else if(x_ressource<tab_agent[0][agent].getX()){
							for(int x_creuse=tab_agent[0][agent].getX();x_creuse>=x_ressource;x_creuse--){
								tab_agent[0][agent].seDeplacer(x_creuse,tab_agent[0][agent].getY());
								this.creuserX_Y(agent);
							}
						}
					}
				}
				if (tab_agent[1][0]!=null){
					this.transformer(0);
				} else {
					System.out.println("aucun ouvrier assigné au fer");
				}
				if (tab_agent[1][1]!=null){
					this.transformer(1);
				}else {
					System.out.println("aucun ouvrier assigné au cuivre");
				}
				if (tab_agent[1][2]!=null){
					this.transformer(2);
				}else {
					System.out.println("aucun ouvrier assigné à l'or");
				}
				tab_agent[0][agent].seDeplacer(ascenseur_x,ascenseur_y);
				System.out.println("MINE :");
				mine.affiche();
				System.out.println("USINE :");
				usine.affiche();
				for(int i=0;i<tab_stock.length;i++){
					System.out.println(tab_stock[i]);
				}
			}
		}
	}
}
