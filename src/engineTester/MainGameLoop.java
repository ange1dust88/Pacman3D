package engineTester;

import java.util.ArrayList;
import java.util.List;
import entities.*;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import guis.GuiTexture;
import guis.guiRenderer;
import models.RawModel;
import models.TexturedModel;
import java.io.File;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import reflectivefloor.FloorFrameBuffers;
import reflectivefloor.FloorRenderer;
import reflectivefloor.FloorShader;
import reflectivefloor.FloorTile;
import renderEngine.*;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		TextMaster.init(loader);
		inGameMenu ingamemenu = new inGameMenu();
		FontType font = new FontType(loader.loadTexture("font"), new File("res/font.fnt"));
		GUIText text = new GUIText("PRESS SPACE TO START THE GAME", 2, font, new Vector2f(0f, 0.5f), 1f, true);
		text.setColour(1f, 1f, 1f);

		// models
		RawModel model = OBJLoader.loadObjModel("ball", loader); // player
		RawModel cubeModel = OBJLoader.loadObjModel("cube", loader);
		RawModel enemyModel = OBJLoader.loadObjModel("ghost", loader);
		RawModel fruitModel = OBJLoader.loadObjModel("fruit", loader);

		// textures
		TexturedModel cubeTexturedModel = new TexturedModel(cubeModel, new ModelTexture(loader.loadTexture("wall")));
		TexturedModel fruitTexturedModel = new TexturedModel(fruitModel, new ModelTexture(loader.loadTexture("red")));
		TexturedModel enemyTexturedModel = new TexturedModel(enemyModel, new ModelTexture(loader.loadTexture("blue")));
		TexturedModel enemy1TexturedModel = new TexturedModel(enemyModel, new ModelTexture(loader.loadTexture("pink")));
		TexturedModel enemy2TexturedModel = new TexturedModel(enemyModel, new ModelTexture(loader.loadTexture("red")));

		Labyrinth labyrinth = new Labyrinth(loader, cubeTexturedModel);
		List<Entity> labyrinthCubes = labyrinth.getLabyrinthCubes();
		Fruits fruits = new Fruits(loader, fruitTexturedModel);
		List<Entity> fruitsPos = fruits.getFruits();
		ghost1 ghosts = new ghost1(loader, enemyTexturedModel);
		List<Entity> ghost1 = ghosts.getGhost1();
		ghost2 ghosts1 = new ghost2(loader, enemy1TexturedModel);
		List<Entity> ghost2 = ghosts1.getGhost2();
		ghost3 ghosts2 = new ghost3(loader, enemy2TexturedModel);
		List<Entity> ghost3 = ghosts2.getGhost3();
		TexturedModel pl = new TexturedModel(model, new ModelTexture(loader.loadTexture("pacman")));
		Player player = new Player(pl, new Vector3f(45, 1, 51), 0, 0, 0, 1.5f, labyrinthCubes, fruitsPos, ghost1, ghost2, ghost3);
		Camera camera = new Camera(player);
		MasterRenderer renderer = new MasterRenderer();

		// gui
		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		List<GuiTexture> guis2 = new ArrayList<GuiTexture>();
		List<GuiTexture> guis3 = new ArrayList<GuiTexture>();
		GuiTexture gui = new GuiTexture(loader.loadTexture("youwin"), new Vector2f(0.25f, 0.25f), new Vector2f(0.5f, 0.5f));
		guis.add(gui);
		GuiTexture gui2 = new GuiTexture(loader.loadTexture("gameover"), new Vector2f(0.25f, 0.25f), new Vector2f(0.5f, 0.5f));
		guis2.add(gui2);
		guiRenderer guiRenderer = new guiRenderer(loader);

		Light light = new Light(new Vector3f(20000, 20000, 1000), new Vector3f(1, 1, 1));
		Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("text")));
		GUIText fruitsCountText = new GUIText("", 1, font, new Vector2f(-0.4f, 0.05f), 1f, true);
		fruitsCountText.setColour(1f, 1f, 1f);
		FloorShader floorShader = new FloorShader();
		FloorFrameBuffers fbos = new FloorFrameBuffers();
		FloorRenderer floorRenderer = new FloorRenderer(loader, floorShader, renderer.getProjectionMatrix(), fbos);
		List<FloorTile> floors = new ArrayList<FloorTile>();
		floors.add(new FloorTile(45, 50, 0));

		boolean shouldQuitGame = false;
		boolean presstostart = true;


		while (!Display.isCloseRequested()) {
			if (presstostart) {
				ghosts.setSpeed(0);
				ghosts1.setSpeed(0);
				ghosts2.setSpeed(0);
				player.setTurnSpeed(0);
				player.setRUNSpeed(0);
				text.setText("PRESS SPACE TO START THE GAME");
				fruitsCountText.setText("");
			} else {
				ghosts.setSpeed(0.2f);
				ghosts1.setSpeed(0.2f);
				ghosts2.setSpeed(0.2f);
				player.setTurnSpeed(100);
				player.setRUNSpeed(9f);
				text.setText("");
				fruitsCountText.setText("Fruit Collected: " + player.getFruitsCount());
				fruitsCountText.setColour(1, 1, 1);
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && presstostart) {
				presstostart = false;
				System.out.println("game started");
			}
			List<Entity> allEntities = new ArrayList<Entity>();
			allEntities.addAll(labyrinthCubes);
			allEntities.addAll(fruitsPos);
			allEntities.addAll(ghost1);
			allEntities.addAll(ghost2);
			allEntities.addAll(ghost3);
			allEntities.add(player);

			player.checkInputs();
			ghosts.moveGhost1();
			ghosts1.moveGhost2();
			ghosts2.moveGhost3();
			player.move();
			camera.move();

			GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
			fbos.bindReflectionFrameBuffer();
			float distance = 0;
			for (FloorTile floor : floors) {
				 distance = 2 * (camera.getPosition().y - floor.getHeight());
			}
			camera.getPosition().y -= distance;
			camera.invertedPitch();
			for (FloorTile floor : floors) {
				renderer.renderScene(allEntities, light, camera, new Vector4f(0, 1, 0, floor.getHeight()));
			}
			camera.getPosition().y += distance;
			camera.invertedPitch();
			fbos.bindRefractionFrameBuffer();
			for (FloorTile floor : floors) {
				renderer.renderScene(allEntities, light, camera, new Vector4f(0, -1, 0, floor.getHeight()));
			}

			fbos.unbindCurrentFrameBuffer();
			GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
			for (FloorTile floor : floors) {
				renderer.renderScene(allEntities, light, camera, new Vector4f(0, 1, 0, floor.getHeight()));
			}


			floorRenderer.render(floors, camera);

			if (ingamemenu.getRestartGame()) {
				player.resetPosition();
				player.gameover = false;
				player.playerWins = false;
				presstostart = true;
				ingamemenu.toggleMenu();
				player.fruitsCollectedNull();
				fruits.resetPosition(loader, fruitTexturedModel);
				ghosts.resetPosition();
				ghosts1.resetPosition();
				ghosts2.resetPosition();
			}

			if (ingamemenu.getQuitGame()) {
				shouldQuitGame = true;
				break;
			}

			ingamemenu.render();
			ingamemenu.handleInput();

			if (player.playerWins) {
				fruitsCountText.setColour(0, 0, 1);
				guiRenderer.render(guis);
				player.setCurrentTurnSpeed(0);
				player.setCurrentSpeed(0);
			} else if (player.gameover) {
				guiRenderer.render(guis2);
			}

			TextMaster.render();
			guiRenderer.render(guis3);
			DisplayManager.updateDisplay();
		}

		if (shouldQuitGame) {
			fbos.cleanUp();
			floorShader.cleanUp();
			TextMaster.cleanUp();
			guiRenderer.cleanUP();
			renderer.cleanUp();
			loader.cleanUp();
			DisplayManager.closeDisplay();
		}
	}
}
