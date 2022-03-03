package csci2020u.assignment1;

public class Main {
    public static void main(String[] args) {
        Registry registry = new Registry();

        registry.AddComponent(TestComponent1.class);
        registry.AddComponent("TestComponent2");

        System.out.println();

        // Simulate 3 game loops
        for (int i = 0; i < 3; i++) {
            for (Component component : registry.GetAllComponents()){
                component.OnUpdate();
            }

            System.out.println();
        }

        // Destroy the registry, we're done with it
        try {
            registry.finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
