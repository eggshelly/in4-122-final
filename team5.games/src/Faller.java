public class Faller {
    String bottom_color;
    String middle_color;
    String top_color;
    int column;
    int row;

    Faller(int col, String[] colors)
    {
        bottom_color = colors[2];
        middle_color = colors[1];
        top_color = colors[0];
        column = col;
        row = 0;
    }
}