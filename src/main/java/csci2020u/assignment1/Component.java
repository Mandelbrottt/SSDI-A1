package csci2020u.assignment1;

abstract class Component {
    /**
     * Called when the object is first constructed by the engine
     */
    public void OnCreate()  {}

    /**
     * Called once every frame
     */
    public void OnUpdate()  {}

    /**
     * Called when the component is no longer being used by the engine
     */
    public void OnDestroy() {}

    protected Registry GetRegistry() {
        return registry;
    }
    
    public void SetRegistry(Registry registry) {
        this.registry = registry;
    }

    private Registry registry;
}
