package Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.util.vector.Vector3f;

public class MapReader {
    public static void main(String[] args) {
        String filePath = "src/Map/Map.txt";
        int[][] labyrinthGrid = readFromFile(filePath);
        if (labyrinthGrid != null) {
            for (int[] row : labyrinthGrid) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        }
    }
    public static List<MapSlot> readMapFromFile(String filePath) {
        List<MapSlot> mapSlots = new ArrayList<>();
        char[][] map = null;
        int numRows = 0;
        int numCols = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                numCols = Math.max(numCols, line.length());
                numRows++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        map = new char[numRows][numCols];

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentRow = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                for (int col = 0; col < line.length(); col++) {
                    map[currentRow][col] = line.charAt(col);
                }
                for (int col = line.length(); col < numCols; col++) {
                    map[currentRow][col] = ' ';
                }
                currentRow++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        int id = 1;
        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numCols; x++) {
                mapSlots.add(new MapSlot(id++, x, y, map[y][x]));
            }
        }

        return mapSlots;
    }

    public static int[][] readFromFile(String filePath) {
        List<MapSlot> mapSlots = new ArrayList<>();
        char[][] map = null;
        int numRows = 0;
        int numCols = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                numCols = Math.max(numCols, line.length());
                numRows++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        map = new char[numRows][numCols];

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentRow = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                for (int col = 0; col < line.length(); col++) {
                    map[currentRow][col] = line.charAt(col);
                }
                for (int col = line.length(); col < numCols; col++) {
                    map[currentRow][col] = ' ';
                }
                currentRow++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        int[][] grid = new int[numRows][numCols];
        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numCols; x++) {
                grid[y][x] = (map[y][x] == '=') ? 1 : 0;
            }
        }

        return grid;
    }

    public static List<Vector3f> generateCubePositions(List<MapSlot> mapSlots, float terrainHeight, float cubeSpacing, float cubeSize, float initialOffset, char targetValue) {
        List<Vector3f> positions = new ArrayList<>();
        for (MapSlot slot : mapSlots) {
            if (slot.getValue() == targetValue) {
                float x = initialOffset + slot.getX() * cubeSpacing;
                float y = terrainHeight + cubeSize / 2;
                float z = initialOffset + slot.getY() * cubeSpacing;
                positions.add(new Vector3f(x, y, z));
            }
        }
        return positions;
    }
}
