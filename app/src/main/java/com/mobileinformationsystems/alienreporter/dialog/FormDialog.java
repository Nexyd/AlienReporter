package com.mobileinformationsystems.alienreporter.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mobileinformationsystems.alienreporter.R;
import com.mobileinformationsystems.alienreporter.beans.AlienReportForm;

import java.util.ArrayList;
import java.util.List;

public class FormDialog
    extends DialogFragment
{
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
         ViewGroup container, Bundle savedInstanceState)
    {
        ConstraintLayout dialog = (ConstraintLayout) inflater.inflate(
            R.layout.report_form_dialog, container, false);

        // getDialog().setTitle(R.string.reportFormTitle);
        List<AlienReportForm> reports;
        Bundle args = getArguments();
        String searchId = "";

        if ((args != null) && (args.getSerializable("reports") != null))
            reports = (List<AlienReportForm>) args.getSerializable("reports");
        else
            reports = new ArrayList<>();

        if ((args != null) && (args.getCharSequence("searchId") != null))
            searchId = args.getCharSequence("searchId").toString();

        TextView id = dialog.findViewById(R.id.report_form_id_text);
        TextView caption = dialog.findViewById(R.id.report_form_caption_text);
        TextView type = dialog.findViewById(R.id.report_form_type_text);

        int index = 0;
        for (int i = 0; i < reports.size(); i++) {
            if (reports.get(i).getId().equals(searchId)) {
                index = i;
                break;
            }
        }

        id.setText(reports.get(index).getId());
        caption.setText(reports.get(index).getCaption());
        type.setText(reports.get(index).getType());

        return dialog;
    }

//    @Override @NonNull
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Dialog dialog = super.onCreateDialog(savedInstanceState);
//
//        try {
//            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        } catch (NullPointerException exception) {
//            exception.getMessage();
//        }
//
//        return dialog;
//    }
}