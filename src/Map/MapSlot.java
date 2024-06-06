package Map;

class MapSlot {
    private int id;
    private int x;
    private int y;
    private char value;

    public MapSlot(int id, int x, int y, char value) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "MapSlot{id=" + id + ", x=" + x + ", y=" + y + ", value=" + value + "}";
    }
}
