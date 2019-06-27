package pl.pwr.s230473.appatm;

import java.util.ArrayList;

public class CurrencyExchange {

    private String name; //"Polski Złoty"
    private String shortCode; // "PL"
    private String code; // "PLN"

    ArrayList<Currency> currencyList = new ArrayList<Currency>(); // Name, Bid, Ask

    public CurrencyExchange(String name, String shortCode, String code,ArrayList<Currency> currencyList)
    {
        this.currencyList = currencyList;
        new CurrencyExchange(name, shortCode, code);
    }

    public CurrencyExchange(String name, String shortCode, String code)
    {
        this.name = name;
        this.shortCode = shortCode;
        this.code = code;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getShortCode() { return shortCode; }
    public void setShortCode(String shortCode) { this.shortCode = shortCode; }

    public String getBase() { return getCode(); }
    public String getCode() { return code; }
    public void setCode(String code) { this.code=code; }

    public ArrayList<Currency> getCurrencyList() { return currencyList; }
    public void setCurrencyList(ArrayList<Currency> currencyList) { this.currencyList=currencyList; }

    public void addCurrent(Currency currency)
    {
        currencyList.add(currency);
    }

    public int getCurrencyListCount()
    {
        return currencyList.size();
    }

}
