package csci2020u.assignment1;

abstract class Component {
    /**
     * Called when the object is first constructed by the engine
     */
    public void onCreate()  {}

    /**
     * Called once every frame
     */
    public void onUpdate()  {}

    /**
     * Called when the component is no longer being used by the engine
     */
    public void onDestroy() {}

    protected Registry getRegistry() {
        return registry;
    }
    
    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    private Registry registry;
}
