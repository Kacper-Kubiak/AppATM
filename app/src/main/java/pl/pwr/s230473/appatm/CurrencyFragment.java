package pl.pwr.s230473.appatm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

public class CurrencyFragment extends ListFragment {
    private static final String TAG = "CurrencyFragment";

    String[] TEST = {"PLN", "EUR", "USD"};

    public String json;

    public CurrencyFragment()
    {
        new JsonTask(this).execute("http://api.nbp.pl/api/exchangerates/rates/c/usd/today/?format=json");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_fragment,container,false);
        ListView listView = view.findViewById(android.R.id.list);
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), TEST);
        listView.setAdapter(customAdapter);
        return view;
    }
}
