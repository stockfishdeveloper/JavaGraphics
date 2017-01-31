public class Rotation {
    public boolean clockwise;
    public Axis axis;
    float degrees;
    public Rotation(boolean Clockwise, Axis a, float Degrees)
    {
        clockwise = Clockwise;
        axis = a;
        degrees = Degrees;
    }
}
enum Axis
{
    X, Y, Z
}
