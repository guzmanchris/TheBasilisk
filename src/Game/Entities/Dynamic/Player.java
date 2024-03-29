package Game.Entities.Dynamic;
import Game.GameStates.State;
import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {

    public int lenght;
    public boolean justAte;
    private Handler handler;

    public int xCoord;
    public int yCoord;

    //Keep track of times method has been called
    public int moveCounter;
    public int ticks = 0;
    
    //Value of speedAdjust sets default speed of snake
    public int speedAdjust = 4;
    
    //set initial score to 0
    public int score = 0;
    

    public String direction;//is your first name one?

    public Player(Handler handler){
        this.handler = handler;
        xCoord = 0;
        yCoord = 0;
        moveCounter = 0;
        direction= "Right";
        justAte = false;
        lenght= 1;

    }


	public void tick(){
        moveCounter++;
        ticks++;
        if(moveCounter>=speedAdjust) {
            checkCollisionAndMove();
            moveCounter=0;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) && !(direction == "Down") ){
            direction="Up";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN) && !(direction == "Up")){
            direction="Down";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) && !(direction == "Right")){
            direction="Left";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT) && !(direction == "Left")){
            direction="Right";
        }
       
        
       //Debugging Tools   
        if(ticks >= 10) {
        	 
        	 if(handler.getKeyManager().n) {
     	        addTail();
     	        ticks = 0;  
             }        
	        if((handler.getKeyManager().plus || handler.getKeyManager().equal) && speedAdjust>1) {
	        	speedAdjust--;
	        	ticks = 0;
	        }
	        
	        if(handler.getKeyManager().minus) {
	        	speedAdjust++;
	        	ticks = 0;
	        }
        
        }

    }

    public void checkCollisionAndMove(){
        handler.getWorld().playerLocation[xCoord][yCoord]=false;
        int x = xCoord;
        int y = yCoord;
        for(Tail t : handler.getWorld().body) {
        	if(t.x == x && t.y == y) {
        		kill();
        	}
        }
        switch (direction){
            case "Left":
                if(xCoord==0){
                    kill();
                }else{
                    xCoord--;
                }
                break;
            case "Right":
                if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                    kill();
                }else{
                    xCoord++;
                }
                break;
            case "Up":
                if(yCoord==0){
                    kill();
                }else{
                    yCoord--;
                }
                break;
            case "Down":
                if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                    kill();
                }else{
                    yCoord++;
                }
                break;
        }
        handler.getWorld().playerLocation[xCoord][yCoord]=true;


        if(handler.getWorld().appleLocation[xCoord][yCoord]){
            Eat();
        }

        if(!handler.getWorld().body.isEmpty()) {
            handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
            handler.getWorld().body.removeLast();
            handler.getWorld().body.addFirst(new Tail(x, y,handler));
        }

    }

    public void render(Graphics g,Boolean[][] playeLocation){
        Random r = new Random();
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
                g.setColor(Color.GREEN);         

                if(playeLocation[i][j]||handler.getWorld().appleLocation[i][j]){
                    g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                }

            }
        }
        String s = "Score: " + score;
        g.drawString(s, 0, 10);
        
    }

    public void Eat(){
        lenght++;
        Tail tail= null;
        handler.getWorld().appleLocation[xCoord][yCoord]=false;
        handler.getWorld().appleOnBoard=false;
        switch (direction){
            case "Left":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail = new Tail(this.xCoord+1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail = new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail =new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                        }
                    }

                }
                break;
            case "Right":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=0){
                        tail=new Tail(this.xCoord-1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail=new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                        }
                    }

                }
                break;
            case "Up":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
            case "Down":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=0){
                        tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        } System.out.println("Tu biscochito");
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
        }
        handler.getWorld().body.addLast(tail);
        handler.getWorld().playerLocation[tail.x][tail.y] = true;
        score ++;
        this.setJustAte(true);
    }

    public void addTail() {
    	lenght++;
    	Tail tail = null;
    	 switch (direction){
         case "Left":
             if( handler.getWorld().body.isEmpty()){
                 if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                     tail = new Tail(this.xCoord+1,this.yCoord,handler);
                 }else{
                     if(this.yCoord!=0){
                         tail = new Tail(this.xCoord,this.yCoord-1,handler);
                     }else{
                         tail =new Tail(this.xCoord,this.yCoord+1,handler);
                     }
                 }
             }else{
                 if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                     tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                 }else{
                     if(handler.getWorld().body.getLast().y!=0){
                         tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                     }else{
                         tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                     }
                 }

             }
             break;
         case "Right":
             if( handler.getWorld().body.isEmpty()){
                 if(this.xCoord!=0){
                     tail=new Tail(this.xCoord-1,this.yCoord,handler);
                 }else{
                     if(this.yCoord!=0){
                         tail=new Tail(this.xCoord,this.yCoord-1,handler);
                     }else{
                         tail=new Tail(this.xCoord,this.yCoord+1,handler);
                     }
                 }
             }else{
                 if(handler.getWorld().body.getLast().x!=0){
                     tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                 }else{
                     if(handler.getWorld().body.getLast().y!=0){
                         tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                     }else{
                         tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                     }
                 }

             }
             break;
         case "Up":
             if( handler.getWorld().body.isEmpty()){
                 if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                     tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                 }else{
                     if(this.xCoord!=0){
                         tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                     }else{
                         tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                     }
                 }
             }else{
                 if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                     tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                 }else{
                     if(handler.getWorld().body.getLast().x!=0){
                         tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                     }else{
                         tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                     }
                 }

             }
             break;
         case "Down":
             if( handler.getWorld().body.isEmpty()){
                 if(this.yCoord!=0){
                     tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                 }else{
                     if(this.xCoord!=0){
                         tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                     }else{
                         tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                     } System.out.println("Tu biscochito");
                 }
             }else{
                 if(handler.getWorld().body.getLast().y!=0){
                     tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                 }else{
                     if(handler.getWorld().body.getLast().x!=0){
                         tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                     }else{
                         tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                     }
                 }

             }
             break;
     }
     handler.getWorld().body.addLast(tail);
     handler.getWorld().playerLocation[tail.x][tail.y] = true;
    	
    }
    
    public void kill(){
        lenght = 0;
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false;

            }
        }
        State.setState(handler.getGame().gameOverState);
    }

    public boolean isJustAte() {
        return justAte;
    }

    public void setJustAte(boolean justAte) {
        this.justAte = justAte;
    }
}
