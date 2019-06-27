package pl.pwr.s230473.appatm;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.content.Context;

public class CustomAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final Activity activity;
    private final String[] TEST;

    public CustomAdapter(Activity activity, String[] TEST)
    {
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.TEST = TEST;
    }

    @Override
    public int getCount() {
        return TEST.length;
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

        int flagOffset = 0x1F1E6;
        int asciiOffset = 0x41;

        String country_from = "US";
        String country_to = "PL";

        int firstChar_from = Character.codePointAt(country_from, 0) - asciiOffset + flagOffset;
        int secondChar_from = Character.codePointAt(country_from, 1) - asciiOffset + flagOffset;

        int firstChar_to = Character.codePointAt(country_to, 0) - asciiOffset + flagOffset;
        int secondChar_to = Character.codePointAt(country_to, 1) - asciiOffset + flagOffset;

        String flag_from = new String(Character.toChars(firstChar_from))
                + new String(Character.toChars(secondChar_from));
        String flag_to = new String(Character.toChars(firstChar_to))
                + new String(Character.toChars(secondChar_to));

        countryFrom.setText(flag_from);
        countryTo.setText(getFlat("DE"));
        currencyTextView.setText(TEST[position]);
        currencyValue.setText(TEST[position]);
        currencyBuy.setText(TEST[position]);
        currencySell.setText(TEST[position]);

        return vi;
    }

    public String getFlat(String code)
    {
        if (code.length() != 2) {
            return "";
        }
        String countryCodeCaps = code.toUpperCase();
        int firstLetter = Character.codePointAt(countryCodeCaps, 0) - 0x41 + 0x1F1E6;
        int secondLetter = Character.codePointAt(countryCodeCaps, 1) - 0x41 + 0x1F1E6;
        return new String(Character.toChars(firstLetter)) + new String(Character.toChars(secondLetter));
    }
}
