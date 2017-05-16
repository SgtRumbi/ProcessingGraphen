import processing.core.PApplet;

import javax.swing.*;

public class GraphBasic extends PApplet {
    //Zu Lehrzwecken werden alle Attribute auf public gesetzt
    //Darstellungsattribute
    public boolean showInfos = true;
    public boolean verschiebbar = true;
 
    //Datenattribute
    public int maxKnoten = 10;
    public Knoten[] knoten = new Knoten[maxKnoten];
    private int[][] adjazenzmatrix = new int[maxKnoten][maxKnoten];
    public boolean[] besucht = new boolean[maxKnoten];

    //Algorithmenattribute
    public int startIndex = -1;
    public int zielIndex = -1;

    private long lastT;
    private long fCounter;

    private DFS dfs = new DFS();
    private BFS bfs = new BFS();
    
    public void settings() {
        //Fenstergroesse
        size(640, 360);
    }
    
    public void setup() {
        init();
    }
    
    public void init() {
        //Erzeugen von 20 Zufallspunkten.
        for (int i = 0; i < knoten.length; i++) {          
          knoten[i] = new Knoten(""+i,random(20,width-20),random(20, height-20));
        }
        
        //Erzeugen der Kanten
        for(int i=0; i < maxKnoten; i=i+1) {
            for(int j=0; j < maxKnoten; j=j+1) {
                if(i == j) { //Diagonale mit Nullen belegen
                    adjazenzmatrix[i][j] = 0;
                } else {
                    int zufallsGewicht = (int)random(1,60);
                    if (zufallsGewicht%4==0)
                        adjazenzmatrix[i][j] = zufallsGewicht;
                    else
                        adjazenzmatrix[i][j] = 0;
                    adjazenzmatrix[j][i] = adjazenzmatrix[i][j];
                }
            }
        }
        
        startIndex = -1;
        zielIndex = -1;
    }
    
    public void matrixAusgeben() {
        for(int i=0; i < maxKnoten; i=i+1) {
            for(int j=0; j < maxKnoten; j=j+1) {
                System.out.print(adjazenzmatrix[i][j]+"\t");
            }
            System.out.println();
        }
    }
    
    public void tiefensuche(int startKnotenIndex) {
        System.out.println("Neustarten der Tiefensuche.");
        
        dfs.cacheGraph(knoten, adjazenzmatrix);
        dfs.start(startKnotenIndex);
    }
    
    public void breitensuche(int startKnotenIndex) {
        System.out.println("Neustarten der Breitensuche.");

        bfs.cacheGraph(knoten, adjazenzmatrix);
        bfs.start(startKnotenIndex);
    }
    
    public void keyPressed() {
        if (keyPressed) {
            //Show Infos
            if (key == 'i' || key == 'I') {
                showInfos = !showInfos;
            }

            if (key == 'e' || key == 'E') {
                matrixAusgeben();
            }

            //New Graph
            if (key == 'n' || key == 'N') {
                init();
            }

            if (key == 'm' || key == 'M') {
                verschiebbar = ! verschiebbar;
            }

            if (key == 'd' || key == 'D') {
                //JOptionPane.showMessageDialog(null, "Kein Algorihmus implementiert");
                if(startIndex>=0) {
                    // tiefensuche(startIndex);
                    // Set start flag
                    // tiefensucheRestartFlag = true;
                    tiefensuche(startIndex);
                } else {
                    // TODO(js): Error handling
                }
            }

            if(key == 'b' || key == 'B') {
                if(startIndex >= 0) {
                    breitensuche(startIndex);
                } else {
                    // TODO(js): Error handling
                }
            }

            if(key == 'l' || key == 'L') {
                if(startIndex >= 0 && zielIndex >= 0) {
                    System.out.println("Wege suchen... Von " + startIndex + " nach " + zielIndex + ".");
                    // wegeSuchen(startIndex, zielIndex);
                    BruteForceWaySearch bfws = new BruteForceWaySearch(knoten, adjazenzmatrix);
                    bfws.start(startIndex, zielIndex);
                }
            }

            if(key == 'p' || key == 'P') {
                if(startIndex >= 0 && zielIndex >= 0) {
                    System.out.println("Dijkstra...");
                    // wegeSuchen(startIndex, zielIndex);
                    DijkstraWaySearch dws = new DijkstraWaySearch(knoten, adjazenzmatrix);
                    dws.start(startIndex, zielIndex);
                    this.knoten = dws.gibKnoten();
                }
            }

            if (key == 'h' || key == 'H') {
                String curText = "i: Infos der Knoten anzeigen\n";
                curText += "e: Adjazenzmatrix ausgeben\n";
                curText += "n: Neuen Graph erzeugen.\n";
                curText += "m: Knoten verschiebbar machen.\n";
                curText += "     Anschlie?end mit Maus \n";
                curText += "     irgendeinen Knoten verschieben.\n";
                curText += "a: Aktuellen Algorithmus starten.\n";
                curText += "s+Maus: Startknoten (ent)markieren.\n";
                curText += "t+Maus: Zielknoten (ent)markieren.\n";
                JOptionPane.showMessageDialog(null, curText);
            }
        }
    }
    
    public void mouseDragged() {
        for(Knoten k: knoten) {
            if(abs(k.x-mouseX)<k.radius && abs(k.y-mouseY)<k.radius) {
                //Knoten verschieben
                if(verschiebbar) {
                    k.x=mouseX;
                    k.y=mouseY;
                }
            }
        }
    }
    
    public void mousePressed() {
        for (int i = 0; i < maxKnoten; i++) {
            Knoten k = knoten[i];
            if (abs(k.x-mouseX)<k.radius && abs(k.y-mouseY)<k.radius) {
                if (keyPressed && (key == 's' || key == 'S' )) {
                    if (k.colour.equals("ff00ff00")) { //Gruen
                        k.colour = "ffffffff"; //Weiss
                        startIndex = -1;
                    } else {
                        if (startIndex == -1) {
                            k.colour = "ff00ff00"; //Gruen: Startpunkt
                            startIndex = i;
                        }
                    }
                }
                if (keyPressed && (key == 't' || key == 'T' )) {
                    if (k.colour.equals("ffff0000")) { //Rot
                        k.colour = "ffffffff"; //Weiss
                        zielIndex = -1;
                    } else {
                        if (zielIndex == -1) {
                            k.colour = "ffff0000"; //Rot Ziel(Target)
                            zielIndex = i;
                        }
                    }
                }
            }
        }
    }
    
    public void draw() {
        //wird automatisch Durchlaufen und nie angehalten
        
        background(51); //51 ist dunkles Grau, sonst 0...255
        fill(255);
        stroke(255);

        long thisT = System.currentTimeMillis();
        long dT = thisT - lastT;
        lastT = thisT;
        
        if(fCounter > 1000) {
            // 1 sec vorbei... Animieren...?
            fCounter = 0;
            /* if (!q.isEmpty()) {
                breitensucheSchritt();
            } */

            if(dfs.isStarted() && bfs.isStarted()) {
                System.err.println("DFS und BFS können (noch) nicht paralell animiert werden!");
            } else {
                if (dfs.isStarted()) {
                    dfs.step();
                    this.knoten = dfs.gibKnoten();
                }

                if (bfs.isStarted()) {
                    bfs.step();
                    this.knoten = bfs.gibKnoten();
                }
            }
        } else {
            fCounter += dT;
        }
        
        stroke(255);
        
        // Kanten zeichnen
        for (int i = 0; i < maxKnoten-1; i++) {
            for (int j = i+1; j < maxKnoten; j++) {
                if (adjazenzmatrix[i][j]>0 && j>i) { //Nur obere Dreiecksmatrix
                    switch(adjazenzmatrix[i][j]) {
                        case 1: {
                            stroke(255);
                        } break;
                        
                        case 2: {
                            stroke(255, 0, 0);
                        } break;
                        
                        case 3: {
                            stroke(0, 255, 0);
                        } break;
                    }

                    line(knoten[i].x, knoten[i].y, knoten[j].x, knoten[j].y);
                }
            }
        }
       
        stroke(255);
        
        //Knoten zeichnen
        for(int i=0; i < maxKnoten; i++) {
            fill(unhex(knoten[i].colour));
            ellipse(knoten[i].x, knoten[i].y, knoten[i].radius, knoten[i].radius);

            //Das Zeigen der Beschreibung verz?gert die Ausfuehrung anfangs
            if (showInfos) {
                if (knoten[i].name.length()<2) { //Kurze Namen im Knoten
                    fill(51);
                    text(knoten[i].name, knoten[i].x-3, knoten[i].y+5);
                } else { //lange Namen daneben
                    fill(255);
                    text(knoten[i].name, knoten[i].x+8, knoten[i].y+4);
                }
            }
        }
    
    }
}