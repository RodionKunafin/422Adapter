package com.example.a422adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemsDataAdapter extends BaseAdapter {

    private List<ItemData> items;

    private LayoutInflater inflater;


    private CompoundButton.OnCheckedChangeListener myCheckChangeList
            = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            items.get((Integer) buttonView.getTag()).setChecked(isChecked);
        }
    };


    ItemsDataAdapter(Context context, List<ItemData> items) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    void addItem(ItemData item) {
        this.items.add(item);
        notifyDataSetChanged();
    }


    void removeItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return items.size();
    }


    @Override
    public ItemData getItem(int position) {
        if (position < items.size()) {
            return items.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_view, parent, false);
        }
        Button btnRemove = view.findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });

        ItemData itemData = items.get(position);

        ImageView image = view.findViewById(R.id.icon);
        TextView title = view.findViewById(R.id.title);
        TextView subtitle = view.findViewById(R.id.subtitle);
        CheckBox checkBox = view.findViewById(R.id.checkbox);

        image.setImageDrawable(itemData.getImage());
        title.setText(itemData.getTitle());
        subtitle.setText(itemData.getSubtitle());
        checkBox.setOnCheckedChangeListener(myCheckChangeList);
        checkBox.setTag(position);
        checkBox.setChecked(itemData.isChecked());

        return view;

    }
}
