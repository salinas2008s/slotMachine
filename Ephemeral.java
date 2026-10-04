public class Ephemeral extends Symbol {
    public Ephemeral(String color) {
        super(color);
    }

    @Override
    public void symbolWeWant(Circle circle) {
        int tamanoActual = circle.getDiameter();
        int nuevoTamano = tamanoActual - 10;
        if (nuevoTamano < 2) {
            nuevoTamano = 2;
        }
        circle.changeSize(nuevoTamano);
    }
}