public class ExchangeRate {
    public String baseCurrency;
    public String currency;
    public float saleRateNB;
    public float purchaseRateNB;

    @Override
    public String toString() {
        return "Exchange Rate :" +
                "\"" + baseCurrency + "\" " +
                "to \"" + currency + '\"' +
                ", Sale = " + saleRateNB +
                ", Purchase = " + purchaseRateNB;
    }
}
