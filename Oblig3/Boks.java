class Boks extends A {
    int id;
    Rute[][] data;
    int csize;
    int rsize;
    String type = "Boks";

    Boks(int id) {
	super(id);
    }
    
    void giveItSelfToRute() {
	for (int i = 0; i < rsize; i++) {
	    for (int j = 0; j < csize; j++) {
		data[i][j].setMyBox(this);
	    }
	}
    }
}

