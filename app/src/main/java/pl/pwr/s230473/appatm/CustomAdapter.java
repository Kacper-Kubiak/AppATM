package pl.pwr.s230473.appatm;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CustomAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final Activity activity;
    private final ArrayList<CurrencyCustom> currencyCustomList;
    private final String base;

    public CustomAdapter(Activity activity, ArrayList<CurrencyCustom> currencyCustomList, String base)
    {
        this.activity = activity;
        this.currencyCustomList = currencyCustomList;
        this.base = base;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return currencyCustomList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;

        if(convertView == null)
            vi = inflater.inflate(R.layout.customlist, null);

        TextView countryFrom = vi.findViewById(R.id.countryFrom);
        TextView countryTo = vi.findViewById(R.id.countryTo);
        TextView currencyTextView = vi.findViewById(R.id.currencyTextView);
        TextView currencyValue = vi.findViewById(R.id.currencyValue);
        TextView currencyBuy = vi.findViewById(R.id.currencyBuy);
        TextView currencySell = vi.findViewById(R.id.currencySell);

        CurrencyCustom currencyCustom = currencyCustomList.get(position);

        /*Map<String, Locale> localeMap;
        String[] countries = Locale.getISOCountries();
        localeMap = new HashMap<String, Locale>(countries.length);
        for (String country : countries) {
            Locale locale = new Locale("", country);
            localeMap.put(locale.getISO3Country().toUpperCase(), locale);
        }*/
        countryFrom.setText(Utils.getCurrencySymbol(base));
        countryTo.setText(Utils.getCurrencySymbol(currencyCustom.getName()));
        currencyTextView.setText(currencyCustom.getName());
        currencyValue.setText("1 " + currencyCustom.getName());
        String bidText = String.valueOf(currencyCustom.getBid());
        String askText = String.valueOf(currencyCustom.getAsk());
        currencyBuy.setText(bidText);
        currencySell.setText(askText);

        return vi;
    }

    static class Utils {
        public static SortedMap<Currency, Locale> currencyLocaleMap;

        static {
            currencyLocaleMap = new TreeMap<Currency, Locale>(new Comparator<Currency>() {
                public int compare(Currency c1, Currency c2) {
                    return c1.getCurrencyCode().compareTo(c2.getCurrencyCode());
                }
            });
            for (Locale locale : Locale.getAvailableLocales()) {
                try {
                    Currency currency = Currency.getInstance(locale);
                    currencyLocaleMap.put(currency, locale);
                } catch (Exception e) {
                }
            }
        }


        public static String getCurrencySymbol(String currencyCode) {
            Currency currency = Currency.getInstance(currencyCode);
            System.out.println(currencyCode + ":-" + currency.getSymbol(currencyLocaleMap.get(currency)));
            return currency.getSymbol(currencyLocaleMap.get(currency));
        }

    }
}
