

package entities;

import Map.MapReader;
import Map.*;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {

    private final List<Entity> labyrinthCubes;


    public Labyrinth(Loader loader, TexturedModel TexturedModel) {
        labyrinthCubes = generateWalls(loader, TexturedModel);
    }

    public List<Entity> getLabyrinthCubes() {
        return labyrinthCubes;
    }

    private static List<Entity> generateWalls(Loader loader, TexturedModel TexturedModel) {
        List<Entity> cubes = new ArrayList<>();

        float cubeSize = 10f;
        float terrainHeight = -2;

        float cubeSpacing = 6.0f;
        float initialOffset = 3.0f;

        Map map = new Map();
        //labyrinth walls
        //(Vector3f[])map.generateMapObjects(terrainHeight, cubeSpacing, initialOffset, '=').toArray();
        Vector3f[] cubePositions = map.generateMapObjects(terrainHeight, cubeSpacing, cubeSize, initialOffset, '=').toArray(new Vector3f[0]);
                /*{
                //borders
                new Vector3f(3, terrainHeight + cubeSize / 2, 3),
                new Vector3f(3, terrainHeight + cubeSize / 2, 9),
                new Vector3f(3, terrainHeight + cubeSize / 2, 15),
                new Vector3f(3, terrainHeight + cubeSize / 2, 21),
                new Vector3f(3, terrainHeight + cubeSize / 2, 27),
                new Vector3f(3, terrainHeight + cubeSize / 2, 33),
                new Vector3f(3, terrainHeight + cubeSize / 2, 39),
                new Vector3f(3, terrainHeight + cubeSize / 2, 45),
                new Vector3f(3, terrainHeight + cubeSize / 2, 51),
                new Vector3f(3, terrainHeight + cubeSize / 2, 57),
                new Vector3f(3, terrainHeight + cubeSize / 2, 63),
                new Vector3f(3, terrainHeight + cubeSize / 2, 69),
                new Vector3f(3, terrainHeight + cubeSize / 2, 75),
                new Vector3f(3, terrainHeight + cubeSize / 2, 81),
                new Vector3f(3, terrainHeight + cubeSize / 2, 87),

                new Vector3f(9, terrainHeight + cubeSize / 2, 87),
                new Vector3f(15, terrainHeight + cubeSize / 2, 87),
                new Vector3f(21, terrainHeight + cubeSize / 2, 87),
                new Vector3f(27, terrainHeight + cubeSize / 2, 87),
                new Vector3f(33, terrainHeight + cubeSize / 2, 87),
                new Vector3f(39, terrainHeight + cubeSize / 2, 87),
                new Vector3f(45, terrainHeight + cubeSize / 2, 87),
                new Vector3f(51, terrainHeight + cubeSize / 2, 87),
                new Vector3f(57, terrainHeight + cubeSize / 2, 87),
                new Vector3f(63, terrainHeight + cubeSize / 2, 87),
                new Vector3f(69, terrainHeight + cubeSize / 2, 87),
                new Vector3f(75, terrainHeight + cubeSize / 2, 87),
                new Vector3f(81, terrainHeight + cubeSize / 2, 87),
                new Vector3f(87, terrainHeight + cubeSize / 2, 87),


                new Vector3f(87, terrainHeight + cubeSize / 2, 3),
                new Vector3f(87, terrainHeight + cubeSize / 2, 9),
                new Vector3f(87, terrainHeight + cubeSize / 2, 15),
                new Vector3f(87, terrainHeight + cubeSize / 2, 21),
                new Vector3f(87, terrainHeight + cubeSize / 2, 27),
                new Vector3f(87, terrainHeight + cubeSize / 2, 33),
                new Vector3f(87, terrainHeight + cubeSize / 2, 39),
                new Vector3f(87, terrainHeight + cubeSize / 2, 45),
                new Vector3f(87, terrainHeight + cubeSize / 2, 51),
                new Vector3f(87, terrainHeight + cubeSize / 2, 57),
                new Vector3f(87, terrainHeight + cubeSize / 2, 63),
                new Vector3f(87, terrainHeight + cubeSize / 2, 69),
                new Vector3f(87, terrainHeight + cubeSize / 2, 75),
                new Vector3f(87, terrainHeight + cubeSize / 2, 81),
                new Vector3f(87, terrainHeight + cubeSize / 2, 87),

                new Vector3f(9, terrainHeight + cubeSize / 2, 3),
                new Vector3f(15, terrainHeight + cubeSize / 2, 3),
                new Vector3f(21, terrainHeight + cubeSize / 2, 3),
                new Vector3f(27, terrainHeight + cubeSize / 2, 3),
                new Vector3f(33, terrainHeight + cubeSize / 2, 3),
                new Vector3f(39, terrainHeight + cubeSize / 2, 3),
                new Vector3f(45, terrainHeight + cubeSize / 2, 3),
                new Vector3f(51, terrainHeight + cubeSize / 2, 3),
                new Vector3f(57, terrainHeight + cubeSize / 2, 3),
                new Vector3f(63, terrainHeight + cubeSize / 2, 3),
                new Vector3f(69, terrainHeight + cubeSize / 2, 3),
                new Vector3f(75, terrainHeight + cubeSize / 2, 3),
                new Vector3f(81, terrainHeight + cubeSize / 2, 3),
                new Vector3f(87, terrainHeight + cubeSize / 2, 3),

                new Vector3f(39, terrainHeight + cubeSize / 2, 21),
                new Vector3f(51, terrainHeight + cubeSize / 2, 21),
                new Vector3f(39, terrainHeight + cubeSize / 2, 33),
                new Vector3f(51, terrainHeight + cubeSize / 2, 33),
                new Vector3f(39, terrainHeight + cubeSize / 2, 39),
                new Vector3f(51, terrainHeight + cubeSize / 2, 39),
                new Vector3f(39, terrainHeight + cubeSize / 2, 45),
                 new Vector3f(51, terrainHeight + cubeSize / 2, 45),
                new Vector3f(39, terrainHeight + cubeSize / 2, 57),
                new Vector3f(51, terrainHeight + cubeSize / 2, 57),
                new Vector3f(39, terrainHeight + cubeSize / 2, 63),
                new Vector3f(51, terrainHeight + cubeSize / 2, 63),
                new Vector3f(39, terrainHeight + cubeSize / 2, 69),
                new Vector3f(39, terrainHeight + cubeSize / 2, 75),
                new Vector3f(51, terrainHeight + cubeSize / 2, 75),
                new Vector3f(57, terrainHeight + cubeSize / 2, 21),
                new Vector3f(63, terrainHeight + cubeSize / 2, 21),
                new Vector3f(69, terrainHeight + cubeSize / 2, 21),
                new Vector3f(75, terrainHeight + cubeSize / 2, 21),
                new Vector3f(57, terrainHeight + cubeSize / 2, 33),
                new Vector3f(63, terrainHeight + cubeSize / 2, 33),
                new Vector3f(75, terrainHeight + cubeSize / 2, 33),
                new Vector3f(63, terrainHeight + cubeSize / 2, 39),
                new Vector3f(75, terrainHeight + cubeSize / 2, 39),
                new Vector3f(75, terrainHeight + cubeSize / 2, 45),
                new Vector3f(63, terrainHeight + cubeSize / 2, 51),
                new Vector3f(75, terrainHeight + cubeSize / 2, 51),
                new Vector3f(69, terrainHeight + cubeSize / 2, 51),
                new Vector3f(57, terrainHeight + cubeSize / 2, 63),
                new Vector3f(63, terrainHeight + cubeSize / 2, 63),
                new Vector3f(69, terrainHeight + cubeSize / 2, 63),
                new Vector3f(75, terrainHeight + cubeSize / 2, 63),
                new Vector3f(75, terrainHeight + cubeSize / 2, 69),
                new Vector3f(75, terrainHeight + cubeSize / 2, 75),
                new Vector3f(63, terrainHeight + cubeSize / 2, 75),
                new Vector3f(57, terrainHeight + cubeSize / 2, 75),
                new Vector3f(33, terrainHeight + cubeSize / 2, 21),
                new Vector3f(27, terrainHeight + cubeSize / 2, 21),
                new Vector3f(15, terrainHeight + cubeSize / 2, 15),
                new Vector3f(51, terrainHeight + cubeSize / 2, 15),
                new Vector3f(39, terrainHeight + cubeSize / 2, 15),
                new Vector3f(15, terrainHeight + cubeSize / 2, 21),
                new Vector3f(15, terrainHeight + cubeSize / 2, 33),
                new Vector3f(27, terrainHeight + cubeSize / 2, 33),
                new Vector3f(27, terrainHeight + cubeSize / 2, 39),
                new Vector3f(27, terrainHeight + cubeSize / 2, 45),
                new Vector3f(15, terrainHeight + cubeSize / 2, 51),
                new Vector3f(27, terrainHeight + cubeSize / 2, 57),
                new Vector3f(27, terrainHeight + cubeSize / 2, 63),
                new Vector3f(21, terrainHeight + cubeSize / 2, 57),
                new Vector3f(15, terrainHeight + cubeSize / 2, 57),
                new Vector3f(21, terrainHeight + cubeSize / 2, 33),
                new Vector3f(15, terrainHeight + cubeSize / 2, 69),
                new Vector3f(27, terrainHeight + cubeSize / 2, 69),
                new Vector3f(27, terrainHeight + cubeSize / 2, 75),
                new Vector3f(15, terrainHeight + cubeSize / 2, 75),
    };*/


        for (Vector3f position : cubePositions) {
            float x = position.x;
            float y = position.y;
            float z = position.z;
            cubes.add(new Entity(TexturedModel, new Vector3f(x, y, z), 0, 0, 0, 3));
        }


        return cubes;
    }
}
