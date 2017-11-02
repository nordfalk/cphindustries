package dk.blackdarkness.g17.cphindustries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Thoma on 11/02/2017.
 */

public class WeaponViewFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "WeaponViewFragment";
    private Button test;
    private FloatingActionButton add, lock;
    private Fragment weaponFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weapon_view_layout, container, false);
        test = view.findViewById(R.id.test3);
        lock = view.findViewById(R.id.lockWeaponView);
        add = view.findViewById(R.id.createWeapon);
        initDisplay();
        return view;
    }

    public void initDisplay() {
        getActivity().setTitle("Weapons");
        test.setOnClickListener(this);
        test.setText("Go to specific weapon");
        lock.setOnClickListener(this);
        add.setOnClickListener(this);
        checkInitLock();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.test3:
                Log.d(TAG, "onClick: Trying to open weapon fragment.");
                weaponFragment = new WeaponFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, weaponFragment).addToBackStack(null).commit();
                break;
            case R.id.createWeapon:
                Log.d(TAG, "onClick: CreateWeapon has been clicked.");
                break;
            case R.id.lockWeaponView:
                checkLock();
                break;
        }
    }

    public void checkLock() {
        if (SceneViewActivity.locked) {
            //Setbackgroundcolor virker ikke - skal også have setImageResource(R.drawable), så låsen skifter til åben
            lock.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
            add.setVisibility(View.VISIBLE);
            SceneViewActivity.locked = false;
        } else {
            //Samme her
            lock.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimarySmoke));
            add.setVisibility(View.GONE);
            SceneViewActivity.locked = true;
        }

    }

    //Tjek om låsen er slået til ved fragment opstart
    //Hvis den ikke er slået til, lås op - ellers gør intet (låst pr. default)
    public void checkInitLock() {
        if(!SceneViewActivity.locked) {
            lock.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
            add.setVisibility(View.VISIBLE);
            SceneViewActivity.locked = false;
        }
    }

}
