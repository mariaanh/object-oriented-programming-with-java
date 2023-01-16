class Rute extends Ruteabstract {

    private int data;
    private Rute next;

    public Kolonne myColumn;
    private Rad myRow;
    private Boks myBox;
    private Brett myBoard;

    private boolean fixed = false;

    Rute(int data) {
        this.data = data;
        next = null;
    }
}
