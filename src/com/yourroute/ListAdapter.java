package com.yourroute;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<CarData> {
    private List<CarData> objects;
	
	public ListAdapter(Context context, int textViewResourceId, List<CarData> objects) {		
		super(context, textViewResourceId, objects);
		this.objects = objects;
	}

	@Override
	public View getView(int position, View item, ViewGroup parent) {
		int[] colors = new int[2];
		colors[0] = Color.parseColor("#F5F5F5");
		colors[1] = Color.parseColor("#E8E8E8");
		
		if (item == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			item = inflater.inflate(R.layout.item, null);
		}
		CarData car = this.objects.get(position);
		TextView name = (TextView) item.findViewById(R.id.name);
		name.setText(car.Name);
		TextView carType = (TextView) item.findViewById(R.id.carType);
		carType.setText(car.CarType);
		TextView startEnd = (TextView) item.findViewById(R.id.startEnd);
		startEnd.setText(car.StartEnd);		
		item.setBackgroundColor(colors[position % 2]);
		
		return item;
	}
		
	
}
