package pl.pwr.s230473.appatm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class ExchangeFragment extends Fragment {
    private static final String TAG = "ExchangeFragment";

    private final ArrayList<CurrencyExchange> currencyExchangesList;

    @SuppressLint("ValidFragment")
    public ExchangeFragment(ArrayList<CurrencyExchange> currencyExchangesList)
    {
        this.currencyExchangesList = currencyExchangesList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exchange_fragment,container,false);

        Spinner spinnerFrom = view.findViewById(R.id.spinnerFrom);
        Spinner spinnerTo = view.findViewById(R.id.spinnerTo);
        EditText textValueFrom = view.findViewById(R.id.valueFrom);
        EditText textValueTo = view.findViewById(R.id.valueTo);

        List<String> list = new ArrayList<String>();
        for (int i=0; i<currencyExchangesList.size(); i++)
        {
            list.add(currencyExchangesList.get(i).getBase());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerFrom.setAdapter(adapter);

        return view;
    }
}
