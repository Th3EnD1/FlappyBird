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
//                        String data = (String)obj;
//                        System.out.println(data);
//                        warCardGameClient.updateGUI(data);
                            String data = "Test";
                            System.out.println(data);
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
