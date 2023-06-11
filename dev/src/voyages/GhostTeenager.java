package voyages;

/** The {@code GhostTeenager} class, it represents a ghost teenager. It is used to fill teenager lists that are not the same size.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 06/11/23
 */
public class GhostTeenager extends Teenager {

    public GhostTeenager() {
        super("Dummy", "Dummy", null, null);
    }
    
    /** This method always return {@code true}.
     * @return {@code true}.
     */
    @Override
    public boolean isGhost() {
        return true;
    }
}