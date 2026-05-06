package feast.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Camel8 {

    @Autowired
    private Camel8 camel8;

    @Override
    public String toString() {
        return "and cooked over a charcoal fire.";
    }
}
