package PlayerControlManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class KeyboardController implements InputController {
    @Override
    public DIRECTION queryInput() {
        boolean rightKey = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean leftKey = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean upKey = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downKey = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        if (rightKey) {
            return DIRECTION.RIGHT;
        }
        if (leftKey) {
            return DIRECTION.LEFT;
        }
        if (upKey) {
            return DIRECTION.UP;
        }
        if (downKey) {
            return DIRECTION.DOWN;
        }
        return DIRECTION.NONE;
    }

    @Override
    public boolean checkForRestart() {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }
}