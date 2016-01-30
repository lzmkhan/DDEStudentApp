package com.example.studentapp;

import android.content.Context;
import android.view.View;

public class contextHolder {

	public Context con;
	public View view;
private static contextHolder conHolder = new contextHolder();
	
	public static contextHolder getInstance()
	{
		return conHolder;
	}
	
	
	
	
}
