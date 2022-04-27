package models.utils.updates;

/**
 * <p>IUpdatable is used by classes which require update calls for an update thread.</p>
 * @author Andrew Stephens
 */
public interface IUpdatable {

    /**
     * <p>Update method accepts the actual/target ratio to regulate the update rate.</p>
     * @param delta The ratio of actual/target updates.
     */
    void update(float delta);

}
