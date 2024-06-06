package engineTester;

import java.util.ArrayList;
import java.util.List;

import entities.*;
import guis.GuiTexture;
import guis.guiRenderer;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.*;
import terrains.Terrain;
import textures.ModelTexture;

public class inGameMenu {

    private boolean isVisible;
    private boolean restartGame = false;
    private boolean resetTriggered = false;

    private boolean quitGame = false;

    private boolean menuKeyPressedLastFrame;
    private Loader loader;
    private guiRenderer guiRenderer;
    private List<GuiTexture> guis;
    private List<GuiTexture> guiquitbtn;
    private List<GuiTexture> guibg;
    private GuiTexture gui;
    private GuiTexture guidef;
    private GuiTexture guiHover;
    private GuiTexture guiquit;
    private GuiTexture guiquitdef;
    private GuiTexture guiquitHover;

    private Player player;

    public inGameMenu() {
        isVisible = false;
        loader = new Loader();
        guiRenderer = new guiRenderer(loader);
        guis = new ArrayList<GuiTexture>();
        gui = new GuiTexture(loader.loadTexture("reset"), new Vector2f(0f, 0f), new Vector2f(0.15f, 0.15f));
        guidef = new GuiTexture(loader.loadTexture("reset"), new Vector2f(0f, 0f), new Vector2f(0.15f, 0.15f));
        guiHover = new GuiTexture(loader.loadTexture("resethover"), new Vector2f(0f, 0f), new Vector2f(0.15f, 0.15f));
        guis.add(gui);

        guiquitbtn = new ArrayList<>();
        guiquit = new GuiTexture(loader.loadTexture("quit"), new Vector2f(0f, -0.3f), new Vector2f(0.15f, 0.15f));
        guiquitdef = new GuiTexture(loader.loadTexture("quit"), new Vector2f(0f, -0.3f), new Vector2f(0.15f, 0.15f));
        guiquitHover = new GuiTexture(loader.loadTexture("quithover"), new Vector2f(0f, -0.3f), new Vector2f(0.15f, 0.15f));
        guiquitbtn.add(guiquit);
        guibg = new ArrayList<GuiTexture>();
        GuiTexture guiBack = new GuiTexture(loader.loadTexture("blue"), new Vector2f(0f, -0.1f), new Vector2f(0.4f, 0.35f));
        guibg.add(guiBack);

    }

    public void render() {
        if (isVisible) {
            guiRenderer.render(guibg);
            guiRenderer.render(guiquitbtn);
            guiRenderer.render(guis);

        }
    }

    public void handleInput() {
        boolean isMenuKeyPressed = Keyboard.isKeyDown(Keyboard.KEY_J);

        if (isMenuKeyPressed && !menuKeyPressedLastFrame) {
            toggleMenu();
        }

        menuKeyPressedLastFrame = isMenuKeyPressed;

        if (isVisible) {
            float mouseX = Mouse.getX();
            float mouseY = Mouse.getY();

            float normalizedMouseX = (2f * mouseX / Display.getWidth()) - 1f;
            float normalizedMouseY = (2f * mouseY / Display.getHeight()) - 1f;

            boolean isHoverRestart = normalizedMouseX >= -0.15f && normalizedMouseX <= 0.128f &&
                    normalizedMouseY >= -0.073f && normalizedMouseY <= 0.125f;

            if (isHoverRestart) {
                gui.setTexture(guiHover.getTexture());
            } else {
                gui.setTexture(guidef.getTexture());
            }

            if (Mouse.isButtonDown(0) && isHoverRestart) {
                restartGame = true;
            }

            boolean isHoverQuit = normalizedMouseX >= -0.15f && normalizedMouseX <= 0.128f &&
                    normalizedMouseY >= -0.375f && normalizedMouseY <= -0.177f;

            if (isHoverQuit) {
                guiquit.setTexture(guiquitHover.getTexture());
            } else {
                guiquit.setTexture(guiquitdef.getTexture());
            }

            if (Mouse.isButtonDown(0) && isHoverQuit) {
                quitGame = true;
            }
        }
    }

    public boolean getRestartGame(){
        return restartGame;
    }

    public boolean getQuitGame(){
        return quitGame;
    }


    public void toggleMenu() {
        isVisible = !isVisible;
        restartGame = false;
        quitGame = false;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
