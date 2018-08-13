package com.phuocthang.vnufoody;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.phuocthang.database.DBHelper;
import com.phuocthang.model.Place;
import com.phuocthang.model.Rating;

/**
 * Created by THANH_UIT on 4/25/2017.
 */

public class DetailPlaceFragmentDialog extends DialogFragment {
    private ImageButton btnDelete;
    private ImageView imgAvatar;
    private TextView tvPlaceName,tvAddress;
    private RatingBar ratingNgon,ratingRe;
    private Button btnCancel,btnPerform;
/*    private DetailPlaceFragmentListener mListener;*/
    private DBHelper db;
    private Place place;

/*    public interface DetailPlaceFragmentListener{
        void OnButtonPerformClickListener();
        void OnButtonCancelClickListener();
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View dialog=inflater.inflate(R.layout.fragment_detail_place,container,false);
        getDialog().setTitle("Thông tin chi tiết");
        Long id=Long.parseLong(getArguments().getString("id_place"));
        Log.d("Detail","Id=="+id);
        db=new DBHelper(getActivity());
        place=db.getDataPlace(id);
        Log.d("Detail",place.getId()+"-"+place.getPlaceName());
        setWidgets(dialog);
        setEvents();
        return dialog;
    }

    private void setEvents() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=getFragmentManager().findFragmentByTag("fragment_detail_Place");
                if(fragment!=null){
                    getFragmentManager().beginTransaction().remove(fragment).commit();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=getFragmentManager().findFragmentByTag("fragment_detail_Place");
                if(fragment!=null){
                    getFragmentManager().beginTransaction().remove(fragment).commit();
                }
            }
        });

        btnPerform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save data
                db.insertRating(new Rating(place.getId(),Math.round(ratingNgon.getRating()),Math.round(ratingRe.getRating())));
                Toast.makeText(getActivity(),place.getPlaceName()+" Ngon: "+ratingNgon.getRating()+" - "+" Re: "+ratingRe.getRating(),Toast.LENGTH_LONG).show();

                Fragment fragment=getFragmentManager().findFragmentByTag("fragment_detail_Place");
                if(fragment!=null){
                    getFragmentManager().beginTransaction().remove(fragment).commit();
                }
            }
        });
    }

    private void setWidgets(View dialog) {
        btnDelete=(ImageButton) dialog.findViewById(R.id.btn_delete);
        imgAvatar=(ImageView)dialog.findViewById(R.id.imgAvatar);
        tvPlaceName=(TextView)dialog.findViewById(R.id.tvPlaceName);
        tvAddress=(TextView)dialog.findViewById(R.id.tvAddress) ;
        ratingNgon=(RatingBar)dialog.findViewById(R.id.ratingNgon);
        ratingRe=(RatingBar) dialog.findViewById(R.id.ratingRe);
        btnCancel=(Button)dialog.findViewById(R.id.btnCancel);
        btnPerform=(Button)dialog.findViewById(R.id.btnPerform);

        // set data
        if(!place.getImage_Url().equals("null")) {
            Drawable img = getActivity().getResources().getDrawable(getResources().getIdentifier(place.getImage_Url(), "drawable", getActivity().getPackageName()));
        }

        tvPlaceName.setText(place.getPlaceName());
        tvAddress.setText(place.getAddress());

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        try{
            //mListener=(DetailPlaceFragmentListener)activity;
        }catch (ClassCastException ex){
        }
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        super.onResume();
    }
}
