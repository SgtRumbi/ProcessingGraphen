class Knoten {
    /* public class Daten {
        public String oname;
        
        public String ortsnameGeben() {
            return oname;
        }
    } */
    
    public String name;
    public int x;
    public int y;
    public boolean markiert;
    public String colour = "ffffffff";//AlphaAlphaRedRedGreenGreenBlueBlue
    public int radius = 16;

    /* private Daten daten = null;

    public Daten datenLiefern() {
        return this.daten;
    } */
    
    /* public Knoten(String ortsname) {
        this.daten = new Daten();
        this.daten.oname = ortsname;
    } */
    
    public Knoten(String name, int x, int y) {
        this.x = x;
        this.y = y;
        this.name = name;
        markiert = false;
    }
    
    public Knoten(String name, float x, float y) {
        this.x = Math.round(x);
        this.y = Math.round(y);;
        this.name = name;
        markiert = false;
    }
}