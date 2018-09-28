package com.mobileinformationsystems.alienreporter.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mobileinformationsystems.alienreporter.R;

/**
 * Created by dani on 12/09/2017.
 */

public class FormDialog
    extends DialogFragment
{
    protected FrameLayout dialog;
    private TextView id;
    private TextView caption;
    private TextView type;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
         ViewGroup container, Bundle savedInstanceState)
    {
        dialog = (FrameLayout) inflater.inflate(
            R.layout.report_form_dialog,
            container, false);

        id = dialog.findViewById(R.id.report_form_id_text);
        caption = dialog.findViewById(R.id.report_form_caption_text);
        type = dialog.findViewById(R.id.report_form_type_text);

        return dialog;
    }

    @Override @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        try {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        } catch (NullPointerException exception) {
            exception.getMessage();
        }

        return dialog;
    }
}