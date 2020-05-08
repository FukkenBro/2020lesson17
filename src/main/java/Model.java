import java.util.ArrayList;

public class Model {
    public String date;
    public String bank;
    public int baseCurrency;
    public String baseCurrencyLit;
    public ArrayList<ExchangeRate> exchangeRate = new ArrayList<ExchangeRate>();

    public ExchangeRate getUSDRate() {
        ExchangeRate result = new ExchangeRate();
        for (int i = 0; i < exchangeRate.size() - 1; i++) {
            ExchangeRate tmp = exchangeRate.get(i);
            if (tmp.currency == null) continue;
            if (tmp.currency.equals("USD")) {
                result = tmp;
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Model{" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", \n\nexchangeRate=" + exchangeRate.toString() +
                '}';
    }
}
