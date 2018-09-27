package Game.Entities.Dynamic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.GameStates.State;
import Main.Handler;

public class PlayerTwo {

	 public int length;
	 private Handler handler;
	 
	 public int xCoord;
	 public int yCoord;
	 
	//Keep track of times method has been called
	  public int moveCounter;
	  public int ticks = 0;
	  
	  public int defaultSpeed = 4;
	  
	  public String direction;

	public PlayerTwo(Handler handler) {
		this.handler = handler;
        xCoord = handler.getWorld().GridWidthHeightPixelCount-5;
        yCoord = handler.getWorld().GridWidthHeightPixelCount-1;
        moveCounter = 0;
        direction= "Left";
        length= 5;
        for(int i=1; i<length; i++) {
        	handler.getWorld().body2.add(new Tail(xCoord+i, yCoord,handler));
        }
	  }
	
	  
	public void tick(){
        moveCounter++;
        ticks++;
        if(moveCounter>=defaultSpeed) {
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
    }
	
	public void checkCollisionAndMove(){
        handler.getWorld().playerLocation[xCoord][yCoord]=false;
        int x = xCoord;
        int y = yCoord;
        for(Tail t : handler.getWorld().body2) {
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

        if(!handler.getWorld().body2.isEmpty()) {
            handler.getWorld().playerLocation[handler.getWorld().body2.getLast().x][handler.getWorld().body2.getLast().y] = false;
            handler.getWorld().body2.removeLast();
            handler.getWorld().body2.addFirst(new Tail(x, y,handler));
        }

    }

    public void render(Graphics g,Boolean[][] playeLocation){
        Random r = new Random();
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
            	g.setColor(Color.BLUE);

                if(playeLocation[i][j]){
                    g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                }
                if(handler.getWorld().appleLocation[i][j]) {
                	g.setColor(Color.RED);
                	 g.fillRect((i*handler.getWorld().GridPixelsize),
                             (j*handler.getWorld().GridPixelsize),
                             handler.getWorld().GridPixelsize,
                             handler.getWorld().GridPixelsize);
                }

            }
        }
    }
    
    public void Eat(){
        length++;
        Tail tail= null;
        handler.getWorld().appleLocation[xCoord][yCoord]=false;
        handler.getWorld().appleOnBoard=false;
        switch (direction){
            case "Left":
                if( handler.getWorld().body2.isEmpty()){
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
                    if(handler.getWorld().body2.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=new Tail(handler.getWorld().body2.getLast().x+1,this.yCoord,handler);
                    }else{
                        if(handler.getWorld().body2.getLast().y!=0){
                            tail=new Tail(handler.getWorld().body2.getLast().x,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(handler.getWorld().body2.getLast().x,this.yCoord+1,handler);

                        }
                    }

                }
                break;
            case "Right":
                if( handler.getWorld().body2.isEmpty()){
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
                    if(handler.getWorld().body2.getLast().x!=0){
                        tail=(new Tail(handler.getWorld().body2.getLast().x-1,this.yCoord,handler));
                    }else{
                        if(handler.getWorld().body2.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body2.getLast().x,this.yCoord-1,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body2.getLast().x,this.yCoord+1,handler));
                        }
                    }

                }
                break;
            case "Up":
                if( handler.getWorld().body2.isEmpty()){
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
                    if(handler.getWorld().body2.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(handler.getWorld().body2.getLast().x,this.yCoord+1,handler));
                    }else{
                        if(handler.getWorld().body2.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body2.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body2.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
            case "Down":
                if( handler.getWorld().body2.isEmpty()){
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
                    if(handler.getWorld().body2.getLast().y!=0){
                        tail=(new Tail(handler.getWorld().body2.getLast().x,this.yCoord-1,handler));
                    }else{
                        if(handler.getWorld().body2.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body2.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body2.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
        }
        handler.getWorld().body2.addLast(tail);
        handler.getWorld().playerLocation[tail.x][tail.y] = true;
    }
    
    public void removeTail() {
    	length--;
    	handler.getWorld().playerLocation[handler.getWorld().body2.getLast().x][handler.getWorld().body2.getLast().y] = false;
    	handler.getWorld().body2.removeLast();
    }
    
    public void kill(){
        length = 0;
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false;

            }
        }
        State.setState(handler.getGame().gameOverState);
    }
	  
	  
}
