package voyages;

public class GhostTeenager extends Teenager {

    public GhostTeenager() {
        super("Dummy", "Dummy", null, null);
    }
    
    public boolean isGhost() {
        return true;
    }
}