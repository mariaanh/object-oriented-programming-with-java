class Kolonne extends A {
    int id;
    Rute[][] data;
    int rsize;
    int csize;
    String type = "Kolonne";
    
    Kolonne(int id) {
	super(id);
    }

    void giveItSelfToRute() {
	for (int i = 0; i < rsize; i++) {
	    for (int j = 0; j < csize; i++) {
		data[i][j].setMyColumn(this);
	    }
	}
    }
}