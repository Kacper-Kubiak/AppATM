package pl.pwr.s230473.appatm;

public class CurrencyCustom {
    private String name;
    private double bid;
    private double ask;
    private double med;

    public CurrencyCustom(String name, double bid, double ask)
    {
        this.name = name;
        this.bid = bid;
        this.ask = ask;
        this.med = (bid+ask)/2;
    }

    public String getName() {return name;}
    public double getBid() {return bid;}
    public double getAsk() {return ask;}

    public double getMed() { return med; }
}
