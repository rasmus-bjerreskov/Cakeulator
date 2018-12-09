package com.example.werefrogs.cakeulator;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class DeleteDialogFragment extends DialogFragment {
    public Dialog onCreateDialog (Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.string_deleteQuery)
                .setPositiveButton(R.string.string_delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton(R.string.string_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
