public class Shy extends Symbol {
    public Shy(String color) {
        super(color);
    }

    @Override
    public void symbolWeWant(Circle circle) {
        if (circle.isVisible()) {
            circle.makeInvisible();
        } else {
            circle.makeVisible();
        }
    }
}