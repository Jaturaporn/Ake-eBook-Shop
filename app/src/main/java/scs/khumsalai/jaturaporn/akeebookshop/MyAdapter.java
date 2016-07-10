package scs.khumsalai.jaturaporn.akeebookshop;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Nicotinman on 7/10/2016.
 */
public class MyAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private String[] bookStrings,priceStrings, iconStrings;     // เป็น Array

    // ทำ Setter ที่เป็น Constructure


    public MyAdapter(Context context,
                     String[] bookStrings,
                     String[] priceStrings,
                     String[] iconStrings) {
        this.context = context;
        this.bookStrings = bookStrings;
        this.priceStrings = priceStrings;
        this.iconStrings = iconStrings;
    }

    @Override
    public int getCount() {
        return bookStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // สร้าง layout เสมือน ต้องเปิด Service LayoutInflater
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ทำการสร้าง Instant เพื่อนำพาข้อมูล
        View view = layoutInflater.inflate(R.layout.my_listview, parent, false);

        //Bind Widget
        TextView bookTextView = (TextView) view.findViewById(R.id.textView8);
        TextView priceTextView = (TextView) view.findViewById(R.id.textView9);
        ImageView iconImageView = (ImageView) view.findViewById(R.id.imageView2);

        bookTextView.setText(bookStrings[position]);
        priceTextView.setText(priceStrings[position]);

        // การ Resize Pics โดยใช้ Picasso
        Picasso.with(context).load(iconStrings[position]).resize(150, 180).into(iconImageView);





        return view;
    }
}   //Main class
