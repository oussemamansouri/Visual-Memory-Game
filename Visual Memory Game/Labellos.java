import javax.swing.* ;
import javax.swing.event.* ;

public class Labellos extends JLabel
	{
	private String numGif ;
	private String cote;
	private int position ;
	/*******************************************/
	public Labellos(String s, int type)
		{
		super(s, type) ;
		}
/*********************************************/
	public void setGif(String n)
		{
		numGif = n ;
		}
/*********************************************/
	public void setcote(String n)
		{
		cote = n ;
		}
	/*********************************************/	
	public String getcote()
		{
		return cote ;
		}
	/*********************************************/	
	public String getGif()
		{
		return numGif ;
		}
	}