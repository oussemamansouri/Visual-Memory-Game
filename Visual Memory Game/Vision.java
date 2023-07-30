import javax.swing.* ;

public class Vision
	{
	Memory m ;
	int i, compte;
	public Vision(Memory m) 
		{
		this.m = m ;
		m.enleveMouselistener();
		GOOOOOOOOOO();
		}
//******************************************************************************
	public void GOOOOOOOOOO()
		{
		new Thread(new Runnable()
			{
			public void run()
				{
				try 
					{
					//Panel de droite
					int Depart;
					String Direction [] = {"Vertical", "Horizontal"};
					String Sens ="";
		
					do
						Depart = (int)((8-0+1)*Math.random()) ;
					while (Depart==4 || Depart ==5 || Depart ==7 || Depart ==8);
					
					if(Depart == 0)
						Sens = Direction[(int)((1-0+1)*Math.random())] ;
					else if(Depart==1|| Depart==2)
						Sens = Direction[0];
					else if(Depart == 3 || Depart ==6)
						Sens = Direction[1];
					
					//System.out.println("Sens : " + Sens + " départ: " + Depart); 
					for(int i=0; i<3; i++)
						{
						Thread.sleep(1000) ;
						if(Sens.equals("Vertical"))
							{
							m.jLabello[0][Depart+i*3].setIcon(new ImageIcon("btnDuo.gif")) ;
							m.lbpict[0].setIcon(new ImageIcon((((Labellos)(m.jLabello[0][Depart+i*3])).getGif()))) ;
							}
						else
							{
							m.jLabello[0][Depart+i].setIcon(new ImageIcon("btnDuo.gif")) ;
							m.lbpict[0].setIcon(new ImageIcon((((Labellos)(m.jLabello[0][Depart+i])).getGif()))) ;
							}
						}
					Thread.sleep(1000);
					m.enleveMouselistener();
					//panel de gauche********************
					Depart = (int)((1-0+1)*Math.random()) ;
					//première icone
					Thread.sleep(1000) ;
					m.jLabello[1][Depart*2].setIcon(new ImageIcon("bouton2.gif")) ;
					m.lbpict[1].setIcon(new ImageIcon((((Labellos)(m.jLabello[1][Depart*2])).getGif()))) ;
					//deuxième
					Thread.sleep(1000) ;
					m.jLabello[1][4].setIcon(new ImageIcon("bouton2.gif")) ;
					m.lbpict[1].setIcon(new ImageIcon((((Labellos)(m.jLabello[1][4])).getGif()))) ;	
					//trosième
					Thread.sleep(1000) ;
					if(Depart==0)	Depart=8; else	Depart=6;
					m.jLabello[1][Depart].setIcon(new ImageIcon("bouton2.gif")) ;
					m.lbpict[1].setIcon(new ImageIcon((((Labellos)(m.jLabello[1][Depart])).getGif()))) ;							
					
					Thread.sleep(1000) ;
					m.metLesMousesListener();
					m.lbAvis.setText("Vous pouvez commencer à jouer");
					
					}
				catch (InterruptedException ex) 
					{
					System.out.println("fuck");
					} 
				} 
			} ).start(); 			
		}
//******************************************************************************
	}