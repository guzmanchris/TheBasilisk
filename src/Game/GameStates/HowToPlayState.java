package Game.GameStates;

import java.awt.Graphics;

import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

public class HowToPlayState extends State {

	private UIManager uiManager;
	
	public HowToPlayState(Handler handler) {
		super(handler);
		 uiManager = new UIManager(handler);
	     handler.getMouseManager().setUimanager(uiManager);
		
		 uiManager.addObjects(new UIImageButton(handler.getWidth()/2-131, handler.getHeight()-(64+16), 200, 64, Images.ReturnToMenu, () -> {
	            handler.getMouseManager().setUimanager(null);
	            State.setState(handler.getGame().menuState);
	        }));
	}

	@Override
	public void tick() {
		 handler.getMouseManager().setUimanager(uiManager);
	     uiManager.tick();
		
	}

	@Override
	public void render(Graphics g) {
		 g.drawImage(Images.HowToPlayScreen,0,0,600,600,null);
	        uiManager.Render(g);
		
	}

	
}
