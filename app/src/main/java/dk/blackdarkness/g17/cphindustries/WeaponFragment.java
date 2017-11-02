package dk.blackdarkness.g17.cphindustries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Thoma on 11/02/2017.
 */

public class WeaponFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "WeaponFragment";
    private Button edit;
    private Fragment weaponEditFragment;
    private TextView dummy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weapon_layout, container, false);
        edit = view.findViewById(R.id.editWeapon);
        dummy = view.findViewById(R.id.dummyText);
        initDisplay();
        return view;
    }

    public void initDisplay() {
        getActivity().setTitle("Weapon #1");
        edit.setOnClickListener(this);
        edit.setText("Edit weapon");
        dummy.setText("Info about weapon here");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editWeapon:
                Log.d(TAG, "onClick: Trying to open edit weapon fragment.");
                weaponEditFragment = new WeaponEditFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, weaponEditFragment).addToBackStack(null).commit();
        }
    }

}