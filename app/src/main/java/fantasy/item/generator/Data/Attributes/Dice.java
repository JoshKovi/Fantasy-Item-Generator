package fantasy.item.generator.Data.Attributes;

public enum Dice {
    D1{
        public Dice  up(){return D4;}
        public Dice down(){return D1;}
        public int maxD(){return 1;}
    }, 
    D4{
        public Dice up(){return D6;}
        public Dice down(){return D1;}
        public int maxD(){return 5;}
    }, 
    D6{
        public Dice up(){return D8;}
        public Dice down(){return D4;}
        public int maxD(){return 7;}
    }, 
    D8{
        public Dice up(){return D10;}
        public Dice down(){return D6;}
        public int maxD(){return 9;}
    }, 
    D10{
        public Dice up(){return D12;}
        public Dice down(){return D8;}
        public int maxD(){return 11;}
    }, 
    D12{
        public Dice up(){return D20;}
        public Dice down(){return D10;}
        public int maxD(){return 13;}
    }, 
    D20{
        public Dice up(){return D20;}
        public Dice down(){return D12;}
        public int maxD(){return 21;}
    };
    private static final int LENGTH = 7;
    abstract public Dice up();
    abstract public Dice down();
    abstract public int maxD();
    public int getLength() {return LENGTH;}
}
