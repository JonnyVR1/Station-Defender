package com.aston.group.stationdefender;

import com.aston.group.stationdefender.callbacks.GameCallback;
import com.aston.group.stationdefender.callbacks.MenuCallback;
import com.aston.group.stationdefender.callbacks.TwoTextCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.screens.GameScreen;
import com.aston.group.stationdefender.screens.IntroScreen;
import com.aston.group.stationdefender.screens.MenuScreen;
import com.aston.group.stationdefender.screens.TwoTextScreen;
import com.aston.group.stationdefender.utils.FileUtils;
import com.aston.group.stationdefender.utils.SoundManager;
import com.aston.group.stationdefender.utils.resources.StackableInventory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * The Main game class.
 *
 * @author Jonathon Fitch
 */
public class Main extends Game implements GameCallback, TwoTextCallback, MenuCallback {
    private IntroScreen introScreen;
    private TwoTextScreen backgroundScreen;
    private TwoTextScreen instructionScreen;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private int levelNumber = 1;
    private int totalScore;

    @Override
    public void create() {
        backgroundScreen = new TwoTextScreen(this, false);
        instructionScreen = new TwoTextScreen(this, false);
        introScreen = new IntroScreen(this);
        menuScreen = new MenuScreen(this);
        initGame();

        // Setup title and body text
        backgroundScreen.setTitle(Constants.MENU_ITEMS[0]);
        backgroundScreen.setBody(Constants.BACKGROUND);
        instructionScreen.setTitle(Constants.MENU_ITEMS[1]);
        instructionScreen.setBody(Constants.INSTRUCTIONS);

        //Set the screen to intro upon creation if debug flag is not set
        if (!Constants.DEBUG) {
            setScreen(introScreen);
        } else {
            setScreen(gameScreen);
        }
        SoundManager.playSound(SoundManager.BACKGROUND_MUSIC);
    }

    /**
     * Disposes of any existing GameScreen then create a new one
     */
    private void initGame() {
        if (gameScreen != null)
            gameScreen.dispose();
        gameScreen = new GameScreen(this, levelNumber);
    }

    @Override
    public void onWinLost(StackableInventory inventory, boolean won, int score, int money) {
        FileUtils.deleteLevelInfo();
        TwoTextScreen postLevelScreen;
        String title;
        if (won) {
            FileUtils.saveLevel(score, money, levelNumber, inventory);
            title = "Level Cleared";
            postLevelScreen = new TwoTextScreen(this, true);
            levelNumber++;
        } else {
            title = "You Failed!";
            postLevelScreen = new TwoTextScreen(this, false);
        }
        totalScore += score;
        postLevelScreen.setTitle(title);
        postLevelScreen.setBody("Level Score: " + score + "\nMoney: £" + money + "\n\nTotal Score: " + totalScore);
        if (!won)
            totalScore = 0;
        setScreen(postLevelScreen);
    }

    @Override
    public void onPause() {
        setScreen(menuScreen);
    }

    @Override
    public void onBack() {
        setScreen(introScreen);
    }

    @Override
    public void onContinue() {
        initGame();
        setScreen(gameScreen);
    }

    @Override
    public void onDisplayBackground() {
        setScreen(backgroundScreen);
    }

    @Override
    public void onDisplayInstructions() {
        setScreen(instructionScreen);
    }

    @Override
    public void onPlay(boolean refresh) {
        if (refresh) initGame();
        setScreen(gameScreen);
    }

    @Override
    public void onExit() {
        Gdx.app.exit();
    }
}