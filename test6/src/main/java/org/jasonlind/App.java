package org.jasonlind;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        enumTest();
        //System.out.println(PaymentMethodRefs.PAYPAL_WITHDRAWAL_METHOD_REF.name());


    }

    static void enumTest(){

        String paymentMethodRef = "PAYPAL-API";

        if(paymentMethodRef.equals(PaymentMethodRefs.PAYPAL_WITHDRAWAL_METHOD_REF.getRef()))

            System.out.println("EQUALS");

        else
            throw new IllegalArgumentException("Unrecognized Braintree payment method reference: " + paymentMethodRef);

    }

}



enum PaymentMethodRefs
{
    PAYPAL_WITHDRAWAL_METHOD_REF("PAYPAL-API"),
    BRAINTREE_PAYPAL_METHOD_REF("BRAINTREE-PAYPAL"),
    REALEX_METHOD_REF("REALEX"),
    REALEX_APPLE_PAY("REALEX-APPLE-PAY"),
    REALEX_MANUAL_METHOD_REF("REALEX_MANUAL"),
    PAYSTACK_METHOD_REF("PAYSTACK"),
    ECO_PAYZ("ECOPAYZ-API"),
    ECO_PAYZ_VOUCHER("ECOPAYZ-API-VOUCHER"),
    NETELLER_METHOD_REF("NETELLER"),
    SECURE_TRADING_METHOD_REF("SECURE-TRADING"),
    ST_MANUAL_METHOD_REF("ST-MANUAL"),
    GT_COLLECTION("GT-COLLECTION-API"),
    TRUSTLY_METHOD_REF("TRUSTLY"),
    INTERAC_METHOD_REF("INTERAC"),
    BANK_ACCOUNT_METHOD_REF("BANK ACCOUNT"),
    CASH_PAYMENT_METHOD_REF("CASH");

    private final String ref;

    PaymentMethodRefs(String ref)
    {
        this.ref=ref;
    }

    public final String getRef()
    {
        return ref;
    }

/*
    @Override
    public final String toString(){
        return this.ref;
    }
*/

}



