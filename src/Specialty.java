public enum Specialty {
    private final int charge;

    Specialty(int charge){
        this.charge = charge;
    }

    public int getChange(){
        return this.charge;
    }
}
