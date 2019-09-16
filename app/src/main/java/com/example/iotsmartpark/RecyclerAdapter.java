package com.example.iotsmartpark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHoder>{

        List<FireModel> list;
        Context context;

        public RecyclerAdapter(List<FireModel> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.cardview_lay,parent,false);
            MyHoder myHoder = new MyHoder(view);


            return myHoder;
        }

        @Override
        public void onBindViewHolder(MyHoder holder, int position) {
            FireModel mylist = list.get(position);
            holder.name.setText(mylist.getColor());
     //       Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.simpleImageView);

            //String str = holder.name.getText(holder.name.toString());

            if (mylist.getColor().equals("on"))
            {
                Glide.with(context).load(R.drawable.green_back).into(holder.simpleImageView);
       //        holder.simpleImageView.setImageResource(R.drawable.ic_launcher_background);

    //           simple.setColorFilter(ContextCompat.getColor(context, R.color), android.graphics.PorterDuff.Mode.MULTIPLY);
            //    simpleImageView.setImageResource(R.drawable.common_google_signin_btn_icon_dark);
            }else if (mylist.getColor().equals("off")) {

                Glide.with(context).load(R.drawable.red_back).into(holder.simpleImageView);
                // do something else
            //   holder.simpleImageView.setImageResource(R.drawable.ic_launcher_foreground);

               //     simpleImageView.setImageResource(R.drawable.ic_launcher_background);
           };

        }

        @Override
        public int getItemCount() {

            int arr = 0;

            try{
                if(list.size()==0){

                    arr = 0;

                }
                else{

                    arr=list.size();
                }



            }catch (Exception e){



            }

            return arr;

        }

        class MyHoder extends RecyclerView.ViewHolder{
            TextView name;
            ImageView simpleImageView;


            public MyHoder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.titleTxt);
                simpleImageView=(ImageView)itemView.findViewById(R.id.articleImage);
            }
        }


    }
