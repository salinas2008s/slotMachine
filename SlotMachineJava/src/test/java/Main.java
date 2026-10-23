
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // PARTE A: tipos de rueda y de símbolo
        SlotMachine a = new SlotMachine();
        a.addSymbol("normal", 1, "blue");
        a.addSymbol("ephemeral", 2, "green");
        a.addSymbol("shy", 3, "red");
        a.addWheel("normal", 1);
        a.addWheel("lefty", 2);
        a.addWheel("rebel", 3);
        a.addWheel("skipper", 4);
        a.addWheel("turbo", 5);
        a.addSymbol("turbo", 4, "white");
        a.makeVisible();
        a.spin();
        a.makeInvisible();

        // PARTE B: Lefty, Rebel y Skipper
        SlotMachine b = new SlotMachine();
        b.addSymbol("normal", 1, "blue");
        b.addSymbol("normal", 2, "red");
        b.addWheel("normal", 1);
        b.addWheel("lefty", 2);
        b.addWheel("rebel", 3);
        b.addWheel("skipper", 4);
        b.makeVisible();
        b.placeSymbol(1, "blue");
        b.spin(2);
        b.placeSymbol(1, "red");
        b.spin(2);
        b.spin();
        b.spin(4);
        b.placeSymbol(3, "blue");
        b.lock(3);
        b.spin(3);
        b.swap(1, 3);
        b.swap(3, 4);
        b.delWheel(3);
        b.placeSymbol(1, "red");
        b.placeSymbol(4, "blue");
        b.lock(4);
        b.unlock(4);
        b.swap(1, 4);
        b.delWheel(4);
        b.makeInvisible();

        // PARTE C: símbolos Normal, Ephemeral y Shy
        SlotMachine c = new SlotMachine();
        c.delSymbol("black");
        c.addSymbol("normal", 1, "blue");
        c.addWheel("normal", 1);
        c.makeVisible();
        c.spin(1);
        c.makeInvisible();

        SlotMachine d = new SlotMachine();
        d.delSymbol("black");
        d.addSymbol("ephemeral", 1, "red");
        d.addWheel("normal", 1);
        d.makeVisible();
        d.spin(1);
        d.spin(1);
        d.spin(1);
        d.spin(1);
        d.spin(1);
        d.spin(1);
        d.makeInvisible();

        SlotMachine e = new SlotMachine();
        e.delSymbol("black");
        e.addSymbol("shy", 1, "green");
        e.addWheel("normal", 1);
        e.makeVisible();
        e.spin(1);
        e.spin(1);
        e.spin(1);
        e.spin(1);
    }
}