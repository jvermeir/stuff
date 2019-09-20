package ifs;

import java.util.Optional;
import java.util.Properties;

public class BunchOfIfSolutions {
    MyProperties properties = new MyProperties();

    public Integer initTheClassicWay() {
        Integer prop = 42;
        if (properties.getProp() != null) {
            prop = properties.getProp();
        }
        return prop;
    }

    public Integer initTheOptionalWay() {
        return (Optional.of(properties.getProp()).orElse(42));
    }

    public Integer initTheBetterFormattedWay() {
        return (Optional
                .of(properties.getProp())
                .orElse(42));
    }
}

class MyProperties extends Properties {
    private Integer prop;

    MyProperties() {
        this.prop = 42;
    }

    public void setProp(Integer prop) {
        this.prop = prop;
    }

    public Integer getProp() {
        return prop;
    }
}
