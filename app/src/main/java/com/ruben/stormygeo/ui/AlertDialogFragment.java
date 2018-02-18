package com.ruben.stormygeo.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import com.ruben.stormygeo.R;

/**
 * Created by Luis Antonio Miranda on 31/01/2018.
 */

public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.error_title);
        builder.setMessage(R.string.error_message);
        builder.setPositiveButton(context.getString(R.string.error_ok), null);

        AlertDialog dialog = builder.create();

        return dialog;
    }
}
