package com.nimitt.imagepicker;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Imagepick extends Fragment {
    private int PICK_IMAGES;
    int pos=0;
    int p=-1;
    Button picker;
    GridView gridView;
    ArrayList<Uri> mArrayUri;
    Uri mImageUri;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.destination_details,container,false);
        Toast.makeText(getContext(), getArguments().getString("DocID"), Toast.LENGTH_SHORT).show();
        mArrayUri=new ArrayList<Uri>();
        picker = v.findViewById(R.id.button);
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("size",""+mArrayUri.size());
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGES);
            }
        });
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data ) {
        if(requestCode==PICK_IMAGES){
            if(resultCode==RESULT_OK ){
                gridView = (GridView) getActivity().findViewById(R.id.grid);
                if(data.getData()!=null){

                    mImageUri=data.getData();

                }else{
                    if(data.getClipData()!=null){
                        ClipData mClipData=data.getClipData();
                        for(int i=0;i<mClipData.getItemCount();i++){

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            Log.d("URI","Uri"+uri);
                            mArrayUri.add(uri);
                        }
                    }
                }
                gridView.setAdapter(new ImageGridAdapter(getContext(),mArrayUri));
                gridView.invalidateViews();
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getContext(), "You clicked " + position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGES);
                    }
                });
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}