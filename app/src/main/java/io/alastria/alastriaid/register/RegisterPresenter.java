package io.alastria.alastriaid.register;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import io.alastria.alastriaid.R;
import io.alastria.alastriaid.home.HomeActivity;
import io.alastria.alastriaid.login.LoginActivity;
import io.alastria.alastriaid.model.Model;
import io.alastria.alastriaid.model.Person;
import io.alastria.alastriaid.util.Utils;

/**
 * Created by Alvaro Suarez on 05/10/2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View mRegisterView;


    public RegisterPresenter(RegisterContract.View registerView) {
        mRegisterView = registerView;
    }

    @Override
    public void registerNewUser(String firstName, String lastName, String email, String phoneNumber, String dni) {
        //TODO: implement register
        if(validateForm( firstName, lastName, email, phoneNumber, dni)) {

            //for testing
            Model _model = Model.getInstance();

            Person _person = _model.getPerson();
            _person.setEmail(email);
            _person.setFirstName(firstName);
            _person.setLastName(lastName);
            _person.setPhoneNumber(phoneNumber);
            _person.setDNI(dni);

            //show toast
            final Activity _activity = (Activity) mRegisterView;
            Utils.ShowToast("Nuevo usuario creado", _activity);

            //go to home page
            final Intent _intent = new Intent(_activity, HomeActivity.class);
            _activity.startActivity(_intent);
            _activity.finish();
        }
    }

    public boolean validateForm(String firstName, String lastName, String email, String phoneNumber, String dni){
        final Activity _activity = (Activity)mRegisterView;

        String _error = "";
        if(email==null || email.isEmpty())
            _error = _activity.getString(R.string.registerErrorEmail);
        else if(lastName==null || lastName.isEmpty())
            _error = _activity.getString(R.string.registerErrorFirstName);
        else if(email==null || email.isEmpty())
            _error = _activity.getString(R.string.registerErrorLastName);
        else if(phoneNumber==null || phoneNumber.isEmpty())
            _error = _activity.getString(R.string.registerErrorPhone);
        else if(dni==null || dni.isEmpty())
            _error = _activity.getString(R.string.registerErrorDni);

        if(!_error.isEmpty()) {
            Utils.ShowErrorToast(_error, _activity);
            return false;
        }
        else
            return true;
    }
    @Override
    public void start() {
    }
}
