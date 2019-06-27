package pl.pwr.s230473.appatm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ExchangeFragment extends Fragment {
    private static final String TAG = "ExchangeFragment";

    private ArrayList<Currency> currencyList = new ArrayList<Currency>();

    @SuppressLint("ValidFragment")
    public ExchangeFragment(ArrayList<Currency> currencyList)
    {
        this.currencyList = currencyList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exchange_fragment,container,false);

        Spinner spinnerFrom = view.findViewById(R.id.spinnerFrom);
        Spinner spinnerTo = view.findViewById(R.id.spinnerTo);
        EditText textValueFrom = view.findViewById(R.id.valueFrom);
        EditText textValueTo = view.findViewById(R.id.valueTo);

        //Currency

        return view;
    }
}
