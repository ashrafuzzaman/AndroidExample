package personal.jitu.android.test;

import java.io.IOException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class AuthActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		try {
			Toast.makeText(this, getToken(), Toast.LENGTH_LONG).show();
		} catch (OperationCanceledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthenticatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getToken() throws OperationCanceledException, AuthenticatorException, IOException {
		AccountManager accountMng = AccountManager.get(this);
		Account[] accounts = accountMng.getAccountsByType("com.google");
		Account account = accounts[0];
		AccountManagerFuture<Bundle> accountManagerFuture = accountMng.getAuthToken(account, "android", null, this, null, null);
		Bundle authTokenBundle = accountManagerFuture.getResult();
		String authToken = authTokenBundle.get(AccountManager.KEY_AUTHTOKEN).toString();
		return authToken;
	}

}