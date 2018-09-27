package Worlds;

import java.awt.Graphics;
import java.util.Random;

import Game.Entities.Static.Apple;
import Main.Handler;

public class WorldTwo extends WorldBase{
	
	public int[] appleXandY = new int[2];
	public int ticks=0;
	
	public WorldTwo(Handler handler) {
		 super(handler);

	        //has to be a number bigger than 20 and even
	        GridWidthHeightPixelCount = 60;
	        GridPixelsize = (600/GridWidthHeightPixelCount);
	        playerLocation = new Boolean[GridWidthHeightPixelCount][GridWidthHeightPixelCount];
	        appleLocation = new Boolean[GridWidthHeightPixelCount][GridWidthHeightPixelCount];

	}

	@Override
	public void tick() {
		ticks++;
		super.tick();
		player1.tick();
		player2.tick();
		if(!appleOnBoard){
            appleOnBoard=true;
            int appleX = new Random().nextInt(handler.getWorld().GridWidthHeightPixelCount-1);
            int appley = new Random().nextInt(handler.getWorld().GridWidthHeightPixelCount-1);

            //change coordinates till one is selected in which the player isnt standing
            boolean goodCoordinates=false;
            do{
                if(!handler.getWorld().playerLocation[appleX][appley]){
                    goodCoordinates=true;
                }
            }while(!goodCoordinates);

            apple = new Apple(handler,appleX,appley);
            appleLocation[appleX][appley]=true;
            appleXandY[0] = appleX;
            appleXandY[1] = appley;
            }
		if(player1.xCoord == appleXandY[0] && player1.yCoord == appleXandY[1] && ticks>=player1.defaultSpeed) {
			player1.Eat();
			player2.removeTail();

		}
		if(player2.xCoord == appleXandY[0] && player2.yCoord == appleXandY[1] && ticks>=player2.defaultSpeed) {
			player2.Eat();
			player1.removeTail();
		}
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		player1.render(g, playerLocation);
		player2.render(g, playerLocation);
	}
	


}
