package client;


public class ClientListener extends Thread
{
    ClientGamePanel clientPanel;
    
    public ClientListener(ClientGamePanel clientPanel)
    {
        this.clientPanel = clientPanel;
    }
    
    public void run()
    {
        while(true)
        {
            try 
            {
                Object obj;
                while ((obj = clientPanel.objectInputStream.readObject()) != null) 
                {
                    if (obj instanceof Integer)
                    {
                        
                    }
                    if (obj instanceof String)
                    {
                        
                    }
                    if (obj instanceof Boolean)
                    {
                        
                    }
                }
            } 
            catch (java.io.IOException ex) 
            {
                
            } 
            
            catch (ClassNotFoundException ex) 
            {
                
            } 
        }
    }
}
