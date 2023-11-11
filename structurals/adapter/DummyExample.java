package structurals.adapter;

// LEGACY CODE
class LegacyService {
    public void doSomething() {
        System.out.println("Legacy service is doing something");
    }
}

// ADAPTEE
class Service {
    void doSomething(){
        System.out.println("Service is doing something");
    }
}

// ADAPTER
class LegacyServiceAdapter extends LegacyService {
    private Service service;

    public LegacyServiceAdapter(Service service) {
        this.service = service;
    }

    @Override
    public void doSomething() {
        service.doSomething();
    }
}

public class DummyExample {
    public static void main(String[] args){
        Service service = new Service();
        LegacyService adapter = new LegacyServiceAdapter(service);
        adapter.doSomething();
    }
}