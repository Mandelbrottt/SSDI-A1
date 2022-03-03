package csci2020u.assignment1;

public class TestComponent1 extends Component {
    @Override
    public void OnCreate() {
        System.out.println("TestComponent1 OnCreate()");
    }
    
    @Override
    public void OnUpdate() {
        System.out.println("TestComponent1 OnUpdate()");
    }
    
    @Override
    public void OnDestroy() {
        System.out.println("TestComponent1 OnDestroy()");
    }
}
