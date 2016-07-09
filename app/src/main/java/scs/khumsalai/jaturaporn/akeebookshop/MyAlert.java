package scs.khumsalai.jaturaporn.akeebookshop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Nicotinman on 7/9/2016.
 */
public class MyAlert {

    //ประกาศ
    public void myDialog(Context context,
                         String strTitle,
                         String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);// Create constant or Object From Class
        builder.setCancelable(false);// ไม่สามารถกด Undo ได้
        //สร้างไอคอน ของตัวน้
        builder.setIcon(R.drawable.nobita48);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();//  ทำให้มันหายไป

            }
        });
        builder.show();

    }

}   // Main Class
