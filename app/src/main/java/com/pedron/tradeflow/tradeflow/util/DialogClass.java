package com.pedron.tradeflow.tradeflow.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.pedron.tradeflow.tradeflow.R;


/**
 * Created by Exchange on 9/3/2015.
 */
public class DialogClass {

    private Context context;

    /**
     * CONSTRUCTOR
     *
     * @param context
     */
    public DialogClass(Context context){
        this.context = context;
    }

    Button exitButon;

    /**
     * Show custom dialog
     */
    public void showCustomDialog() {
        // Create custom dialog object
        final Dialog dialog = new Dialog(context);
        // hide to default title for Dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // inflate the layout dialog_layout.xml and set it as contentView
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popup_layout, null, false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);

        exitButon = (Button)view.findViewById(R.id.exit_button);
        exitButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(0);
            }
        });
        // Display the dialog
        dialog.show();
    }

}
