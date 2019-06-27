package pl.pwr.s230473.appatm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class CurrencyFragment extends ListFragment {
    private static final String TAG = "CurrencyFragment";

    private String base = "PLN";
    private ArrayList<CurrencyCustom> currencyCustomList = new ArrayList<CurrencyCustom>();
    private ArrayList<CurrencyExchange> currencyExchangesList = new ArrayList<CurrencyExchange>();

    @SuppressLint("ValidFragment")
    public CurrencyFragment(ArrayList<CurrencyExchange> currencyExchangesList)
    {
        this.currencyExchangesList = currencyExchangesList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_fragment,container,false);
        ListView listView = view.findViewById(android.R.id.list);
        loadedCurrency();
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), currencyCustomList, base);
        listView.setAdapter(customAdapter);
        return view;
    }

    private void loadedCurrency()
    {
        for(int i=0; i<currencyExchangesList.size(); i++)
        {
            if(!base.equals(currencyExchangesList.get(i).getBase())) continue;
            CurrencyExchange currencyExchange = currencyExchangesList.get(i);
            for(int j=0; j<currencyExchange.getCurrencyListCount(); j++)
            {
                CurrencyCustom currencyCustom = currencyExchange.getCurrencyCustomList().get(j);
                currencyCustomList.add(currencyCustom);
            }
        }
    }

    /*public void loadedCurrency()
    {
        //{"base":"PLN","rates":{"EUR":{"bid":4.3159,"ask":4.3909},"USD":{"bid":3.7159,"ask":3.7909},"PLN":{"bid":1,"ask":1}}}
        String json = "{\"base\":\"PLN\",\"rates\":{\"EUR\":{\"bid\":4.3159,\"ask\":4.3909},\"USD\":{\"bid\":3.7159,\"ask\":3.7909},\"PLN\":{\"bid\":1,\"ask\":1}}}";

        try {
            JSONObject reader = new JSONObject(json);
            String base = reader.getString("base");
            JSONObject rates  = reader.getJSONObject("rates");
            JSONObject EUR  = rates.getJSONObject("EUR");
            Double bid = EUR.getDouble("bid");
            Double ask = EUR.getDouble("ask");
            CurrencyCustom eur = new CurrencyCustom("EUR", bid, ask);
            currencyCustomList.add(eur);
            JSONObject USD  = rates.getJSONObject("USD");
            bid = USD.getDouble("bid");
            ask = USD.getDouble("ask");
            CurrencyCustom usd = new CurrencyCustom("USD", bid, ask);
            currencyCustomList.add(usd);

            JSONObject PLN  = rates.getJSONObject("PLN");
            bid = PLN.getDouble("bid");
            ask = PLN.getDouble("ask");
            CurrencyCustom pln = new CurrencyCustom("PLN", bid, ask);
            currencyCustomList.add(pln);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}
