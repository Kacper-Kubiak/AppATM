package pl.pwr.s230473.appatm;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/*
    * Icons made by Freepik from www.flaticon.com is licensed by CC 3.0 BY
 */
public class MainActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    ArrayList<CurrencyExchange> currencyExchangesList = new ArrayList<CurrencyExchange>();

    private final String[] currencyBaseTab = {"PLN","USD","EUR","GBP","CHF"};

    private int[] tabIcons = {
            R.drawable.ic_action_dollar_symbol,
            R.drawable.ic_action_dollar_sign_and_piles_of_coins,
            R.drawable.ic_action_dollar_symbol
    };

    private String[] tabName = {
            "Wymiana",
            "Kursy",
            "TEST"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        setupTabIcons();

        loadCurrency();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ExchangeFragment(currencyExchangesList), tabName[0]);
        adapter.addFragment(new CurrencyFragment(currencyExchangesList), tabName[1]);
        adapter.addFragment(new Tab3Fragment(), tabName[2]);
        viewPager.setAdapter(adapter);
    }

    private void loadCurrency() {
        for (int i = 0; i < currencyBaseTab.length; i++)
        {
            System.out.println(currencyBaseTab[i]);
            String url = "https://api.exchangeratesapi.io/latest?base="+currencyBaseTab[i];
            try {
                JSONObject json = new JsonTask(url).execute().get();
                parseJSON(json);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseJSON(JSONObject reader)
    {
        try {
            String base = reader.getString("base");
            CurrencyExchange currencyExchange = new CurrencyExchange("dev","dev", base);
            JSONObject rates = reader.getJSONObject("rates");
            for (int i = 0; i < currencyBaseTab.length; i++)
            {
                if(base.equals(currencyBaseTab[i])) continue;
                Double bid = rates.getDouble(currencyBaseTab[i]);
                Double ask = bid;
                //Aktualne API nie zwraca wartości kupna sprzedaży tylko wartość średnią
                /*JSONObject currentJSON  = rates.getJSONObject(currencyBaseTab[i]);
                Double bid = currentJSON.getDouble("bid");
                Double ask = currentJSON.getDouble("ask");*/
                CurrencyCustom currencyCustom = new CurrencyCustom(currencyBaseTab[i], bid, ask);
                currencyExchange.addCurrent(currencyCustom);
            }
            currencyExchangesList.add(currencyExchange);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class JsonTask extends AsyncTask<String, String, JSONObject> {

        private URL url;
        public JsonTask(String url)
        {
            try {
                this.url = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            String jsonString = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */ );
                urlConnection.setConnectTimeout(15000 /* milliseconds */ );
                urlConnection.setDoOutput(true);
                urlConnection.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                jsonString = sb.toString();
                System.out.println("JSON: " + jsonString);
                return new JSONObject(jsonString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            return null;
        }
    }
}
