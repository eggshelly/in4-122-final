public class Faller {
    int bottom_color;
    int middle_color;
    int top_color;
    int column;
    int row;

    Faller(int col, int[] colors)
    {
        bottom_color = colors[2];
        middle_color = colors[1];
        top_color = colors[0];
        column = col;
        row = 0;
    }
}