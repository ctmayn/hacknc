package hacknc.com.poolit;

import com.reimaginebanking.api.nessieandroidsdk.*;
import com.reimaginebanking.api.nessieandroidsdk.models.Account;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

/**
 * Created by Christopher on 10/29/2016.
 */

public class Transfer {
    int value;
    User sender;
    User recipient;
    private static final String KEY = "d1cab498a02db5a8370dd4fe90e52321";

    public static void transfer(User sender, User recipient, final int value){
        NessieClient client = NessieClient.getInstance(KEY);
        client.ACCOUNT.getAccount(sender.getAccountID(), new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                Account senderAcc = (Account) result;
                NessieClient client = NessieClient.getInstance(KEY);
                Account newAcc = new Account.Builder()
                        .balance(senderAcc.getBalance() - value)
                        .build();
                client.ACCOUNT.updateAccount(senderAcc.getId(), newAcc, new NessieResultsListener() {
                    @Override
                    public void onSuccess(Object result) {

                    }

                    @Override
                    public void onFailure(NessieError error) {

                    }
                });
            }
            @Override
            public void onFailure(NessieError error) {
                throw new IllegalArgumentException("Account ID did not match any in the system!");
            }
        });
        client.ACCOUNT.getAccount(recipient.getAccountID(), new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                Account recipientAcc = (Account) result;
                NessieClient client = NessieClient.getInstance(KEY);
                Account newAcc = new Account.Builder()
                        .balance(recipientAcc.getBalance() + value)
                        .build();
                client.ACCOUNT.updateAccount(recipientAcc.getId(), newAcc, new NessieResultsListener() {
                    @Override
                    public void onSuccess(Object result) {

                    }

                    @Override
                    public void onFailure(NessieError error) {

                    }
                });
            }
            @Override
            public void onFailure(NessieError error) {
                throw new IllegalArgumentException("Account ID did not match any in the system!");
            }
        });
    }
}
