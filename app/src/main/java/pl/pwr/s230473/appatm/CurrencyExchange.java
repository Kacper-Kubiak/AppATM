package pl.pwr.s230473.appatm;

public class CurrencyExchange {
    private  Currency fromCurrency;
    private  Currency toCurrency;

    public CurrencyExchange(Currency fromCurrency, Currency toCurrency)
    {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public Currency getFromCurrency(){return fromCurrency;}
    public Currency getToCurrency(){return toCurrency;}
}
