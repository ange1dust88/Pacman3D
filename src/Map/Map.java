package Map;

import org.lwjgl.util.vector.Vector3f;

import java.util.List;

public class Map {

    String filePath = "src/Map/Map.txt";
    List<MapSlot> mapSlots;

    public Map()
    {
        mapSlots = MapReader.readMapFromFile(filePath);
    }
    public List<Vector3f> generateMapObjects(float terrainHeight, float cubeSpacing, float cubeSize, float initialOffset, char targetValue)
    {
        return MapReader.generateCubePositions(mapSlots, terrainHeight, cubeSpacing, cubeSize, initialOffset, targetValue);
    }

}
