public class Tiefensuche {
    private boolean[] besucht;
    private Knoten[] knoten;
    private int[][] adjazenzmatrix;
    private final int maxKnoten;
    
    // Add
    private int currentNodeIndex = 0;
    
    private void pushNode(int index, Knoten knoten) {
        if (currentNodeIndex >= maxKnoten) {
            System.err.println("Cannot add node!");
        } else {
            this.knoten[currentNodeIndex] = knoten;
            currentNodeIndex += 1;
        }
    }
    
    // Brute-Force
    private String aktWeg = "";
    private final int infinity = Integer.MAX_VALUE;
    private int aktLaenge = 0;
    
    public Tiefensuche(int maxKnoten) {
        this.maxKnoten = maxKnoten;
        besucht = new boolean[maxKnoten];
        adjazenzmatrix = new int[maxKnoten][maxKnoten];
        knoten = new Knoten[maxKnoten];
    }
    
    public void tiefensucheSchritt(int knotenIdx) {
        besucht[knotenIdx]=true;
        System.out.println(knoten[knotenIdx].name);
        //System.out.println(knotenfeld[knotenIdx].datenLiefern().ortsnameGeben());
        for(int i=0; i < besucht.length; i++) {
            if (adjazenzmatrix[knotenIdx][i]>0 && besucht[i]==false) {
                tiefensucheSchritt(i);
            }
        }
    }
    
    public void tiefensuche(int startKnotenIdx) {
        for (int i=0;i<besucht.length; i++) {
            besucht[i]=false;
        }
        tiefensucheSchritt(startKnotenIdx);
    }
    
    public int[][] matrix() {
        return adjazenzmatrix;
    }
    
    public void wegeSuchen(int startKnotenIndex, int zielKnotenIndex) {
        aktWeg = "";
        aktLaenge = infinity;
        for(int i = 0; i < besucht.length; ++i) {
            besucht[i] = false;
        }
        ablaufen(startKnotenIndex, zielKnotenIndex, knoten[startKnotenIndex].name, 0);
        System.out.println("Bester Weg: " + aktWeg);
    }
    
    private void ablaufen(int aktKnotenIndex, int zielKnotenIndex, String pfad, int laenge) {
        int neueLaenge = 0;
        String neuerPfad = "";
        besucht[aktKnotenIndex] = true;
        
        if(aktKnotenIndex == zielKnotenIndex) {
            if(laenge == aktLaenge) {
                aktWeg += "\n" + pfad + ": " + aktLaenge;
                System.out.println("Gleich guten Weg gefunden!");
            } else if(laenge < aktLaenge) {
                System.out.println("Besseren Weg als '" + aktWeg + "' gefundnen: '" + pfad + "'!");
                aktLaenge = laenge;
                aktWeg = pfad + ": " + aktLaenge;
            }
        } else {
            for(int i = 0; i < maxKnoten; ++i) {
                if(matrix()[aktKnotenIndex][i] > 0 && besucht[i] == false) {
                    neuerPfad = pfad + "/" + knoten[i].name;
                    neueLaenge = laenge + matrix()[aktKnotenIndex][i];
                    ablaufen(i, zielKnotenIndex, neuerPfad, neueLaenge);
                }
            }
        }
    }
}
