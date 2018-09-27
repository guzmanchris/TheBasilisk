package Game.GameStates;


import java.awt.Graphics;

import Game.Entities.Dynamic.PlayerOne;
import Game.Entities.Dynamic.PlayerTwo;
import Main.Handler;
import Resources.Images;
import Worlds.WorldBase;
import Worlds.WorldTwo;

public class MultiPlayerState extends State{
	
	public int ticks;
	private WorldBase world;
	
	public MultiPlayerState(Handler handler) {
		super(handler);
		world = new WorldTwo(handler);
		handler.setWorld(world);
		handler.getWorld().player1= new PlayerOne(handler);
		handler.getWorld().player2 = new PlayerTwo(handler);
		
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false;
                handler.getWorld().appleLocation[i][j]=false;

            }
        }
        handler.getWorld().playerLocation[handler.getWorld().player1.xCoord][handler.getWorld().player1.yCoord] =true;
		handler.getWorld().playerLocation[handler.getWorld().player2.xCoord][handler.getWorld().player2.yCoord] =true;
		
	}

	@Override
	public void tick() {
		ticks++;
        handler.getWorld().tick();
        
        if(handler.getKeyManager().p && ticks>=30){
            State.setState(handler.getGame().pauseState);
            ticks = 0;}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.background,0,0,handler.getWidth(),handler.getHeight(),null);
        handler.getWorld().render(g);
		
	}

}
