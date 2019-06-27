package pl.pwr.s230473.appatm;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ListFragment;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyFragment extends ListFragment {
    private static final String TAG = "CurrencyFragment";

    String[] TEST = {"EUR", "USD"};
    ArrayList<Currency> currencyList = new ArrayList<Currency>();

    public CurrencyFragment()
    {
        loadedCurrency();
        /*try {
            JSONObject JSON = new JsonTask(this).execute("http://localhost/currency.php").get();
            int i = 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 0;*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_fragment,container,false);
        ListView listView = view.findViewById(android.R.id.list);
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), currencyList);
        listView.setAdapter(customAdapter);
        return view;
    }
    public void loadedCurrency()
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
            Currency eur = new Currency("EUR", bid, ask);
            currencyList.add(eur);
            JSONObject USD  = rates.getJSONObject("USD");
            bid = USD.getDouble("bid");
            ask = USD.getDouble("ask");
            Currency usd = new Currency("USD", bid, ask);
            currencyList.add(usd);

            JSONObject PLN  = rates.getJSONObject("PLN");
            bid = PLN.getDouble("bid");
            ask = PLN.getDouble("ask");
            Currency pln = new Currency("PLN", bid, ask);
            currencyList.add(pln);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
