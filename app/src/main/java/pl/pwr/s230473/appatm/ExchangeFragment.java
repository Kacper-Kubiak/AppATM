package pl.pwr.s230473.appatm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class ExchangeFragment extends Fragment {
    private static final String TAG = "ExchangeFragment";

    private final ArrayList<CurrencyExchange> currencyExchangesList;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private Button countButton;
    private TextView textCountValue;
    private EditText textValueFrom;


    @SuppressLint("ValidFragment")
    public ExchangeFragment(ArrayList<CurrencyExchange> currencyExchangesList)
    {
        this.currencyExchangesList = currencyExchangesList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exchange_fragment,container,false);

        spinnerFrom = view.findViewById(R.id.spinnerFrom);
        spinnerTo = view.findViewById(R.id.spinnerTo);
        textValueFrom = view.findViewById(R.id.valueFrom);
        textCountValue = view.findViewById(R.id.calcValue);
        countButton = view.findViewById(R.id.countButton);

        List<String> list = new ArrayList<String>();
        List<String> seclist = new ArrayList<String>();

        for (int i=0; i<currencyExchangesList.size(); i++)
        {
            list.add(currencyExchangesList.get(i).getBase());
            CurrencyExchange currencyExchange = currencyExchangesList.get(i);
            if(i == 0)
            {
                for(int j=0; j<currencyExchange.getCurrencyListCount(); j++)
                {
                    CurrencyCustom currencyCustom = currencyExchange.getCurrencyCustomList().get(j);
                    seclist.add(currencyCustom.getName());
                }
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerFrom.setAdapter(adapter);

        ArrayAdapter<String> secadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, seclist);
        secadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerTo.setAdapter(secadapter);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                String selecteditem =  adapter.getItemAtPosition(i).toString();
                System.out.println("SELECTED: " + selecteditem);
                changeCurrenctExchange(selecteditem);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countValue();
            }
        });

        return view;
    }

    private void countValue() {
        if(textValueFrom.getText().toString().length() > 0) {
            Double valueFrom = Double.valueOf(textValueFrom.getText().toString());
            Double valueTo = 1.0;

            String selectedToItem =  spinnerTo.getSelectedItem().toString();
            String selectedFromItem =  spinnerFrom.getSelectedItem().toString();

            for(int i=0; i<currencyExchangesList.size(); i++)
            {
                if(!selectedFromItem.equals(currencyExchangesList.get(i).getBase())) continue;
                CurrencyExchange currencyExchange = currencyExchangesList.get(i);
                for(int j=0; j<currencyExchange.getCurrencyListCount(); j++)
                {
                    if(!selectedToItem.equals(currencyExchange.getCurrencyCustomList().get(j).getName())) continue;
                    CurrencyCustom currencyCustom = currencyExchange.getCurrencyCustomList().get(j);
                    valueTo = currencyCustom.getMed();
                }
            }
            Double amount = valueFrom * valueTo;
            textCountValue.setText(amount.toString() + " " + selectedToItem);
        }
        else textCountValue.setText("");
    }

    private void changeCurrenctExchange(String selecteditem) {
        textCountValue.setText("");

        List<String> list = new ArrayList<String>();
        for (int i=0; i<currencyExchangesList.size(); i++)
        {
            if(!selecteditem.equals(currencyExchangesList.get(i).getBase())) continue;

            CurrencyExchange currencyExchange = currencyExchangesList.get(i);
            for(int j=0; j<currencyExchange.getCurrencyListCount(); j++)
            {
                CurrencyCustom currencyCustom = currencyExchange.getCurrencyCustomList().get(j);
                list.add(currencyCustom.getName());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerTo.setAdapter(adapter);
    }
}
