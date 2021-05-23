package entity;

import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.Float.*;
public class AtomicFloat extends Number {

    private AtomicInteger bits;

    public AtomicFloat() {
        this(0f);
    }

    public AtomicFloat(float initialValue) {
        bits = new AtomicInteger(floatToIntBits(initialValue));
    }

    public final void set(float newValue) {
        bits.set(floatToIntBits(newValue));
    }

    public final float get() {
        return intBitsToFloat(bits.get());
    }

    public float floatValue() {
        return get();
    }


    public double doubleValue() { return (double) floatValue(); }
    public int intValue()       { return (int) get();           }
    public long longValue()     { return (long) get();          }

}