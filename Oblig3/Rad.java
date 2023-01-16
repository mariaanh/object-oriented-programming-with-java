class Rad extends A {
    int id;
    Rute[][] data;
    int csize;
    int rsize;
    String type = "Rad";

    Rad(int id) {
	super(id);
    }

    void giveItSelfToRute() {
	for (int i = 0; i < rsize; i++) {
	    for (int j = 0; i < csize; j++) {
		data[i][j].setMyRow(this);
	    }
	}
    }
}