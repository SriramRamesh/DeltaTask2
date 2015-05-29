package com.example.android.deltatask2;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    public int uflag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] Names = getApplicationContext().getResources().getStringArray(R.array.Person_names);
        TypedArray Images = getApplicationContext().getResources().obtainTypedArray(R.array.Images);

        setListAdapter(new ImageAndTextAdapter(getApplicationContext(), R.layout.list_row,Names,Images,uflag));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void Search(View v)
    {
        EditText e=(EditText)findViewById(R.id.editText);
        String S=e.getText().toString();
        if(S.equals("Arnold")||S.equals("Kajol")||S.equals("Kangana")||S.equals("LarryPage")||S.equals("Jessica")||S.equals("Dhoni"))
        {
            Toast toast=Toast.makeText(getApplicationContext(),"Contact Found:"+S,Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {   Toast toast=Toast.makeText(getApplicationContext(),"Missing",Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void Sort(View v)
    {
        if(uflag==1)
            uflag=0;
        else
            uflag=1;
        String[] options = getApplicationContext().getResources().getStringArray(R.array.Person_names);
        TypedArray icons = getApplicationContext().getResources().obtainTypedArray(R.array.Images);
        setListAdapter(new ImageAndTextAdapter(getApplicationContext(), R.layout.list_row, options, icons,uflag));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

    class ImageAndTextAdapter extends ArrayAdapter<String> {

        private LayoutInflater mInflater;

        private String[] mStrings;
        private TypedArray mIcons;
        private int  mflag;
        private int mViewResourceId;
        //Got this definition from Internet Source
        public ImageAndTextAdapter(Context ctx, int viewResourceId,
                                   String[] strings, TypedArray icons,int flag) {
            super(ctx, viewResourceId, strings);

            mInflater = (LayoutInflater)ctx.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            mflag=flag;
            mStrings = strings;
            mIcons = icons;

            mViewResourceId = viewResourceId;
        }

        @Override
        public int getCount() {
            return mStrings.length;
        }

        @Override
        public String getItem(int position) {

            return mStrings[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(mViewResourceId, null);

            ImageView iv = (ImageView)convertView.findViewById(R.id.Ricon);
            if(mflag==1)
                iv.setImageDrawable(mIcons.getDrawable(position));
            else
                iv.setImageDrawable(mIcons.getDrawable(5-position));
            TextView tv = (TextView)convertView.findViewById(R.id.Rtext);
            if(mflag==1)
                tv.setText(mStrings[position]);
            else
                tv.setText(mStrings[5-position]);


            return convertView;
        }
    }