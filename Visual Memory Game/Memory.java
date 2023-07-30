//new ImageIcon(getToolkit().createImage(getClass().getResource("ascendant.gif")))
import java.awt.* ;

import java.awt.event.* ;
import javax.swing.* ;
import javax.swing.event.* ;

import javax.swing.border.* ;
import java.lang.* ;
import java.util.Vector;


public class Memory extends JFrame implements ActionListener, WindowListener
	{
	static JFrame jF ;
	final int droite=1;
	final int gauche=0;
	final int cases=9;
	boolean JOUE=false;
	int nbCoups=0;
	Labellos jLabello[][] = new Labellos[2][cases] ;
	DevoileGif loinLeVoile = new DevoileGif() ;
	JButton tir, visio ;
	JLabel score, lbAvis;
	JLabel [] lbpict = new JLabel[2];
	JLabel [] descript = new JLabel[2];
	Vector complet = new Vector();

	String tabAnimaux[] = {"elephant.gif", "gorille.gif", "kangourou.gif", "koala.gif",
			"lion.gif", "ours1.gif", "ours2.gif", "rhino.gif", "zebre.gif"} ;
			
	String description[] = {"Eléphant", "Gorille", "Kangourou", "Panda",
			"Lion", "Ours brun", "Ours en peluche", "Rhinocéros", "Zèbre"} ;					
//******************************************************************************					
	public Memory()
		{
		getContentPane().setLayout(null) ;
		donneLeLookWindow() ;		
		agenceFenetre() ;
		}
//******************************************************************************	
	public void agenceFenetre() 
		{
		Border lowerB, raised ;
		lowerB = BorderFactory.createLoweredBevelBorder();
		raised = BorderFactory.createRaisedBevelBorder() ;
		Font ft = new Font("Verdana", Font.PLAIN, 13) ;
		
		tir = new JButton("Tirer") ;
		tir.setBounds(285, 40, 100, 25) ;
		tir.addActionListener(this) ;
		tir.setFont(ft) ;
		getContentPane().add(tir) ;
		
		visio = new JButton("Visionner");
		visio.setBounds(285, 75, 100, 25) ;
		visio.addActionListener(this) ;
		visio.setFont(ft) ;
		visio.setEnabled(false);
		getContentPane().add(visio) ;
		
		lbAvis = new JLabel("") ;
		lbAvis.setBounds(20, 360, 360, 25) ;
		lbAvis.setFont(ft) ;
		lbAvis.setForeground(Color.blue) ;
		getContentPane().add(lbAvis) ;
		
		score = new JLabel("", JLabel.CENTER) ;
		score.setBounds(285, 110, 100, 25) ;
		score.setForeground(Color.blue) ;
		score.setFont(ft) ;
		getContentPane().add(score) ;
		
		JPanel Panaffiche [] = new JPanel[2] ;
		JPanel panChoix [] = new JPanel[2] ;
		for(int i=0; i<2; i++)
			{
			//Panels contenant les labels contenant les images
			Panaffiche[i] = new JPanel();
			Panaffiche[i].setLayout(null) ;
			Panaffiche[i].setBorder(lowerB) ;
			Panaffiche[i].setBounds(20+i*408,20, 227, 142) ;
			getContentPane().add(Panaffiche[i]) ;
			//Labels contenant les labels contenant les images
			lbpict[i] = new JLabel("", JLabel.CENTER) ;
			lbpict[i].setBounds(2,2,225, 140);
			lbpict[i].setIcon(new ImageIcon("voile.gif")) ;
			Panaffiche[i].add(lbpict[i]) ;
			//Labels de description
			descript[i] = new JLabel("", JLabel.CENTER) ;
			descript[i].setBounds(20+i*428, 170, 227, 25) ;
			descript[i].setForeground(Color.blue) ;
			descript[i].setFont(ft) ;
			getContentPane().add(descript[i]) ;
			//Panel de choix
			panChoix[i] = new JPanel() ;
			panChoix[i].setBackground(new Color(219,219,219)) ;
			panChoix[i].setBounds(160+i*200,220,118, 118) ;
			panChoix[i].setBorder(raised) ;
			panChoix[i].setLayout(new GridLayout(3,3,2,2)) ;
			getContentPane().add(panChoix[i]) ;
			}
		
		
		for(int cote=0; cote<2; cote++)
			for (int i=0; i<cases; i++)
				{
				jLabello[cote][i] = new Labellos("", JLabel.CENTER) ;
				jLabello[cote][i].setVisible(true) ;
				panChoix[cote].add(jLabello[cote][i]) ;
				}
		addWindowListener(this) ;	
		}	
//******************************************************************************
	public void actionPerformed(ActionEvent evt)
		{
		if(evt.getSource() == tir)
			{	
			tir.setEnabled(false);
			visio.setEnabled(true);
			lbAvis.setText("Vous pouvez visionner ou commencer à jouer");
			JOUE = true;
			tireGifs() ;
			}
		else
			{
			visio.setEnabled(false);
			Vision V = new Vision(this) ;	
			}
	
		}
	//******************************************************************************
	public void tireGifs()
		{
		int i,j;
		
		int tir11[]=new int [cases];
		tir11 = Aleatoricul(cases) ;
		
		int tir22[]=new int [cases];
		tir22 = Aleatoricul(cases) ;
		
		for(i=0;i<cases;i++)
			{
			jLabello[gauche][i].setGif(tabAnimaux[tir11[i]-1]) ;
			jLabello[gauche][i].setcote("gauche") ;
			}
			
	
		for(i=0;i<cases;i++)
			{
			jLabello[droite][i].setGif(tabAnimaux[tir22[i]-1]) ;
			jLabello[droite][i].setcote("droite") ;
			}	
		metLesMousesListener();		
		}
//******************************************************************************
	public int[] Aleatoricul(int n)
		{
		int sacNumeros[] = new int[cases+1] ;
		int recip[] = new int[cases] ;
		int i, j, indice, indiceSup=cases ;

		//remplir le sac de numéros
		for (i=0; i<=indiceSup; i++)
			sacNumeros[i] = i ;

		for (i=0; i<n; i++)
			{
			indice = (int)((indiceSup-1+1)*Math.random())+1 ;
			recip[i] = sacNumeros[indice] ;

			//réduire le tableau pour écraser la valeur qui vient de sortir
			for (j=indice+1; j<=indiceSup; j++)
				sacNumeros[j-1] = sacNumeros[j] ;

			indiceSup-- ;
			}

		return recip ;
		}
	//******************************************************************************
	public void metLesMousesListener()
		{
		for(int qqqq =0; qqqq<2; qqqq++)
			{
			lbpict[qqqq].setIcon(new ImageIcon("voile.gif"));
			descript[qqqq].setText("");
			}
		score.setText(Integer.toString(nbCoups));	
		for(int cote=0; cote<2;cote++)
			for(int i=0; i<cases;i++)
				if(!complet.contains(new String(((Labellos)jLabello[cote][i]).getGif())))
					{
					jLabello[cote][i].addMouseListener(loinLeVoile) ;
					jLabello[cote][i].setIcon(new ImageIcon("btnUno.gif")) ;	
					}
				else
					jLabello[cote][i].setIcon(null);
					
		if(complet.size()==cases)
			{
			lbAvis.setText("Jeu terminé en " + nbCoups + " coups");
			complet.clear();
			nbCoups=0;
			tir.setEnabled(true);
			JOUE = false;
			}		
		}
	//******************************************************************************
	public void enleveMouselistener()
		{
		for(int cote=0; cote<2;cote++)
			for(int i=0; i<cases;i++)
				{
				jLabello[cote][i].removeMouseListener(loinLeVoile) ;
				jLabello[cote][i].setIcon(new ImageIcon("btnUno.gif")) ;	
				}
		for(int qqqq =0; qqqq<2; qqqq++)
			{
			lbpict[qqqq].setIcon(new ImageIcon("voile.gif"));
			descript[qqqq].setText("");
			}	
		}
	//******************************************************************************
	public static void donneLeLookWindow()
		{
		try
			{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()) ;
			}
		catch(Exception e)
			{
			}
		}
	//******************************************************************************
	public int getNumPaires()
		{
		return cases;	
		}
	//******************************************************************************
	public String getDescription(String gif)
		{
		for(int i=0; i<cases; i++)
			if(tabAnimaux[i]==gif)
				return description[i];
		return "Erreur";
		}
	//******************************************************************************
	public void windowActivated(WindowEvent evt) {}
	public void windowDeactivated(WindowEvent evt) {}
	public void windowIconified(WindowEvent evt) {}
	public void windowDeiconified(WindowEvent evt) {}
	public void windowOpened(WindowEvent evt) {}
	public void windowClosed(WindowEvent evt) {}
	public void windowClosing(WindowEvent evt) 
		{
		String s="" ;
		
		if (JOUE)
			{
			s = "Le jeu n'est pas encore terminé...\n\n" ;
			s+= "Voulez-vous vraiment quitter?" ;
			}
		else
			s = "Voulez-vous vraiment quitter ?" ;
				
		int res = JOptionPane.showConfirmDialog(this, s, "Avis", 
					JOptionPane.YES_NO_OPTION) ;
					
		if (res > 0)    //répondu par non
			return ;
		
		System.exit(0) ;
		}
//******************************************************************************
	public static void main(String[] args)
		{
		jF = new Memory() ;
		jF.setSize(680, 450) ;
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize() ;
		
		jF.setLocation((screen.width-jF.getSize().width)/2, (screen.height-jF.getSize().height)/2) ;
		jF.setBackground(new Color(219, 219, 219)) ;
		jF.setTitle("Le memory de l'examen") ;
		jF.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE) ;
		jF.setResizable(false) ;
		jF.setVisible(true) ;
		}
//******************************************************************************
	public class DevoileGif extends MouseAdapter
		{
		Labellos gifChoisi ;
		Thread momento ;
		int compte=0;
		private boolean cestOk = false ;
		String gifs[] = new String[2];

		public void mousePressed(MouseEvent evt)
			{
			int i; int cote;
			visio.setEnabled(false);
			lbAvis.setText("");
			gifChoisi = (Labellos) evt.getSource() ;
			compte++;		

			if(gifChoisi.getcote().equals("droite"))
				cote=droite;
			else
				cote=gauche;
				
				gifs[cote] = gifChoisi.getGif(); 
				lbpict[cote].setIcon(new ImageIcon(gifChoisi.getGif())) ;
				descript[cote].setText(getDescription(gifChoisi.getGif()));
				for(i=0; i<cases; i++)
					{
					jLabello[cote][i].removeMouseListener(this);
					if(((Labellos)jLabello[cote][i]).getGif()==gifChoisi.getGif())
						jLabello[cote][i].setIcon(new ImageIcon("btnDuo.gif")) ;
					}
			if(compte==2)
				{
				compte=0; nbCoups++;
				if(gifs[0] == gifs[1])
					{
					complet.add(new String(gifs[1])); //Vecteur contenant les BONS résultats
					lbAvis.setText("Correct !");
					}
				else
					lbAvis.setText("Faux");
					
				new Thread(new Runnable()
					{
					public void run()
						{
						try 
							{
							Thread.sleep(1000) ;
							metLesMousesListener();
							}
						catch (InterruptedException ex) 
							{
							System.out.println("fuck");
							} 
						} 
					} ).start();
				}
					
			}
		}	
//******************************************************************************
	}