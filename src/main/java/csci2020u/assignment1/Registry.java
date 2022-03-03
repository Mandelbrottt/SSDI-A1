package csci2020u.assignment1;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

/**
 * Public registry that keeps track of a list of components. Users can Add, Get,
 * and Remove components
 * from the registry. The registry uses runtime information to create and
 * retrieve components
 */
public class Registry {
    @Override
    protected void finalize() throws Throwable {
        for (Component component : components.values()) {
            if (component != null) {
                component.onDestroy();
            }
        }
    }

    public Collection<Component> getAllComponents() {
        return components.values();
    }

    /**
     * Creates a component of class type and adds it to the registry
     * 
     * @param type The runtime class of the component to create
     * @return A reference to the created component if a valid class is passed in,
     *         otherwise null
     * @remark Do not pass in Component.class, as this will result in a runtime
     *         error.
     */
    public Component addComponent(Class<? extends Component> type) {
        int hashcode = type.hashCode();

        // Return reference to component if it already exists
        if (components.containsKey(hashcode)) {
            return components.get(hashcode);
        }

        Component newComponent;

        // Try to create the component
        try {
            var constructor = type.getConstructor();

            newComponent = constructor.newInstance();
        } catch (
                NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }

        // Set the component's registry so that they can make use of its component
        // functions
        newComponent.setRegistry(this);

        components.put(hashcode, newComponent);

        // Call OnCreate after m_components.put in case OnCreate gets the newly added
        // component somewhere
        newComponent.onCreate();

        return newComponent;
    }

    /**
     * Creates a component of the class with name className and adds it to the
     * registry
     * 
     * @param typeName The name of the component to create
     * @return A reference to the created component if a valid class name is passed
     *         in, otherwise null
     */
    public Component addComponent(String typeName) {
        @SuppressWarnings("rawtypes")
        Class c = getClassNameFromString(typeName);

        @SuppressWarnings("unchecked")
        Component result = addComponent(c);

        return result;
    }

    /**
     * Gets the component of class type
     * 
     * @param type The runtime class of the component to get
     * @return A reference to the component if a valid class is passed in, otherwise
     *         null
     * @remark Do not pass in Component.class, as this will result in a runtime
     *         error.
     */
    public Component getComponent(Class<? extends Component> type) {
        int hashcode = type.hashCode();

        if (components.containsKey(hashcode)) {
            return components.get(hashcode);
        }

        return null;
    }

    /**
     * Gets the component of class with the name typeName
     * 
     * @param typeName The name of the component to create
     * @return A reference to the component if a valid class is passed in, otherwise
     *         null
     * @remark Do not pass in Component.class, as this will result in a runtime
     *         error.
     */
    public Component getComponent(String typeName) {
        @SuppressWarnings("rawtypes")
        Class c = getClassNameFromString(typeName);

        @SuppressWarnings("unchecked")
        Component result = getComponent(c);

        return result;
    }

    /**
     * Removes a component of the class with name className from the registry
     * 
     * @param c The runtime class of the component to remove
     * @remark Component.class is not a valid class.
     */
    public void removeComponent(Class<? extends Component> c) {
        int hashcode = c.hashCode();

        // Call OnDestroy if the component exists
        if (components.containsKey(hashcode)) {
            components.get(hashcode).onDestroy();
        }

        components.remove(hashcode);
    }

    /**
     * Removes a component of the class with name className from the registry
     * 
     * @param typeName The name of the component to remove
     */
    @SuppressWarnings("unchecked")
    public void removeComponent(String className) {
        @SuppressWarnings("rawtypes")
        Class c = getClassNameFromString(className);

        removeComponent(c);
    }

    /**
     * Gets the runtime class with name className in the current module
     * 
     * @param className the unqualified name of the class
     * @return A valid runtime class if the class was found, otherwise null
     */
    @SuppressWarnings("rawtypes")
    private Class getClassNameFromString(String className) {
        Class c;
        try {
            // Contrived example that only gets classes from current
            // module but it'll do for this example
            c = Class.forName("csci2020u.assignment1." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return c;
    }

    private Map<Integer, Component> components = new Hashtable<Integer, Component>();
}
