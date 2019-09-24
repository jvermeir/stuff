How to if? 

While trying to upgrade my programming skills, I ran into a new-fangled way to do ifs. 
The example below is taken from code that reads properties from a file. A property may or may not 
be defined in the file. If it isn't it should get a default value. 
  
So my classic go-to solution was to use an if statement like this:

    public Integer initTheClassicWay() {
        Integer prop = 42;
        if (properties.getProp() != null) {
            prop = properties.getProp();
        }
        return prop;
    }

While this works, I thought I might as well try something new. Imagine a class with thirty properties or so.
That would result in a noisy method that would probably get flagged by SonarCube as to complex, forcing
me to annotate the code so the warning would be ignored and then everyone who would be reading
this method would have to process the warning and agree it was not important. Lots of wasted future effort.

So I came up with this:

    public Integer initTheOptionalWay() {
        return (Optional.of(properties.getProp()).orElse(42));
    }

Nice, I thought, a one-liner, that is always great, right? But is it better? Looking at this 
version a few days later I thought it hides what is going on. While the classic version is
very specific, more like a human being would reason, the second version seems to be doing its best
to hide its intentions. At least, to me, it looks like that's what it's doing.

So I figured I'd ask a colleague who has the benefit of having grown up with functional programming.
He looked at the code and thought it was nice, but suggested some reformatting, like this:

    public Integer initTheBetterFormattedWay() {
        return (Optional
                .of(properties.getProp())
                .orElse(42));
    }

Aha, I thought, now I can see what's going on, way better. But wait: now I have almost the same number of lines as before, but without the if. 

So, which of these solutions is better?

You can find the code here: https://github.com/jvermeir/stuff/tree/master/javaStuff/src/main/java/ifs
