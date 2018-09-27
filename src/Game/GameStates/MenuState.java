package Game.GameStates;


import Main.Handler;
import Resources.Images;
import UI.ClickListlener;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);


        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-131, handler.getHeight()/2-(46*2), 262, 46, Images.butstart, new ClickListlener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
                handler.getGame().reStart();
                State.setState(handler.getGame().gameState);
            }
        }));
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-131, handler.getHeight()/2-(23), 262, 46, Images.challengeAFriend, new ClickListlener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
                handler.getGame().multRestart();
                State.setState(handler.getGame().multiPlayerState);
            }
        }));
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-131, handler.getHeight()/2+(46), 262, 46, Images.HowToPlay, new ClickListlener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);;
                State.setState(handler.getGame().howToPlayState);
            }
        }));
    }


    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
        g.drawImage(Images.title,0,0,handler.getWidth(),handler.getHeight(),null);
        uiManager.Render(g);

    }


}
