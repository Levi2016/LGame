﻿package org.test;

import loon.core.geom.RectBox;
import loon.core.graphics.opengl.LTexture;

public class CMenuItem
{
	public float alpha;
	public int button;
	public int count;
	public float currentItemScale;
	public float currentSelScale;
	public float itemScale;
	public boolean noButtonButton;
	public RectBox pos = new RectBox();
	public float scaleTime;
	public LTexture[] selectedTexture;
	public float selScale;
	public boolean skipItem;
	public LTexture[] texture;
	public int value;
}