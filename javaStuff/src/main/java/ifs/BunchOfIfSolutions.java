package ifs;

import java.util.Optional;

public class BunchOfIfSolutions {
    MyStuff stuff = new MyStuff();

    public Integer initTheClassicWay() {
        Integer prop = 42;
        if (stuff.getProp() != null) {
            prop = stuff.getProp();
        }
        return prop;
    }

    public Integer initTheOptionalWay() {
        return (Optional.of(stuff.getProp()).orElse(42));
    }

    public Integer initTheBetterFormattedWay() {
        return (Optional
                .of(stuff.getProp())
                .orElse(42));
    }
}

class MyStuff {
    private Integer prop;

    MyStuff() {
        // compute a value for this.prop. The value may be null.
    }

    public void setProp(Integer prop) {
        this.prop = prop;
    }

    public Integer getProp() {
        return prop;
    }
}
