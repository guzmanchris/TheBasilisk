package Game.GameStates;

import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class PauseState extends State {

    private int count = 0;
    private UIManager uiManager;

    public PauseState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);

        uiManager.addObjects(new UIImageButton(56, 223, 200, 64, Images.Resume, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().gameState);
            count=0; 
        }));

        uiManager.addObjects(new UIImageButton(56, 223+(64+16), 200, 64, Images.Restart, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().gameState);
            handler.getGame().reStart();
            count=0;
        }));

        uiManager.addObjects(new UIImageButton(56, (223+(64+16))+(64+16), 200, 64, Images.ReturnToMenu, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
            count=0;
        }));





    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        count++;
        if( count>=30){
        	if((handler.getKeyManager().pbutt || handler.getKeyManager().p)){
                State.setState(handler.getGame().gameState);
                count = 0;
            }
        }
        }


    @Override
    public void render(Graphics g) {
        g.drawImage(Images.Pause,0,0,600,600,null);
        uiManager.Render(g);

    }
}
