package Game.GameStates;

import java.awt.Graphics;

import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

public class GameOverState extends State{
    private UIManager uiManager;
    private int count = 0;

	public GameOverState(Handler handler) {
		super(handler);
		 uiManager = new UIManager(handler);
	     handler.getMouseManager().setUimanager(uiManager);
	     
	     uiManager.addObjects(new UIImageButton(56, 223, 200, 64, Images.PlayAgain, () -> {
	            handler.getMouseManager().setUimanager(null);
	            State.setState(handler.getGame().gameState);
	            handler.getGame().reStart();
	        }));
	     
	     uiManager.addObjects(new UIImageButton(56, 223+(64+16), 200, 64, Images.ReturnToMenu, () -> {
           handler.getMouseManager().setUimanager(null);
           State.setState(handler.getGame().menuState);
       }));


	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        count ++;
        if( count>=30){
            count=30;
        }
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.GameOver,0,0,600,600,null);
        uiManager.Render(g);
		
	}

}
