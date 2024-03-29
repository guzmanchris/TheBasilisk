package Game.GameStates;

import Game.Entities.Dynamic.Player;
import Main.Handler;
import Resources.Images;
import Worlds.WorldBase;
import Worlds.WorldOne;

import java.awt.*;


/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameState extends State {

    private WorldBase world;
    public int ticks = 0;

    public GameState(Handler handler){
        super(handler);
        world = new WorldOne(handler);
        handler.setWorld(world);
        handler.getWorld().player= new Player(handler);
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false;
                handler.getWorld().appleLocation[i][j]=false;

            }
        }
        handler.getWorld().playerLocation[handler.getWorld().player.xCoord][handler.getWorld().player.yCoord] =true;


    }

    @Override
    public void tick() {
    	ticks++;
        handler.getWorld().tick();
        
        if(handler.getKeyManager().p && ticks>=30){
            State.setState(handler.getGame().pauseState);
            ticks = 0;
        }

    }

    @Override
    public void render(Graphics g) {
    	
    	g.drawImage(Images.background,0,0,handler.getWidth(),handler.getHeight(),null);
        handler.getWorld().render(g);

    }

}
