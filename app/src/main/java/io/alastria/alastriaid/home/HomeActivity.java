package io.alastria.alastriaid.home;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import io.alastria.alastriaid.R;
import io.alastria.alastriaid.login.LoginActivity;
import io.alastria.alastriaid.model.Model;
import io.alastria.alastriaid.model.Service;
import io.alastria.alastriaid.profile.ProfileActivity;
import io.alastria.alastriaid.util.Utils;

/**
 * Created by Alvaro Suarez on 05/10/2017.
 */

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private HomeContract.Presenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomePresenter = new HomePresenter(this);
        mHomePresenter.start();

        final Activity _activity = this;

        final FloatingActionButton _btnModifyProfile = (FloatingActionButton)findViewById(R.id.btnModifyProfile);
        _btnModifyProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //go to modify profile page
                final Intent _intent = new Intent(_activity, ProfileActivity.class);
                startActivity(_intent);
                finish();
            }
        });
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mHomePresenter = presenter;
    }

    @Override
    public void displayProfile(String firstName, String lastName, String email, String phoneNumber, String dni){
        ((TextView) findViewById(R.id.nameUser)).setText(firstName + " " + lastName);
        ((TextView) findViewById(R.id.emailUser)).setText(email);
        ((TextView) findViewById(R.id.phoneNumberUser)).setText(phoneNumber);
        ((TextView) findViewById(R.id.dniUser)).setText(dni);
    }

    @Override
    public void displayServices(ArrayList<Service> services){
        FragmentManager _fm = getSupportFragmentManager();
        Fragment _fragment = _fm.findFragmentById(R.id.fragmentContainer);

        if (_fragment == null) {
            _fragment = new HorizontalServiceListViewFragment();
            ((HorizontalServiceListViewFragment)_fragment).setListServices(services);

            _fm.beginTransaction()
                    .add(R.id.fragmentContainer, _fragment)
                    .commit();
        }
    }

}
