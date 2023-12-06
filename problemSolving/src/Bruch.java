public class Bruch {

    private final long zähler;
    private final long nenner;

    public Bruch(long zähler, long nenner) {
        this.zähler = zähler;
        this.nenner = nenner;
    }

    public Bruch add(Bruch b) {
        long neuerNenner = this.nenner * b.nenner;
        long neuerZähler = this.zähler * b.nenner + b.zähler * this.nenner;
        return new Bruch(neuerZähler, neuerNenner).kürze();
    }

    public Bruch sub(Bruch b) {
        return this.add( b.negiere() );
    }

    public Bruch mult(Bruch b) {
        long neuerZähler = this.zähler * b.zähler;
        long neuerNenner = this.nenner * b.nenner;
        return new Bruch(neuerZähler, neuerNenner).kürze();
    }

    public Bruch div(Bruch b) {
        return this.mult( b.kehrWert() );
    }

    public Bruch kehrWert() {
        return new Bruch( nenner, zähler );
    }

    public Bruch negiere() {
        long neuerZähler = -this.zähler;
        long neuerNenner = this.nenner;
        if (neuerZähler < 0 && neuerNenner < 0) {
            neuerZähler = -neuerZähler;
            neuerNenner = -neuerNenner;
        }
        return new Bruch( neuerZähler, neuerNenner );
    }

    public Bruch kürze() {
        long teiler = ggT(zähler, nenner);
        return new Bruch( zähler/teiler, nenner/teiler );
    }

    public boolean isEqual(Bruch b) {
        // wann sind zwei Brüche gleich?
//        Bruch a1 = this.kürze();
//        Bruch b1 = b.kürze();
//        return (a1.zähler == b1.zähler && a1.nenner == b1.nenner);
        Bruch ergebnis = this.div(b);
        return (ergebnis.zähler == ergebnis.nenner);
    }

    private long ggT(long a, long b) {
        if (a == 0) return b;
        if (b == 0) return a;
        if (a < 0) a = -a;  // a = Math.abs(a)
        if (b < 0) b = -b;

        while (a != b) {
            if (a > b) a = a - b;
            else       b = b - a;
        }
        return a;
    }

    public String toString() {
        return zähler + "/" + nenner;
    }
}
