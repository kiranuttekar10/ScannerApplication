package demoacnner;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.event.EventListenerList;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class keytest implements NativeKeyListener {
	
	List<Character> ls =new ArrayList<Character>();
	List<String> codelist= new ArrayList<String>();
	String[] codearray=new String[10];
	int arraycount=0,listcount=0;
	
	
	
	public keytest()
	{
		// Get the logger for "org.jnativehook" and set the level to off.
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);

		// Don't forget to disable the parent handlers.
		logger.setUseParentHandlers(false);
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent a) {
		
		
	}

	
	@Override
	public void nativeKeyReleased(NativeKeyEvent a) {
	
	}

	
	@Override
	public void nativeKeyTyped(NativeKeyEvent a) {
		System.out.println("KeyTyped: "+ a.getKeyChar());
		try {
			printeventinfo("",a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	
	private void printeventinfo(String string, NativeKeyEvent a) throws Exception {
		
		char ch=a.getKeyChar();
		
		//for getting keys in correct sequence
		if(ls.size() < 4)
		{
		switch(ch)
		{
		case 'S' :	if(ls.isEmpty())
			       {
			         ls.add(ch);
			         
			        }
		            else
		            {
			        ls.clear();
		            }
		           break;
		case 'E' : 	if(!ls.isEmpty() && ls.size()==1)
		        	{
			         ls.add(ch);
			        }
		           else
		           {
			        ls.clear();
		            }
		           break;
		case 'R' : 	if(!ls.isEmpty() && ls.size()==2)
		            {
			        ls.add(ch);
		            }else
		            {
			        ls.clear();
		            }
		            break;
		case '-' : if(!ls.isEmpty() && ls.size()==3)
			        {           
			          ls.add(ch);
			        }else
			        {
			        ls.clear();
		            }
		            break;
		 default : ls.clear();            
		            
		}
		}
			
		
		System.out.println("The size of the list :"+ ls.size());
		
		if(ls.size() >= 4)
		{
			
	    	if(ls.get(0)!= 'S' || ls.get(1)!= 'E' || ls.get(2)!='R' || ls.get(3)!='-')
	    	{
	    		
	    		ls.clear();
	    		System.out.println("your sequence is not correct.");
	    		System.out.println("The correct sequence is - for example:- 'SER-123456' ");
	    		System.out.println("Please Enter correct sequence again.");
	    	}
	    	else
	    	{
	    		ls.add(ch);
	    	}
	    	
		}
	    
		
		
		System.out.println("Is the list empty : " + ls.isEmpty());
		
		
		if(ls.size()==11)
	    {
			System.out.println("Show the 6-digit code:");
	    	if(ls.get(0) == 'S' || ls.get(1)== 'E' || ls.get(2)=='R' || ls.get(3)=='-')
	    	{
	    		//String listString = "";
                List<Character> ls1=ls.subList(5,11);
                System.out.println("Length of the code :"+ls1.size());
                StringBuilder builder = new StringBuilder(ls1.size());
                for(Character c: ls1)
                {
                    builder.append(c);
                }
                String listString=builder.toString();
	    		
	    		System.out.println("Your six digit code is :"+listString);
	    		collectcode(listString);
	    		
	    		ls.clear();
	    	
	    	}	
	    	ls.clear();
	     }
		
		
		
		
		
	     
}
	
	
	
	public void collectcode(String code)
	{
		
		
		codelist.add(code);
		codearray[arraycount]=codelist.get(listcount);
		arraycount++;
		listcount++;
		
		
		
		
		
		if (arraycount == 10)
		
		{   
		
		for(int i=0 ;i<=arraycount	;i++)
		  {
			 
				try {
					urlcall(codearray[i]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		  }
		 
		    
		
        arraycount=0;
    
		
		}
		
		
		if (listcount == 100)
		{
			System.out.println("Clearing the arraylist");
			codelist.clear();
			listcount=0;
		}
	
	
}

   public void urlcall(String url) throws Exception
   {
	   HttpURLConnectionclass http = new HttpURLConnectionclass();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet(url);
		
		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost(url);
   }
	
	public static void main(String[] args) throws NativeHookException 
	{   
		keytest k=new keytest();
	    GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(k);
        
	}
	
	
}
