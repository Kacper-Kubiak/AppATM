package pl.pwr.s230473.appatm;

import java.util.ArrayList;

public class CurrencyExchange {

    private String name; //"Polski Złoty"
    private String shortCode; // "PL"
    private String code; // "PLN"

    ArrayList<CurrencyCustom> currencyCustomList = new ArrayList<CurrencyCustom>(); // Name, Bid, Ask

    public CurrencyExchange(String name, String shortCode, String code,ArrayList<CurrencyCustom> currencyCustomList)
    {
        this.currencyCustomList = currencyCustomList;
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

    public ArrayList<CurrencyCustom> getCurrencyCustomList() { return currencyCustomList; }
    public void setCurrencyCustomList(ArrayList<CurrencyCustom> currencyCustomList) { this.currencyCustomList = currencyCustomList; }

    public void addCurrent(CurrencyCustom currencyCustom)
    {
        currencyCustomList.add(currencyCustom);
    }

    public int getCurrencyListCount()
    {
        return currencyCustomList.size();
    }

}
