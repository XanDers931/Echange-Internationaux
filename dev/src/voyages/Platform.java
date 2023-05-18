package voyages;

import java.util.ArrayList;

/**The Platform class. It represents the platform that contains all the teenagers.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.2, 05/12/23
 */
public class Platform {
    
    /**
     * The list of teenagers.
     */
    ArrayList<Teenager> teenagers;

    /**The constructor for the {@code Platform} class.
     */
    public Platform() {
        this.teenagers = new ArrayList<Teenager>();
    }
}